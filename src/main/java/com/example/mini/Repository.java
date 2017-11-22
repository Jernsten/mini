package com.example.mini;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;

@Component
public class Repository {
    
    @Autowired
    DataSource dataSource;
    
    public User loadUser(String username) {
        
        String sql = "SELECT * FROM Academy_Projekt4.dbo.Students WHERE NickName = ?";
        User user = null;
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Academy_Projekt4.dbo.Students WHERE NickName = ?")) {
            
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                String nickName = rs.getString("NickName");
                
                System.out.println(nickName + " signed in");
                
                String password = rs.getString("Password");
                String imgUrl = rs.getString("ImgUrl");
                user = new User(nickName, password, imgUrl);
                user.online(true);
            }
            conn.close(); // Vill vi det?
        } catch (SQLException e) {
            // Gör något med exception
        }
        
        return user;
    }
    
    
    public HashMap<String, User> loadUsers() {
        
        HashMap<String, User> userList = new HashMap<>();
        
        String sql = "SELECT * FROM Academy_Projekt4.dbo.Students";
        
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                
                String username = rs.getString("NickName");
                //Kontroll
                System.out.println(username);
                String password = rs.getString("Password");
                String imgUrl = rs.getString("ImgUrl");
                
                User user = new User(username, password, imgUrl);
                userList.put(username, user);
            }
            
        } catch (SQLException e) {
            // Gör något med exception
        }
        return userList;
    }
}
