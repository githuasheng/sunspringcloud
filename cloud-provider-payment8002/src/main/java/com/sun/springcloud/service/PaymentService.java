package com.sun.springcloud.service;

import com.sun.springcloud.entities.Payment;

public interface PaymentService {
    int create(Payment payment);
    Payment getpaymentById(Long id);
}
