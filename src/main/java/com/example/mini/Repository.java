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

    public List<Message> loadOldMessages() {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT *\n" +
                "FROM (\t\n" +
                "\tSELECT TOP 10 (\n" +
                "\t\tSELECT NickName\n" +
                "\t\tFROM Academy_Projekt4.dbo.Students AS StudentInfo\n" +
                "\t\tWHERE StudentInfo.ID = MessageInfo.StudentID\n" +
                "\t\t) AS Name,\n" +
                "\t\t[Message],\n" +
                "\t\t[Time]\n" +
                "\tFROM [Academy_Projekt4].[dbo].[Messages] AS MessageInfo\n" +
                "\tORDER BY ID DESC\n" +
                "\t) AS TopTenNewMessages\n" +
                "ORDER BY TopTenNewMessages.Time ASC";

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
            // Gör något här
        }

        return messages;
    }

    public User loadUserProfileInfo(String username){
        User user = new User();
        String sql = "SELECT S.ID AS AccountID, S.NickName, P.FirstName, P.LastName, P.Age, P.[Description], S.ImgUrl\n" +
                "FROM Students AS S, (\n" +
                "\tSELECT NickName, FirstName, LastName, Age, [Description]\n" +
                "\tFROM Profiles\n" +
                "\t) AS P\n" +
                "WHERE S.NickName = P.NickName AND S.NickName = ?";



        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            rs.next(); // Jump to first and only row
            int userId = rs.getInt("AccountID");
            String nickName = rs.getString("NickName");
            String firstName = rs.getString("FirstName");
            String lastName = rs.getString("LastName");
            int age = rs.getInt("Age");
            String description = rs.getString("Description");
            String imgUrl = rs.getString("ImgUrl");

            // Set user information to be shown in profile
            user.setUserId(userId);
            user.setNickName(nickName);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAge(age);
            user.setDescription(description);
            user.setImgUrl(imgUrl);

        }catch (SQLException e){
            return null;
        }

        return user;
    }
}
