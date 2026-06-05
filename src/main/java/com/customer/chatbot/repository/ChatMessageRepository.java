package com.customer.chatbot.repository;

import com.customer.chatbot.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByUserUsernameOrderByTimestampAsc(String username);
    List<ChatMessage> findAllByOrderByTimestampAsc();

    // ADD THIS: Safely deletes all messages mapped to the logged-in user
    @Transactional
    void deleteByUserUsername(String username);
}