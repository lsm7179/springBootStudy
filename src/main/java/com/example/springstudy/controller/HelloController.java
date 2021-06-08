package com.example.springstudy.controller;

import com.example.springstudy.MyData;
import com.example.springstudy.repositories.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.PostConstruct;

@Controller
public class HelloController {

    @Autowired
    MyDataRepository repository;

    @PostConstruct
    public void init(){
        MyData md=new MyData();
        md.setName("lee");
        md.setAge(30);
        md.setMail("sml@test.com");
        md.setMemo("this is init Data");
        repository.saveAndFlush(md);

        MyData md2=new MyData();
        md2.setName("kim");
        md2.setAge(40);
        md2.setMail("kim@test.com");
        md2.setMemo("this is init Data2");
        repository.saveAndFlush(md2);

        MyData md3=new MyData();
        md3.setName("park");
        md3.setAge(20);
        md3.setMail("park@test.com");
        md3.setMemo("this is init Data3");
        repository.saveAndFlush(md3);
    }

    @RequestMapping(value = "/", method=RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("formModel") MyData myData,ModelAndView mv){
        mv.setViewName("index");
        mv.addObject("msg","this is sample content.");
        Iterable<MyData> list = repository.findAll();
        mv.addObject("datalist",list);
        return mv;
    }

    @RequestMapping(value = "/", method=RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView form(@ModelAttribute("formModel") MyData myData, ModelAndView mav){
        repository.saveAndFlush(myData);
        return new ModelAndView("redirect:/");
    }

    /*@RequestMapping(value="/", method = RequestMethod.POST)
    public ModelAndView send(@RequestParam("text1")String name, ModelAndView mav){
        mav.addObject("msg","안녕하세요 "+name+" 님!");
        mav.addObject("value",name);
        mav.setViewName("index");
        return mav;
    }*/

}
