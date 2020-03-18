package com.sun.springcloud.dao;

import com.sun.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PaymentDao {
    public int create(Payment payment);
    public Payment getpaymentById(@Param("id") Long id);
}
