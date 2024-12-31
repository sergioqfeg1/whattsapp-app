package com.chats.whattsapp.request;

public class LogInRequest {

    private String email;
    private String password;

    public LogInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
