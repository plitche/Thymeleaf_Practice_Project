package com.example.Thymeleaf.controller;

import com.example.Thymeleaf.service.MemberService;
import com.example.Thymeleaf.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    final MemberService memberService;
    final ProductService productService;

    @RequestMapping("/")
    public String getMain(Model model) throws Exception {
        model.addAttribute("msg", "Hello World");
        model.addAttribute("productList", productService.getProductList());
        return "main";
    }

}
