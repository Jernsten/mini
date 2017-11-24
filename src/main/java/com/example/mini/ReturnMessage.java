package com.example.mini;

public class ReturnMessage {
    private String content;
    private String username;
    private String imgUrl;
    
    public ReturnMessage(String content, String username, String imgUrl) {
        this.content = content;
        this.username = username;
        this.imgUrl = imgUrl;
    }
    
    public ReturnMessage() {
    }
    
    public ReturnMessage(String content) {
        this.content = content;
    }
    
    public ReturnMessage(String content, String username) {
        this.content = content;
        this.username = username;
    }
    
    public String getImgUrl() {
        return imgUrl;
    }
    
    public String getContent() {
        return content;
    }
    
    public String getUsername() {
        return username;
    }
}
