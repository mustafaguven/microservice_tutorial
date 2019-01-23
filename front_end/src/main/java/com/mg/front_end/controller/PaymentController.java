package com.mg.front_end.controller;

import com.mg.front_end.model.PaymentResponse;
import com.mg.front_end.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @HystrixCommand(fallbackMethod = "fallbackPayment")
    @GetMapping("/{id}")
    public PaymentResponse paymentById(@PathVariable("id") Integer id) {
        return paymentService.getPaymentById(id);
    }

    public PaymentResponse fallbackPayment(Integer id) {
        PaymentResponse response = new PaymentResponse();
        response.setAppId("-1");
        response.setIsSuccess(0);
        response.setMessage("hata payback calismiyor");
        return response;
    }
}
