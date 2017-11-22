package com.example.mini;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    private List<Message> loadOldMessages() {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT TOP (10) (\n" +
                "\tSELECT NickName\n" +
                "\tFROM Academy_Projekt4.dbo.Students AS StudentInfo\n" +
                "\tWHERE StudentInfo.ID = MessageInfo.StudentID\n" +
                "\t) AS Name,\n" +
                "    [Message],\n" +
                "    [Time]\n" +
                "  FROM [Academy_Projekt4].[dbo].[Messages] AS MessageInfo" +
                "  ORDER BY Time";

        try(Connection conn = dataSource.getConnection();
            Statement st = conn.createStatement()){

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                String name = rs.getString("Name");
                String message = rs.getString("Message");
                Message m = new Message(message, name);
                messages.add(m);
            }
        }catch(SQLException e){
            // Gör något här Thommy! <3
        }

        return messages;
    }
}
