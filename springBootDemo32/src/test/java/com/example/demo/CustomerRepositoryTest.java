package com.example.demo;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.Map;


@SpringBootTest
public class CustomerRepositoryTest
{
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void saveCustomers()
    {
        // 新增
        repository.save(new Customer("Alice", "北京", 13));
        repository.save(new Customer("Bob", "北京", 23));
        repository.save(new Customer("wang", "石家庄", 30));
        repository.save(new Customer("summer", "上海", 22));
    }

    @Test
    public void fetchAllCustomers()
    {
        // 查询
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        Iterable<Customer> iterable = repository.findAll();
        for (Customer customer : iterable)
        {
            System.out.println(customer);
        }
    }

    @Test
    public void updateCustomers()
    {
        // 更新
        Customer customer = repository.findByUserName("summer");
        customer.setAddress("北京市海淀区");
        repository.save(customer);
    }

    @Test
    public void fetchIndividualCustomers()
    {
        // 按条件查询
        System.out.println("repository.findByUserName(\"summer\"):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByUserName("summer"));
    }

    @Test
    public void fetchIndividualCustomers2()
    {
        // 按条件查询2
        System.out.println("Customers found with findByAddress(\"北京\"):");
        System.out.println("--------------------------------");
        String q = "北京";
        for (Customer customer : repository.findByAddress(q))
        {
            System.out.println(customer);
        }
    }

    @Test
    public void fetchPageCustomers()
    {
        // 使用Spring Data提供的Pageable分页查询
        System.out.println("使用Spring Data提供的Pageable分页查询:");
        System.out.println("-------------------------------");
        Sort sort = Sort.by(Sort.Direction.DESC, "address.keyword");
        Pageable pageable = PageRequest.of(0, 2, sort);
        Page<Customer> pages = repository.findByAddress("北京", pageable);
        System.out.println("Page customers " + pages.getContent().toString());
    }

    @Test
    public void fetchPageCustomers2()
    {
        // 使用ES提供的QueryBuilder构建复杂条件并带分页的查询
        // 查询条件可以是must(等同于AND)、mustNot(等同于NOT)、should(等同于OR)
        // 注意should不能与must同时使用
        System.out.println("使用ES提供的QueryBuilder构建复杂条件并带分页的查询:");
        System.out.println("-------------------------------");
        QueryBuilder customerQuery = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("address", "北京"));
        Page<Customer> pages = repository.search(customerQuery, PageRequest.of(0, 2));
        System.out.println("Page customers " + pages.getContent().toString());
        pages = repository.search(customerQuery, PageRequest.of(1, 2));
        System.out.println("Page customers " + pages.getContent().toString());
    }

    @Test
    public void fetchShouldNoPage()
    {
        // 使用ES提供的QueryBuilder构建复杂条件查询
        System.out.println("使用ES提供的QueryBuilder构建复杂条件查询:");
        System.out.println("-------------------------------");
        QueryBuilder customerQuery = QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("address", "北京")).should(QueryBuilders.matchQuery("userName", "wang"));
        Iterable<Customer> iterable = repository.search(customerQuery);
        for (Customer customer : iterable)
        {
            System.out.println(customer.toString());
        }
    }

    @Test
    public void fetchAggregation()
    {
        // 聚合查询
        System.out.println("Customers found with fetchAggregation:");
        System.out.println("-------------------------------");

        // 使用QueryBuilder构建查询条件
        QueryBuilder customerQuery = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("address", "北京"));

        // 使用SumAggregationBuilder指明需要聚合的字段
        SumAggregationBuilder sumBuilder = AggregationBuilders.sum("sumAge").field("age");

        // 把前两部分的内容为参数构建成SearchQuery
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(customerQuery).addAggregation(sumBuilder).build();

        // 把合并之后的SearchQuery作为参数传给ElasticsearchTemplate查询
        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Aggregations>()
        {
            @Override
            public Aggregations extract(SearchResponse response)
            {
                return response.getAggregations();
            }
        });

        // 解析聚合查询结果
        // 转换成map集合
        Map<String, Aggregation> aggregationMap = aggregations.asMap();

        // 获得对应的聚合函数的聚合子类，该聚合子类也是个map集合,里面的value就是桶Bucket，我们要获得Bucket
        InternalSum sumAge = (InternalSum) aggregationMap.get("sumAge");
        System.out.println("sum age is " + sumAge.getValue());
    }

    @Test
    public void deleteCustomers()
    {
        // 删除
        repository.deleteAll();
        //repository.deleteByUserName("wang");
    }
}
