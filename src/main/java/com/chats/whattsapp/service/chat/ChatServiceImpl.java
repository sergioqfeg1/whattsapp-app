package com.chats.whattsapp.service.chat;

import java.util.List;
import java.util.Optional;

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
        Optional<Chat> chat = chatRepository.findById(chatId);
        if (chat.isPresent()) {
            return chat.get();
        }
        throw new ChatException("Chat not found");
    }

    @Override
    public List<Chat> findChatsByUser(Long userId) throws UserException {
        User user = userService.findUserById(userId);
        return chatRepository.findChatByUserId(user.getId());
    }

    @Override
    public Chat createGroup(GroupChatRequest req, User reqUser) throws UserException {
        Chat group = new Chat();
        group.setGroup(true);
        group.setCreatedBy(reqUser);
        group.setChatImage(req.getChatImage());
        group.setChatName(req.getChatName());
        group.getAdmins().add(reqUser);
        for (Long userId : req.getUserIds()) {
            User user = userService.findUserById(userId);
            group.getUsers().add(user);
        }
        return chatRepository.save(group);
    }

    @Override
    public Chat addUserToGroup(Long userId, Long chatId, User reqUser) throws UserException, ChatException {
        Chat chat = findChatById(chatId);
        User user = userService.findUserById(userId);
        if (chat.getAdmins().contains(reqUser) && !chat.getUsers().contains(user))
            chat.getUsers().add(user);
        else
            throw new UserException("You aren't authorized to add this user");
        return chatRepository.save(chat);
    }

    @Override
    public Chat renameGroup(Long chatId, String groupName, User reqUserId) throws UserException, ChatException {
        Chat chat = findChatById(chatId);
        if (chat.getUsers().contains(reqUserId)) {
            chat.setChatName(groupName);
            return chatRepository.save(chat);
        }
        throw new UserException("You aren't authorized to rename this group");
    }

    @Override
    public Chat removeFromGroup(Long chatId, Long userId, User reqUser) throws UserException, ChatException {
        Chat chat = findChatById(chatId);
        User user = userService.findUserById(userId);
        if (chat.getAdmins().contains(reqUser) && chat.getUsers().contains(user))
            chat.getUsers().remove(user);
        else
            throw new UserException("You aren't authorized to remove this user");
        return chatRepository.save(chat);
    }

    @Override
    public void deleteChat(Long chatId) throws UserException, ChatException {
        Chat chat = findChatById(chatId);
        chatRepository.delete(chat);
    }

}
