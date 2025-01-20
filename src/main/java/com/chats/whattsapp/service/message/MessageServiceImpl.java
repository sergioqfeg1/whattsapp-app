package com.chats.whattsapp.service.message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chats.whattsapp.exception.ChatException;
import com.chats.whattsapp.exception.MessageException;
import com.chats.whattsapp.exception.UserException;
import com.chats.whattsapp.model.Chat;
import com.chats.whattsapp.model.Message;
import com.chats.whattsapp.model.User;
import com.chats.whattsapp.repository.MessageRepository;
import com.chats.whattsapp.request.SendMessageRequest;
import com.chats.whattsapp.service.chat.ChatService;
import com.chats.whattsapp.service.user.UserService;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;

    public MessageServiceImpl(MessageRepository messageRepository, UserService userService, ChatService chatService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.chatService = chatService;
    }

    @Override
    public Message sendMessage(SendMessageRequest req) throws UserException, ChatException {
        User user = userService.findUserById(req.getUserId());
        Chat chat = chatService.findChatById(req.getChatId());

        Message message = new Message();
        message.setUser(user);
        message.setChat(chat);
        message.setTimestamp(LocalDateTime.now());
        message.setContent(req.getContent());

        return message;
    }

    @Override
    public List<Message> getChatsMessages(Long chatId, User reqUser) throws ChatException, UserException {
        Chat chat = chatService.findChatById(chatId);
        if (!chat.getUsers().contains(reqUser))
            throw new UserException("You are not a member of this chat");
        List<Message> messages = messageRepository.findByChatId(chat.getId());
        return messages;
    }

    @Override
    public Message findMessageById(Long messageId) throws MessageException {
        Optional<Message> opt = messageRepository.findById(messageId);
        if (opt.isPresent())
            return opt.get();
        throw new MessageException("The message is not present");
    }

    @Override
    public void deleteMessage(Long messageId, User reqUser) throws MessageException {
        Message message = findMessageById(messageId);
        if (message.getUser().equals(reqUser))
            messageRepository.delete(message);
        throw new MessageException("You are not allowed to delete a message you didn't send");
    }

}
