package com.example.demo.model;

public class Blog
{
    private Long id;
    private String Title;
    private String Content;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return Title;
    }

    public void setTitle(String title)
    {
        Title = title;
    }

    public String getContent()
    {
        return Content;
    }

    public void setContent(String content)
    {
        Content = content;
    }
    
    public Blog(Long id, String title, String content)
    {
        this.setId(id);
        this.setTitle(title);
        this.setContent(content);
    }
}
