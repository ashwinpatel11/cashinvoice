package com.user.dto;

import lombok.Data;

@Data
public class Product {

    private Long id;
    private String name;
    private long qty;
    private double price;
    private boolean isActive;
    private long userId;

}
