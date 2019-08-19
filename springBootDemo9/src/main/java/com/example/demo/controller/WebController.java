package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.Car;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.group.GroupA;
import com.example.demo.model.group.GroupB;
import com.example.demo.model.group.GroupOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@RestController
//@Validated
public class WebController
{
    @RequestMapping("/saveUser")
    public void saveUser(@Valid User user, BindingResult result)
    {
        System.out.println("user:" + user);
        if (result.hasErrors())
        {
            for (ObjectError error : result.getAllErrors())
            {
                System.out.println(error.getCode() + "-" + error.getDefaultMessage());
            }
        }
    }


    @RequestMapping("/getParam")
    public void getParam(@NotEmpty(message = "姓名不能为空") String userName, @Max(value = 100, message = "年龄不能大于100岁")int age)
    {
        System.out.println("userName:" + userName);
        System.out.println("age:" + age);
    }


    /**前面配置了快速失败返回的Bean*/
    @Autowired
    private Validator validator;

    @RequestMapping("/saveUser2")
    public void saveUser2()
    {
        Car car = new Car();
//        car.setCarName("abc");

        User user = new User();
        user.setUserName("111");
        user.setAge(30);
        user.setRemark("rmkrmkrmk");
        user.setCar(car);

        Set<ConstraintViolation<User>> violationSet = validator.validate(user);
        for (ConstraintViolation<User> model : violationSet) {
            System.out.println(model.getMessage());
        }
    }

    @RequestMapping("/test3")
    public void test3(@Validated({GroupA.class, GroupB.class}) Role role, BindingResult result)
    {
        if (result.hasErrors())
        {
            for (ObjectError error : result.getAllErrors())
            {
                System.out.println(error.getCode() + "-" + error.getDefaultMessage());
            }
        }
    }

    @RequestMapping("/test4")
    public void test4()
    {
        Role role = new Role();

        /**GroupA验证不通过*/
        role.setRoleID(-12);

        /**GroupA验证通过*/
        //role.setUserId(12);

        role.setRoleName("a");
        role.setRoleNO(110);
        role.setIsActive(5);

        Set<ConstraintViolation<Role>> validate = validator.validate(role, GroupA.class, GroupB.class);
        for (ConstraintViolation<Role> item : validate)
        {
            System.out.println(item.getMessage());
        }
    }

    @RequestMapping("/test5")
    public void test5(@Validated({GroupOrder.class}) Role role, BindingResult result)
    {
        if (result.hasErrors())
        {
            for (ObjectError error : result.getAllErrors())
            {
                System.out.println(error.getCode() + "-" + error.getDefaultMessage());
            }
        }
    }

    @RequestMapping("/test6")
    public void test6()
    {
        Book book = new Book();
        book.setBookName("a tale of two cities");
//        book.setBookName("A TALE OF TWO CITIES");

        Set<ConstraintViolation<Book>> validate = validator.validate(book);
        for (ConstraintViolation<Book> item : validate)
        {
            System.out.println(item.getMessage());
        }
    }
}
