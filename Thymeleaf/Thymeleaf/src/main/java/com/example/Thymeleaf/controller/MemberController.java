package com.example.Thymeleaf.controller;

import com.example.Thymeleaf.service.MemberService;
import com.example.Thymeleaf.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    final MemberService memberService;
    final ProductService productService;

    @RequestMapping("/login")
    public void login() {

    }
}
