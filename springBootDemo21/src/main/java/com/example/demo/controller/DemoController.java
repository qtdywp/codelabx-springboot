package com.example.demo.controller;

import com.example.demo.config.BaseResult;
import com.example.demo.model.Blog;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "微博API", protocols = "http")
@RestController
public class DemoController
{
    // 获取blog列表
    @ApiOperation(value = "获取blog列表", notes = "获取全部已发布的blog列表", produces = "application/json, application/xml", consumes = "application/json, application/xml")
    @GetMapping("Blogs")
    public List<Blog> Blogs()
    {
        System.out.println("select id, Title, Content from blogs");
        List<Blog> blogs = new ArrayList<>();
        blogs.add(new Blog(1L, "title1", "content1"));
        blogs.add(new Blog(2L, "title2", "content2"));
        return blogs;
    }

    // 发布一条blog
    @ApiOperation(value = "发布一条blog", notes = "发布一条全新的博文")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "不需要指定ID", required = false, dataType = "String", paramType = "query"), @ApiImplicitParam(name = "title", value = "标题", required = true, dataType = "String", paramType = "query"), @ApiImplicitParam(name = "content", value = "内容", required = true, dataType = "String", paramType = "query"),})
    @PostMapping("/Blog")
    public Blog create(Blog blog)
    {
        System.out.println("insert into blogs(Title, Content) values('" + blog.getTitle() + "','" + blog.getContent() + "')");
        return blog;
    }

    // 修改一条blog
    @ApiOperation(value = "修改一条blog", notes = "根据ID修改一条blog")
    @ApiResponses({@ApiResponse(code = 100, message = "请求参数有误"), @ApiResponse(code = 101, message = "未授权"), @ApiResponse(code = 103, message = "禁止访问"), @ApiResponse(code = 104, message = "请求路径不存在"), @ApiResponse(code = 500, message = "服务器内部错误")})
    @PutMapping("/Blog")
    public Blog update(Blog blog)
    {
        System.out.println("update blogs set id=" + blog.getId() + ",Title='" + blog.getTitle() + "',Content='" + blog.getContent() + "' where id=" + blog.getId());
        return blog;
    }

    // 修改一条blog的正文
    @ApiOperation(value = "修改一条blog的正文", notes = "根据ID修改一条blog的正文")
    @PatchMapping("/Blog/Content")
    public BaseResult<Blog> patch(Blog blog)
    {
        System.out.println("update blogs set Content='" + blog.getContent() + "' where id=" + blog.getId());
        return BaseResult.successWithData(blog);
    }

    // 获取一条blog
    @ApiOperation(value = "获取一条blog", notes = "根据ID获取一条blog")
    @GetMapping("/Blog/{id}")
    public Blog get(@PathVariable Long id)
    {
        System.out.println("select id, Title, Content from blogs where id=" + id);
        Blog Blog = new Blog(id, "titleXXX", "contentXXX");
        return Blog;
    }

    // 删除一条blog
    @ApiOperation(value = "删除一条blog", notes = "根据ID删除一条blog")
    @DeleteMapping("/Blog/{id}")
    public void delete(@PathVariable("id") Long id)
    {
        System.out.println("delete from blogs where id=" + id);
    }
}
