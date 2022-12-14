package com.example.Thymeleaf.controller;

import com.example.Thymeleaf.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.ServiceNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    final ChatService chatService;

    @RequestMapping()
    public String goChatPage(HttpServletRequest request,
                             Model model) throws ServiceNotFoundException {
        HttpSession httpSession = request.getSession();
        String memberId = (String) httpSession.getAttribute("memberId");

        if (memberId == null) {
            throw new ServiceNotFoundException("Not login Exception - ChatController");
        }

        int applyCount = chatService.applyChat(memberId);
        model.addAttribute("applyCount", applyCount);

        return "/chat/main";
    }
}
