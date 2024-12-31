package com.chats.whattsapp.request;

import java.util.List;

public class GroupChatRequest {

    private List<Long> userIds;
    private String chatName;
    private String chatImage;

    public GroupChatRequest() {
    }

    public GroupChatRequest(List<Long> userIds, String chatName, String chatImage) {
        this.userIds = userIds;
        this.chatName = chatName;
        this.chatImage = chatImage;
    }

    public List<Long> getUserIds() {
        return List.copyOf(userIds);
    }

    public String getChatName() {
        return chatName;
    }

    public String getChatImage() {
        return chatImage;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public void setChatImage(String chatImage) {
        this.chatImage = chatImage;
    }

}
