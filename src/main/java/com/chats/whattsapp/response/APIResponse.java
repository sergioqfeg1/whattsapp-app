package com.chats.whattsapp.response;

public class APIResponse {

    private String message;
    private boolean status;

    public APIResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

}
