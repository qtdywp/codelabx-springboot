package com.example.demo.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


public class User
{
    @NotEmpty(message = "姓名不能为空")
    private String userName;

    @Max(value = 100, message = "年龄不能大于100岁")
    @Min(value = 18, message = "必须年满18岁！")
    private int age;

    @NotEmpty(message = "备注不能为空")
    @Length(min = 6, message = "备注长度不能小于6位")
    private String remark;

    @Valid
    private Car car;

    public Car getCar()
    {
        return car;
    }

    public void setCar(Car car)
    {
        this.car = car;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    @Override
    public String toString()
    {
        return "userName:" + userName + ",age=" + age + ",remark=" + remark;
    }
}
