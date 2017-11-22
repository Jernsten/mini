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
    
    public HashMap<String,User> loadUsers() {
        
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
