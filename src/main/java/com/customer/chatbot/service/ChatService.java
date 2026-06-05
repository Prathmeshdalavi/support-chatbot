package com.customer.chatbot.service;

import com.customer.chatbot.model.ChatMessage;
import com.customer.chatbot.model.User;
import com.customer.chatbot.repository.ChatMessageRepository;
import com.customer.chatbot.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    public ChatService(ChatMessageRepository chatMessageRepository, UserRepository userRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.userRepository = userRepository;
    }

    public ChatMessage saveMessage(String username, String content, String sender) {
        User user = userRepository.findByUsername(username).orElse(null);

        // Safe, standard Java initialization that JPA loves
        ChatMessage message = new ChatMessage();
        message.setContent(content);
        message.setSender(sender);
        message.setTimestamp(LocalDateTime.now());
        message.setUser(user);

        return chatMessageRepository.save(message);
    }

    public List<ChatMessage> getChatHistory(String username) {
        return chatMessageRepository.findByUserUsernameOrderByTimestampAsc(username);
    }

    public List<ChatMessage> getAllConversations() {
        return chatMessageRepository.findAllByOrderByTimestampAsc();
    }

    public void clearChatHistory(String username) {
        chatMessageRepository.deleteByUserUsername(username);
    }
}