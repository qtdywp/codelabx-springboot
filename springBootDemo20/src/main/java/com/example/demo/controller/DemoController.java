package com.example.demo.controller;


import com.example.demo.model.Blog;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController
{
    // 获取blog列表
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
    @PostMapping("/Blog")
    public Blog create(Blog blog)
    {
        System.out.println("insert into blogs(id, Title, Content) values(" + blog.getId() + ",'" + blog.getTitle() + "','" + blog.getContent() + "')");
        return blog;
    }

    // 修改一条blog
    @PutMapping("/Blog")
    public Blog update(Blog blog)
    {
        System.out.println("update blogs set id=" + blog.getId() + ",Title='" + blog.getTitle() + "',Content='" + blog.getContent() + "' where id=" + blog.getId());
        return blog;
    }

    // 修改一条blog的内容
    @PatchMapping("/Blog/Content")
    public Blog patch(Blog blog)
    {
        System.out.println("update blogs set Content='" + blog.getContent() + "' where id=" + blog.getId());
        return blog;
    }

    // 获取一条blog
    @GetMapping("/Blog/{id}")
    public Blog get(@PathVariable Long id)
    {
        System.out.println("select id, Title, Content from blogs where id=" + id);
        Blog Blog = new Blog(id, "titleXXX", "contentXXX");
        return Blog;
    }

    // 删除一条blog
    @DeleteMapping("/Blog/{id}")
    public void delete(@PathVariable("id") Long id)
    {
        System.out.println("delete from blogs where id=" + id);
    }
}
