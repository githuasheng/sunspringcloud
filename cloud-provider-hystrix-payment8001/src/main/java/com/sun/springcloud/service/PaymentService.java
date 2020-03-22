package com.sun.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     * 正常访问
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池：" +Thread.currentThread().getName() + "paymentInfo_OK  id:"+id;
    }

    /**
     * 超时
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")//超时设置响应时间为3秒否则走降级方法
    })
    public String paymentInfo_TimeOut(Integer id){
        try {
            TimeUnit.SECONDS.sleep(3);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //int i= 10/0;
        return "线程池：" +Thread.currentThread().getName() + "paymentInfo_TimeOut  id:"+id;
    }
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池：" +Thread.currentThread().getName() + "paymentInfo_TimeOutHandler  id:"+id +"服务端降级处理";
    }
}
