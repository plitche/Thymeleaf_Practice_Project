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
public class MemberController {

    final MemberService memberService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public MemberDto login(@RequestParam String memberId,
                      @RequestParam String password,
                      Model model) throws Exception {
        MemberDto memberDto = memberService.login(memberId, password);
        return memberDto;
    }
}
