package com.chats.whattsapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "chat_name")
    private String chatName;
    @Column(name = "chat_image")
    private String chatImage;
    @Column(name = "is_group")
    private boolean isGroup;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToMany
    Set<User> admins = new HashSet<User>();
    
    @ManyToMany
    Set<User> users = new HashSet<User>();

    @OneToMany
    List<Message> messages = new ArrayList<Message>();

    public Chat() {
    }

    public Chat(String chatName, String chatImage, boolean isGroup, User createdBy) {
        this.chatName = chatName;
        this.chatImage = chatImage;
        this.isGroup = isGroup;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public String getChatName() {
        return chatName;
    }

    public String getChatImage() {
        return chatImage;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public Set<User> getUsers() {
        return users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Set<User> getAdmins() {
        return admins;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public void setChatImage(String chatImage) {
        this.chatImage = chatImage;
    }

    public void setGroup(boolean isGroup) {
        this.isGroup = isGroup;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

}
