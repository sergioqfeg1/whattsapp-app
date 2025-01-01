package com.chats.whattsapp.request;

public class SingleChatRequest {

    private Long userId;

    public SingleChatRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
