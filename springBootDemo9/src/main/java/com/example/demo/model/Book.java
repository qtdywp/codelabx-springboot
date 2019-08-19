package com.example.demo.model;

import com.example.demo.util.CheckCase.CaseMode;
import com.example.demo.util.CheckCase.CheckCase;

public class Book
{
    @CheckCase(value = CaseMode.UPPER, message = "书名必须大写")
    String bookName;

    public String getBookName()
    {
        return bookName;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }
}
