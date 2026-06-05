package com.customer.chatbot.controller; // Use your actual package structure

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeepAliveController {

    @GetMapping("/api/ping")
    public String keepAlivePing() {
        return "Server Awake";
    }
}