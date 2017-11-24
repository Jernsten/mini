package com.example.mini;

public class Message {
    
    private String message;
    private String username;
    private String imgUrl;
    
    public Message() {
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    public Message(String message) {
        this.message = message;
    }
    
    public Message(String message, String username) {
        this.message = message;
        this.username = username;
    }
    
    public Message(String message, String username, String imgUrl) {
        this.message = message;
        this.username = username;
        this.imgUrl = imgUrl;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getMessage() {
        return message;
    }
    
    public String getImgUrl() {
        return imgUrl;
    }
}
