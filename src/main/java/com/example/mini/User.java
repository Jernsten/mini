package com.example.mini;

// När appen startar skapar vi upp ett User-objekt per person som finns i databasen
public class User {
    private String nickName;
    private String password;
    private String imgUrl;
    private boolean online = false;
    
    public User(String nickName, String password, String imgUrl) {
        this.nickName = nickName;
        this.password = password;
        this.imgUrl = imgUrl;
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public boolean isOnline() {
        return online;
    }
    
    public void online(boolean online) {
        this.online = online;
    }
}
