package com.example.springstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {


    @RequestMapping("/{num}")
    public ModelAndView index(@PathVariable int num, ModelAndView mv){
        int res=0;
        for(int i=0;i<=num;i++)
            res+=i;
        mv.addObject("msg","total: "+res);
        mv.setViewName("index");
        return mv;
    }


}
