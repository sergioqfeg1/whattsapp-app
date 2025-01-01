package com.chats.whattsapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chats.whattsapp.model.Chat;
import com.chats.whattsapp.model.User;

public interface ChatRepository extends JpaRepository<Chat, Long>{

    @Query("SELECT c FROM Chat c JOIN c.users u WHERE u.id = ?1")
    List<Chat> findChatByUserId(Long userId);

    @Query("SELECT c FROM Chat c WHERE c.isGroup = false AND ?1 MEMBER OF c.users AND ?2 MEMBER OF c.users")
    Chat findSingleChatByUserIds(User user, User reqUser);

}
