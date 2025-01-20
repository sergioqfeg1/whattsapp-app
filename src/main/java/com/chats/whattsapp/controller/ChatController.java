package com.chats.whattsapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chats.whattsapp.exception.ChatException;
import com.chats.whattsapp.exception.UserException;
import com.chats.whattsapp.model.Chat;
import com.chats.whattsapp.model.User;
import com.chats.whattsapp.request.GroupChatRequest;
import com.chats.whattsapp.request.SingleChatRequest;
import com.chats.whattsapp.response.APIResponse;
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

    @PostMapping("/group")
    public ResponseEntity<Chat> createGroupHandler(@RequestBody GroupChatRequest groupChatRequest, @RequestHeader("Authorization") String jwt) throws UserException {
        User reqUser = userService.findUserProfile(jwt);
        Chat chat = chatService.createGroup(groupChatRequest, reqUser);
        return new ResponseEntity<Chat>(chat, HttpStatus.OK);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> findChatByIdHandler(@PathVariable Long chatId ,@RequestHeader("Authorization") String jwt) throws ChatException {
        Chat chat = chatService.findChatById(chatId);
        return new ResponseEntity<Chat>(chat, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Chat>> findAllChatsByUserIdHandler(@RequestHeader("Authorization") String jwt) throws UserException {
        User reqUser = userService.findUserProfile(jwt);
        List<Chat> chats = chatService.findChatsByUser(reqUser.getId());
        return new ResponseEntity<List<Chat>>(chats, HttpStatus.OK);
    }

    @PutMapping("/{groupId}/add/{userId}")
    public ResponseEntity<Chat> addUserToGroupHandler(@PathVariable Long groupId, @PathVariable Long userId, @RequestBody GroupChatRequest groupChatRequest, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User reqUser = userService.findUserProfile(jwt);
        Chat chat = chatService.addUserToGroup(userId, groupId, reqUser);
        return new ResponseEntity<Chat>(chat, HttpStatus.OK);
    }

    @PutMapping("/{groupId}/remove/{userId}")
    public ResponseEntity<Chat> removeUserToGroupHandler(@PathVariable Long groupId, @PathVariable Long userId, @RequestBody GroupChatRequest groupChatRequest, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User reqUser = userService.findUserProfile(jwt);
        Chat chat = chatService.removeFromGroup(groupId, userId, reqUser);
        return new ResponseEntity<Chat>(chat, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{chatId}")
    public ResponseEntity<APIResponse> deleteChatHandler(@PathVariable Long chatId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User reqUser = userService.findUserProfile(jwt);
        chatService.deleteChat(chatId);
        APIResponse response = new APIResponse("The chat has been deleted successfully", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
