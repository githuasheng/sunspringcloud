package com.sun.springcloud.controller;


import com.sun.springcloud.entities.CommonResult;
import com.sun.springcloud.entities.Payment;
import com.sun.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String servicePort; //端口
    @Resource //java 注解
    private DiscoveryClient discoveryClient; //这个类是获取eureka注册服务相关信息

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if (result>0){
            return new CommonResult(200, "插入数据库成功,"+servicePort, result);
        }else{
            return new CommonResult(444, "插入数据库失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getpaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getpaymentById(id);
        if (null != payment){
            return new CommonResult(200, "查询成功1"+servicePort, payment);
        }else{
            return new CommonResult(444, "查询失败"+id);
        }
    }
    @GetMapping(value = "/payment/discovery")//cloud-payment-service
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println("****element:" + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances) {
            System.out.println(instance.getInstanceId()+":"+instance.getHost()+":"+instance.getPort()+"==>"+instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return servicePort;
    }

    public String paymentFeignTimeout(){
        return null;
    }
}
