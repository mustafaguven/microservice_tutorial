package com.mg.front_end.service;

import com.mg.front_end.model.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "payment-service")
public interface PaymentService {

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    PaymentResponse getPaymentById(@PathVariable("id") Integer id);
}
