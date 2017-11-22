package com.example.mini;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class PageController {
    
    private Repository repository = new Repository();
    private HashMap<String, User> userList = repository.getUserList();
    
    @GetMapping("/login")
    public ModelAndView chat(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return new ModelAndView("chat")
                    .addObject("username", session.getAttribute("username"));
        } else {
            return new ModelAndView("login");
        }
    }
    
    @PostMapping("/login")
    public ModelAndView login(@RequestParam String username, String password, HttpSession session) {
        
        User user = userList.get(username);
        
        // Om sername inte finns bland inladdade users från MSSQL
        if (user == null) {
            return chat(session); // tillbaka till login
            
        } else {
            
            // Om lösenordet matchar
            if (password.equals(user.getPassword())) {
                session.setAttribute("username", username);
            }
            
            return chat(session); // vidare till chatt
        }
    }
}