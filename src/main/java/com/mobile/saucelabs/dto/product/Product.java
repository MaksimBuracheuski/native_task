package com.mobile.saucelabs.dto.product;

import lombok.Data;

@Data
public class Product {

    private String name;
    private String price;
    private String description;

    public Product(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, String price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
