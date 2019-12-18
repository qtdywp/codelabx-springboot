package com.example.demo.service;

import com.example.demo.model.Customer;

import java.util.List;

public interface CustomersInterface
{
    public List<Customer> searchCity(Integer pageNumber, Integer pageSize, String searchContent);
}
