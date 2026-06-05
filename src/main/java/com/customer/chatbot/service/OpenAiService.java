package com.customer.chatbot.service;

import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OpenAiService {

    private final ChatClient chatModel;

    public OpenAiService(ChatClient chatModel) {
        this.chatModel = chatModel;
    }

    public String generateResponse(String userPrompt) {
        try {
            String systemInstruction = "You are a professional, polite customer service support bot. Answer this customer's prompt immediately: ";
            return chatModel.call(systemInstruction + userPrompt);
        } catch (Exception e) {
            return "Notice: System could not reach AI Engine instantly. Details: " + e.getMessage();
        }
    }
}