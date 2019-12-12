package com.example.demo.Model;

import java.io.Serializable;

public class User implements Serializable
{
    private Long id;

    private String userName;

    private String passWord;


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassWord()
    {
        return passWord;
    }

    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }

    @Override
    public String toString()
    {
        return "User{id=" + this.id + ",userName=" + this.userName + ",passWord=" + this.passWord + "}";
    }
}
