package com.example.mini;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class Repository {
    List<User> userList = new ArrayList<>();
    
    @Autowired
    DataSource dataSource;
    
    public void loadUsers() {
        String sql = "SELECT * FROM Academy_Projekt4.dbo.Students";
        
        
        try (
                Connection conn = dataSource.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String nickname = rs.getString("NickName");
                // För att testa
                System.out.println(nickname);
                String password = rs.getString("Password");
                
                //String imgUrl = rs.getString("ImgUrl");
                
                User user = new User(nickname, password, "");
                userList.add(user);
            }
        } catch (SQLException e) {
            // Gör något med exception
        }
        
    }
    
    public List<User> getUserList() {
        return userList;
    }
}
