package com.sun.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {
    //指定轮询的规则
    @Bean
    public IRule myRule(){
        return new RandomRule();//
    }
}
