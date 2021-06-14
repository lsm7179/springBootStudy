package com.example.springstudy.config;

import com.example.springstudy.component.MyDataBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBootAppConfig {

    @Bean
    MyDataBean myDataBean(){
        return new MyDataBean();
    }

}
