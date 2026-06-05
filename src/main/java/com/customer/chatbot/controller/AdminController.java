package com.customer.chatbot.controller;

import com.customer.chatbot.service.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ChatService chatService;

    public AdminController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("logs", chatService.getAllConversations());
        return "admin";
    }
}