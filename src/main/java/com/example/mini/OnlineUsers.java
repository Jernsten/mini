package com.example.mini;

import java.util.HashMap;

public class OnlineUsers {
    private HashMap<String, User> onlineUsers = new HashMap<>();
    
    public void addUser(User user) {
        onlineUsers.put(user.getNickName(), user);
    }
    
    public HashMap<String, User> getOnlineUsers() {
        return onlineUsers;
    }
}