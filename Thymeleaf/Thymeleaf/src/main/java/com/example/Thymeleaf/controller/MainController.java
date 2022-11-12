package com.example.Thymeleaf.controller;

import com.example.Thymeleaf.dto.ProductDto;
import com.example.Thymeleaf.service.MemberService;
import com.example.Thymeleaf.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MainController {

    final MemberService memberService;
    final ProductService productService;

    @RequestMapping("/")
    public String getMain(Model model) throws Exception {
        model.addAttribute("productList", productService.getProductList());
        return "main";
    }

    @RequestMapping("/product/detail/{productSeq}")
    @ResponseBody
    public ProductDto getProductDetail(@PathVariable int productSeq) throws Exception {
        return productService.getProduct(productSeq);
    }

    @RequestMapping("/product/add")
    public String addProductPage() {
        return "/product/addProduct";
    }

}
