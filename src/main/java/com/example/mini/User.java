package com.example.mini;

// När appen startar skapar vi upp ett User-objekt per person som finns i databasen
public class User {
    private int userId;
    private String nickName;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private String description;
    private String imgUrl;
    private boolean online = false;

    public User(){} // Empty constructor

    public User(String nickName, String password, String imgUrl) {
        this.nickName = nickName;
        this.password = password;
        this.imgUrl = imgUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
