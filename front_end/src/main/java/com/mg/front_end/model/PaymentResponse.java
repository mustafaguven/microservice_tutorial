package com.mg.front_end.model;

import lombok.Getter;
import lombok.Setter;

@Setter
public class PaymentResponse {

    @Getter
    @Setter
    private int isSuccess;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private String appId;
}
