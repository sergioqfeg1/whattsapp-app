package com.chats.whattsapp.service.chat;

import java.util.List;

import com.chats.whattsapp.exception.ChatException;
import com.chats.whattsapp.exception.UserException;
import com.chats.whattsapp.model.Chat;
import com.chats.whattsapp.model.User;
import com.chats.whattsapp.request.GroupChatRequest;

public interface ChatService {

    Chat createChat(User reqUser, Long userId2) throws UserException;
    Chat findChatById(Long chatId) throws ChatException;
    List<Chat> findChatsByUser(Long userId) throws UserException;
    Chat createGroup(GroupChatRequest req, Long reqUserId) throws UserException;
    Chat addUserToGroup(Long userId, Long chatId) throws UserException, ChatException;
    Chat renameGroup(Long chatId, String groupName, Long reqUserId) throws UserException, ChatException;
    Chat removeFromGroup(Long chatId, Long userId, Long reqUserId) throws UserException, ChatException;
    Chat deleteChat(Long chatId, Long userId) throws UserException, ChatException;

}
