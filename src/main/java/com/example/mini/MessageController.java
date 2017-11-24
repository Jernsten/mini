package com.example.mini;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Controller
public class MessageController {
    
    @Autowired
    javax.sql.DataSource dataSource; // Varför blir det såhär?
    
    @MessageMapping("/message")
    @SendTo("/incoming/messages")
    public ReturnMessage message(Message message) throws Exception {
        logMessage(message);
        
        Thread.sleep(500); // simulated delay
        
        return new ReturnMessage(message.getMessage(), message.getUsername());
    }
    
    private void logMessage(Message message) {
        
        String sql = "INSERT INTO Academy_Projekt4.dbo.Messages VALUES((SELECT ID FROM Academy_Projekt4.dbo.Students WHERE NickName = ?), ?,GETDATE())";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, message.getUsername());
            ps.setString(2, message.getMessage());
            ps.executeUpdate();
        } catch (SQLException e) {
            // Gör något med exception
        }
    }
}
