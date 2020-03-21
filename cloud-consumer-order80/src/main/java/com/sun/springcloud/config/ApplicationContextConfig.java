package com.sun.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    //@LoadBalanced //如果多个微服务则需要这个注解开启负载均衡机制
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
