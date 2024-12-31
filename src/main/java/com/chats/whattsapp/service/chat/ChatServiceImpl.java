package com.chats.whattsapp.service.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chats.whattsapp.exception.ChatException;
import com.chats.whattsapp.exception.UserException;
import com.chats.whattsapp.model.Chat;
import com.chats.whattsapp.model.User;
import com.chats.whattsapp.repository.ChatRepository;
import com.chats.whattsapp.request.GroupChatRequest;
import com.chats.whattsapp.service.user.UserService;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserService userService;

    public ChatServiceImpl(ChatRepository chatRepository, UserService userService) {
        this.chatRepository = chatRepository;
        this.userService = userService;
    }

    @Override
    public Chat createChat(User reqUser, Long userId2) throws UserException {
        User user = userService.findUserById(userId2);
        Chat isChatExist = chatRepository.findSingleChatByUserIds(user, reqUser);
        if (isChatExist != null) {
            return isChatExist;
        }
        Chat chat = new Chat();
        chat.setCreatedBy(user);
        chat.getUsers().add(reqUser);
        chat.getUsers().add(user);
        chat.setGroup(false);
        chatRepository.save(chat);
        return chat;
    }

    @Override
    public Chat findChatById(Long chatId) throws ChatException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findChatById'");
    }

    @Override
    public List<Chat> findChatsByUser(Long userId) throws UserException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findChatsByUser'");
    }

    @Override
    public Chat createGroup(GroupChatRequest req, Long reqUserId) throws UserException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createGroup'");
    }

    @Override
    public Chat addUserToGroup(Long userId, Long chatId) throws UserException, ChatException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUserToGroup'");
    }

    @Override
    public Chat renameGroup(Long chatId, String groupName, Long reqUserId) throws UserException, ChatException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renameGroup'");
    }

    @Override
    public Chat removeFromGroup(Long chatId, Long userId, Long reqUserId) throws UserException, ChatException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFromGroup'");
    }

    @Override
    public Chat deleteChat(Long chatId, Long userId) throws UserException, ChatException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteChat'");
    }

}
