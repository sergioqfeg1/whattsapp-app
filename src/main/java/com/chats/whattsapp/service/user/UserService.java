package com.chats.whattsapp.service.user;

import java.util.List;

import com.chats.whattsapp.exception.UserException;
import com.chats.whattsapp.model.User;
import com.chats.whattsapp.request.UpdateUserRequest;

public interface UserService {

    User findUserById(Long id) throws UserException;
    User findUserProfile(String jwt) throws UserException;
    User updateUser(Long id, UpdateUserRequest req) throws UserException;
    List<User> searchUser(String query);

}
