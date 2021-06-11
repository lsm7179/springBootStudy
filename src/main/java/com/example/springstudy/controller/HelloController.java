package com.example.springstudy.controller;

import com.example.springstudy.data.MyData;
import com.example.springstudy.dao.MyDataDaoImpl;
import com.example.springstudy.repositories.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    MyDataRepository repository;

    @PersistenceContext
    EntityManager entityManager;

    MyDataDaoImpl dao;

    @PostConstruct
    public void init(){
        dao=new MyDataDaoImpl(entityManager);
        MyData md=new MyData();
        md.setName("lee");
        md.setAge(30);
        md.setMail("sml@test.com");
        md.setMemo("01011112222");
        repository.saveAndFlush(md);

        MyData md2=new MyData();
        md2.setName("kim");
        md2.setAge(40);
        md2.setMail("kim@test.com");
        md2.setMemo("01011112223");
        repository.saveAndFlush(md2);

        MyData md3=new MyData();
        md3.setName("park");
        md3.setAge(20);
        md3.setMail("park@test.com");
        md3.setMemo("01011112224");
        repository.saveAndFlush(md3);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView find(ModelAndView mav){
        mav.setViewName("find");
        mav.addObject("title","Find Page");
        mav.addObject("msg","MyData의 예제입니다.");
        mav.addObject("value","");
        Iterable<MyData> list = dao.getAll();
        mav.addObject("datalist",list);
        return mav;
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request, ModelAndView mav){
        mav.setViewName("find");
        String param = request.getParameter("fstr");
        if(param==""){
            mav=new ModelAndView("redirect:/find");
        }else{
            mav.addObject("title","Find result");
            mav.addObject("msg",param+" 의 검색 결과");
            mav.addObject("value",param);
            List<MyData> list =dao.find(param);
            mav.addObject("datalist",list);
        }
        return mav;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav){
        mav.setViewName("list/index");
        mav.addObject("msg","MyData의 예제입니다.");
        //Iterable<MyData> list = dao.getAll();
        Iterable<MyData> list = repository.findAllOderByName();
        mav.addObject("datalist",list);
        return mav;
    }

    @RequestMapping(value = "/", method=RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("formModel") MyData myData,ModelAndView mv){
        mv.setViewName("index");
        mv.addObject("msg","this is sample content.");
        mv.addObject("formModel",myData);
        Iterable<MyData> list = repository.findAll();
        mv.addObject("datalist",list);
        return mv;
    }

    @RequestMapping(value = "/", method=RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView form(@ModelAttribute("formModel") @Validated MyData myData, BindingResult result, ModelAndView mav){
        ModelAndView res = null;
        if(!result.hasErrors()){
            repository.saveAndFlush(myData);
            res=new ModelAndView("redirect:/");
        }else{
            mav.setViewName("index");
            mav.addObject("msg","sorry, error is occured...");
            Iterable<MyData> list=repository.findAll();
            mav.addObject("datalist",list);
            res=mav;
        }
        return res;
    }

    @RequestMapping(value = "/edit/{id}", method=RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute MyData myData,@PathVariable int id,ModelAndView mav){
        mav.setViewName("edit");
        mav.addObject("title","edit mydata. ");
        MyData data=repository.findById((long)id).get();
        mav.addObject("formModel",data);
        return mav;
    }

    @RequestMapping(value = "/edit", method=RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView update(@ModelAttribute MyData myData, ModelAndView mav){
        repository.saveAndFlush(myData);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id, ModelAndView mav){
        mav.setViewName("delete");
        mav.addObject("title","delete mydata.");
        MyData data=repository.findById((long)id).get();
        mav.addObject("formModel",data);
        return mav;
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView remove(@RequestParam long id,ModelAndView mav){
        repository.deleteById(id);
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
