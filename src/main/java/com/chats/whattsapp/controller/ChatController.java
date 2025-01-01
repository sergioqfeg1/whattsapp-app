package com.chats.whattsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chats.whattsapp.exception.UserException;
import com.chats.whattsapp.model.Chat;
import com.chats.whattsapp.model.User;
import com.chats.whattsapp.request.SingleChatRequest;
import com.chats.whattsapp.service.chat.ChatService;
import com.chats.whattsapp.service.user.UserService;

@RestController
@RequestMapping("/api/v1/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;

    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    @PostMapping("/single")
    public ResponseEntity<Chat> createChatHandler(@RequestBody SingleChatRequest singleChatRequest, @RequestHeader("Authorization") String jwt) throws UserException {
        User reqUser = userService.findUserProfile(jwt);
        Chat chat = chatService.createChat(reqUser, singleChatRequest.getUserId());
        return new ResponseEntity<Chat>(chat, HttpStatus.OK);
    }


}
