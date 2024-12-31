package com.chats.whattsapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private LocalDateTime timestamp;
    @ManyToOne
    private User user;
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    public Message() {
    }

    public Message(String content, LocalDateTime timestamp, User user, Chat chat) {
        this.content = content;
        this.timestamp = timestamp;
        this.user = user;
        this.chat = chat;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public User getUser() {
        return user;
    }

    public Chat getChat() {
        return chat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

}
