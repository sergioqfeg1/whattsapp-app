package com.chats.whattsapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chats.whattsapp.exception.UserException;
import com.chats.whattsapp.model.User;
import com.chats.whattsapp.request.UpdateUserRequest;
import com.chats.whattsapp.response.APIResponse;
import com.chats.whattsapp.service.user.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfile(jwt);
        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{query}")
    public ResponseEntity<List<User>> searchUserHandler(@PathVariable String query) {
        List<User> users = userService.searchUser(query);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse> updateUserHandler(@RequestBody UpdateUserRequest req, @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfile(jwt);
        userService.updateUser(user.getId(), req);
        APIResponse res = new APIResponse("User updated successfully", true);
        return new ResponseEntity<APIResponse>(res, HttpStatus.ACCEPTED);
    }

}
