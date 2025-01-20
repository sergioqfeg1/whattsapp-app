package com.chats.whattsapp.service.message;

import java.util.List;

import com.chats.whattsapp.exception.ChatException;
import com.chats.whattsapp.exception.MessageException;
import com.chats.whattsapp.exception.UserException;
import com.chats.whattsapp.model.Message;
import com.chats.whattsapp.model.User;
import com.chats.whattsapp.request.SendMessageRequest;

public interface MessageService {

    Message sendMessage(SendMessageRequest req) throws UserException, ChatException;
    List<Message> getChatsMessages(Long chatId, User reqUser) throws ChatException, UserException;
    Message findMessageById(Long messageId) throws MessageException;
    void deleteMessage(Long messageId, User reqUser) throws MessageException;

}
