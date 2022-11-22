package com.example.Thymeleaf.controller;

import com.example.Thymeleaf.dto.MemberDto;
import com.example.Thymeleaf.service.MemberService;
import com.example.Thymeleaf.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    final MemberService memberService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public MemberDto login(@RequestParam String id,
                      @RequestParam String password,
                      Model model) throws Exception {
        System.out.println("id = " + id);
        System.out.println("password = " + password);
        MemberDto memberDto = memberService.login(id, password);
        return memberDto;
    }
}
