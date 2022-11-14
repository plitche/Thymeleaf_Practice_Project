package com.example.Thymeleaf.service;

import com.example.Thymeleaf.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private Integer seq = 0;
    private List<ProductDto> productDtoList = new ArrayList<>();

    public ProductService() {
        this.productDtoList.add(new ProductDto(++seq, "java", 10000, "자바 책 입니다."));
        this.productDtoList.add(new ProductDto(++seq, "spring", 20000, "스프링 책 입니다."));
        this.productDtoList.add(new ProductDto(++seq, "node", 15000, "노드 책 입니다."));
        this.productDtoList.add(new ProductDto(++seq, "thymeleaf", 30000, "타임리프 책 입니다."));
    }

    public List<ProductDto> getProductList() {
        return this.productDtoList;
    }

    public ProductDto getProduct(int seq) throws Exception {
        return this.productDtoList.stream()
                .findFirst().filter(productDto -> productDto.getProductSeq() == seq)
                .orElseThrow(() -> new Exception("bad product seq parameter ERROR"));
    }

    public ProductDto addProduct(String productName, int price, String desc) throws Exception {
        ProductDto productDto = new ProductDto(++seq, productName, price, desc);
        this.productDtoList.add(productDto);
        return getProduct(productDto.getProductSeq());
    }

}
