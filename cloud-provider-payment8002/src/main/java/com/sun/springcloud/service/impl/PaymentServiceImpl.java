package com.sun.springcloud.service.impl;

import com.sun.springcloud.dao.PaymentDao;
import com.sun.springcloud.entities.Payment;
import com.sun.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    };
    public Payment getpaymentById( Long id){
        return paymentDao.getpaymentById(id);
    };
}
