package com.chats.whattsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.chats.whattsapp.exception.UserException;
import com.chats.whattsapp.model.User;
import com.chats.whattsapp.request.LogInRequest;
import com.chats.whattsapp.response.AuthResponse;
import com.chats.whattsapp.service.token.TokenProvider;
import com.chats.whattsapp.service.user.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenProvider tokenProvider;

    public AuthController(UserService userService, 
            PasswordEncoder passwordEncoder, 
            TokenProvider tokenProvider) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(@RequestBody User user) throws UserException {
        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();

        User isUser = userService.findUserByEmail(email);
        if (isUser != null) {
            throw new UserException("This email is already used by another user");
        }

        User created = new User();
        created.setEmail(email);
        created.setPassword(passwordEncoder.encode(password));
        created.setFullName(fullName);
        userService.saveUser(created);

        Authentication auth = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = tokenProvider.generateToken(auth);

        AuthResponse res = new AuthResponse(jwt, true);
        return new ResponseEntity<AuthResponse>(res, HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LogInRequest req) {
        String email = req.getEmail();
        String password = req.getPassword();

        Authentication auth = userService.authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = tokenProvider.generateToken(auth);

        AuthResponse res = new AuthResponse(jwt, true);
        return new ResponseEntity<AuthResponse>(res, HttpStatus.ACCEPTED);
    }

}
