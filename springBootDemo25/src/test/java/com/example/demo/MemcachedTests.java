package com.example.demo;

import net.rubyeye.xmemcached.CASOperation;
import net.rubyeye.xmemcached.Counter;
import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.transcoders.StringTranscoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetSocketAddress;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemcachedTests
{

    @Autowired
    private MemcachedClient memcachedClient;

    @Test
    public void testGetSet() throws Exception
    {
        // 第一个是存储的 key 名称
        // 第二个是过期时间（单位秒），超过这个时间，memcached 将这个数据替换出去，0 表示永久存储（默认是一个月）
        // 第三个参数就是实际存储的数据，可以是任意的 Java 可序列化类型。
        memcachedClient.set("hello", 0, "Hello,xmemcached");

        // 获取数据
        String value = memcachedClient.get("hello");
        System.out.println("hello=" + value);

        // 删除数据
        memcachedClient.delete("hello");
        value = memcachedClient.get("hello");
        System.out.println("hello=" + value);
    }

    @Test
    public void testMore() throws Exception
    {
        if (!memcachedClient.set("hello", 0, "world"))
        {
            System.err.println("set error");
        }

        // add与set的作用类似。区别是set会把key对应的value值替换掉，而add则先判断当前key下是否有值，如果有则不做任何操作，如果没有则存入
        if (!memcachedClient.add("hello", 0, "dennis"))
        {
            System.err.println("Add error,key is existed");
        }

        // 替换
        if (!memcachedClient.replace("hello", 0, "dennis"))
        {
            System.err.println("replace error");
        }

        // 在已有值的后面追加值
        memcachedClient.append("hello", " good");

        // 在已有值的前面追加值
        memcachedClient.prepend("hello", "hello ");

        String name = memcachedClient.get("hello");
        System.out.println(name);

        memcachedClient.deleteWithNoReply("hello");
    }

    @Test
    public void testIncrDecr() throws Exception
    {
        memcachedClient.delete("Incr");
        memcachedClient.delete("Decr");

        // 原子递增变量数值
        // 第一个参数指定递增的key名称
        // 第二个参数指定递增的幅度大小
        // 第三个参数指定当key不存在的情况下的初始值
        System.out.println(memcachedClient.incr("Incr", 6, 12));

        // 默认第三个参数是0
        System.out.println(memcachedClient.incr("Incr", 3));
        System.out.println(memcachedClient.incr("Incr", 2));

        // 原子递减变量数值
        // 参数规则与incr相同
        System.out.println(memcachedClient.decr("Decr", 1, 6));
        System.out.println(memcachedClient.decr("Decr", 2));
    }

    @Test
    public void testCounter() throws Exception
    {
        // 获取一个计数器实例
        // 第一个参数key
        // 第二个参数是当值不存在时的初始值
        Counter counter = memcachedClient.getCounter("counter1", 10);
        System.out.println("counter=" + counter.get());

        // 原子增加1
        long c1 = counter.incrementAndGet();
        System.out.println("counter=" + c1);

        // 原子减去1
        long c2 = counter.decrementAndGet();
        System.out.println("counter=" + c2);

        // 增加传入的值，如果是负数则相当于减法
        long c3 = counter.addAndGet(-6);
        System.out.println("counter=" + c3);
    }

    @Test
    public void testCas() throws Exception
    {
        // 设置初始值
        memcachedClient.set("hello", 0, 100);

        // 注意这里是gets不是get
        // gets方法获取一个GetsResponse，此对象包装了存储的数据和CAS值，该CAS值可以用来原子更新操作
        GetsResponse<Integer> result = memcachedClient.gets("hello");
        System.out.println("hello value " + result.getValue());

        // 传统方法(需要显式地提前调用gets获取CAS值)
        // 尝试将cas的值更新为200
        long cas = result.getCas();
        if (!memcachedClient.cas("hello", 0, 222, cas))
        {
            System.err.println("cas error");
        }
        System.out.println("hello value " + memcachedClient.get("hello"));

        // 新方法(无需显式地调用gets获取CAS值，且支持自定义"尝试更新次数")
        memcachedClient.cas("hello", 0, new CASOperation<Integer>()
        {
            // 设置"尝试更新次数"
            public int getMaxTries()
            {
                return 1;
            }

            // 自动获取当前的GetsResponse来更新数据
            // 如果更新成功，则意味着这个方法返回的值已存储成功。其这个方法的两个参数则是最新的GetsResponse中的两个值。
            public Integer getNewValue(long currentCAS, Integer currentValue)
            {
                return 999;
            }
        });
        System.out.println("hello value " + memcachedClient.get("hello"));
    }

    @Test
    public void testTouch() throws Exception
    {
        // 设置操作等待的超时时间2秒（默认5秒）
        memcachedClient.set("Touch", 2, "Touch Value");

        // 设置缓存过期时间（2秒）
        // 如果报错Unknow command TOUCH，则服务端版本过低，需要更新
        memcachedClient.touch("Touch", 2);
        Thread.sleep(3000);

        // 设置操作等待的超时时间1秒（默认5秒）
        String value = memcachedClient.get("Touch", 1000);
        System.out.println("Touch=" + value);
    }

    @Test
    public void testStat() throws Exception
    {
        // 查看统计信息
        Map<InetSocketAddress, Map<String, String>> result = memcachedClient.getStats();
        System.out.println("Stats=" + result.toString());

        // 根据传入需要统计的项目名称，返回统计信息
        Map<InetSocketAddress, Map<String, String>> items = memcachedClient.getStatsByItem("items");
        System.out.println("items=" + items.toString());
    }

}