package com.example.springstudy.controller;

import com.example.springstudy.MyData;
import com.example.springstudy.repositories.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @Autowired
    MyDataRepository repository;

    @RequestMapping(value = "/")
    public ModelAndView index(ModelAndView mv){
        mv.setViewName("index");
        Iterable<MyData> list=repository.findAll();
        mv.addObject("data",list);
        return mv;
    }

    /*@RequestMapping(value="/", method = RequestMethod.POST)
    public ModelAndView send(@RequestParam("text1")String name, ModelAndView mav){
        mav.addObject("msg","안녕하세요 "+name+" 님!");
        mav.addObject("value",name);
        mav.setViewName("index");
        return mav;
    }*/

}
