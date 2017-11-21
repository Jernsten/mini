package com.example.mini;

public class Message {
    
    private String message;
    private String username;
    
    public Message() {
    }
    
    public Message(String message) {
        this.message = message;
    }
    
    public Message(String message, String username) {
        this.message = message;
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
