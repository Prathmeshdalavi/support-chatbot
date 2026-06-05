package com.customer.chatbot.controller;

import com.customer.chatbot.model.ChatMessage;
import com.customer.chatbot.service.ChatService;
import com.customer.chatbot.service.OpenAiService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.List;

@Controller
public class ChatController {

    private final ChatService chatService;
    private final OpenAiService openAiService;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(ChatService chatService, OpenAiService openAiService, SimpMessagingTemplate messagingTemplate) {
        this.chatService = chatService;
        this.openAiService = openAiService;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/chat")
    public String chatPage(Model model, Principal principal) {
        List<ChatMessage> history = chatService.getChatHistory(principal.getName());
        model.addAttribute("history", history);
        model.addAttribute("username", principal.getName());
        return "chat";
    }

    @MessageMapping("/chat.sendMessage")
    public void handleWebSocketMessage(ChatMessage msg, Principal principal) {
        String username = principal.getName();

        chatService.saveMessage(username, msg.getContent(), "USER");
        messagingTemplate.convertAndSendToUser(username, "/queue/messages", msg);

        String aiResponseText = openAiService.generateResponse(msg.getContent());
        ChatMessage aiMessage = chatService.saveMessage(username, aiResponseText, "AI");
        
        messagingTemplate.convertAndSendToUser(username, "/queue/messages", aiMessage);
    }
}