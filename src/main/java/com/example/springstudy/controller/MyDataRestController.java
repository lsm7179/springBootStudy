package com.example.springstudy.controller;

import com.example.springstudy.component.MySampleBean;
import com.example.springstudy.data.MyData;
import com.example.springstudy.service.MyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyDataRestController {

    @Autowired
    private MyDataService service;

    @Autowired
    MySampleBean bean;

    @RequestMapping("/rest")
    public List<MyData> restAll() {
        return service.getAll();
    }

    @RequestMapping("/rest/{num}")
    public MyData restBy(@PathVariable int num) {
        return service.get(num);
    }

    @RequestMapping("/count")
    public int count(){
        return bean.count();
    }
}
