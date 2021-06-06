package com.example.springstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mv){
        mv.setViewName("index");
        mv.addObject("msg","이름을 적어서 전송해주세요.");
        return mv;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public ModelAndView send(@RequestParam("text1")String name, ModelAndView mav){
        mav.addObject("msg","안녕하세요 "+name+" 님!");
        mav.addObject("value",name);
        mav.setViewName("index");
        return mav;
    }

}
