package com.example.demo.model;

import javax.validation.constraints.NotEmpty;

public class Car
{
    @NotEmpty(message = "carName不能为空")
    private String carName;

    public String getCarName()
    {
        return carName;
    }

    public void setCarName(String carName)
    {
        this.carName = carName;
    }
}
