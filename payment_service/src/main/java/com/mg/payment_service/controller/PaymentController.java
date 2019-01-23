package com.mg.payment_service.controller;

import com.mg.payment_service.model.PaymentResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class PaymentController {

    @Value("${app.id}")
    private String instance;

    @Value("${payment.name}")
    private String paymentName;

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping(value = "/{id}")
    public PaymentResponse makePayment(@PathVariable("id") Integer id) {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setIsSuccess(1);
        paymentResponse.setAppId(instance);
        paymentResponse.setMessage(String.format("%s nolu katalogun payment islemi tamamlandi ----> %s", id, paymentName));
        return paymentResponse;
    }

    public PaymentResponse fallback(@PathVariable("id") Integer id){
        return new PaymentResponse();
    }

}
