package com.example.mini;

public class ReturnMessage {
    private String content;
    private String username;
    
    public ReturnMessage() {
    }
    
    public ReturnMessage(String content) {
        this.content = content;
    }
    
    public ReturnMessage(String content, String username) {
        this.content = content;
        this.username = username;
    }
    
    public String getContent() {
        return content;
    }
    
    public String getUsername() {
        return username;
    }
}
