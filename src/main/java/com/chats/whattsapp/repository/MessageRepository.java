package com.chats.whattsapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chats.whattsapp.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m JOIN m.chat c WHERE c.id = ?1")
    List<Message> findByChatId(Long chatId);

}
