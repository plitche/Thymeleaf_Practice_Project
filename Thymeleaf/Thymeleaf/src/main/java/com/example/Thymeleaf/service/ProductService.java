package com.example.Thymeleaf.service;

import com.example.Thymeleaf.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private Integer seq = 0;
    private List<ProductDto> productDtoList;


    public ProductService() {
        this.productDtoList.add(new ProductDto(++seq, "java", 10000));
        this.productDtoList.add(new ProductDto(++seq, "spring", 20000));
        this.productDtoList.add(new ProductDto(++seq, "node", 15000));
        this.productDtoList.add(new ProductDto(++seq, "thymeleaf", 30000));
    }

    public List<ProductDto> getProductList() {
        return this.productDtoList;
    }

    public ProductDto getProduct(int seq) throws Exception {
        return this.productDtoList.stream()
                .findFirst().filter(productDto -> productDto.getProductSeq() == seq)
                .orElseThrow(() -> new Exception("bad product seq parameter ERROR"));
    }


}
