package com.user.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateOrderRequest {


    @NotNull(message = "product should not null")
    @NotBlank(message = "product can not be blank")
    @Size(min = 3, max = 50, message = "product must be between 3 and 20 characters")
    private String product;

    @Positive(message = "amount must be grater than 0")
    private double amount;
}

