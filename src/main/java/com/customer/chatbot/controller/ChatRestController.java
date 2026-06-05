package com.customer.chatbot.controller;

import com.customer.chatbot.model.ChatMessage;
import com.customer.chatbot.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatRestController {

    private final ChatService chatService;

    public ChatRestController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/history")
    public ResponseEntity<List<ChatMessage>> getHistoryApi(Principal principal) {
        return ResponseEntity.ok(chatService.getChatHistory(principal.getName()));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearChat(Principal principal) {
        chatService.clearChatHistory(principal.getName());
        return ResponseEntity.ok().build();
    }
}