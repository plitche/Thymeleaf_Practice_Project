package com.example.Thymeleaf.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private Integer productSeq;
    private String productName;
    private Integer productPrice;

    public ProductDto(Integer productSeq, String productName, Integer productPrice) {
        this.productSeq = productSeq;
        this.productName = productName;
        this.productPrice = productPrice;
    }
}
