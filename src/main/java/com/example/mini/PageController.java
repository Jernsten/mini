package com.example.mini;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class PageController {
    
    @Autowired
    private Repository repository;

    @GetMapping("/logout")
    public String logout(HttpSession httpSession, HttpServletResponse res) {
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        httpSession.invalidate();
        return "redirect:/";
    }
    @GetMapping("/")
    public ModelAndView chat(HttpSession session) {
        if (session.getAttribute("username") != null) {
            List<Message> messageList = repository.loadOldMessages(); // Contains messages from database
            
            return new ModelAndView("chat")
                    .addObject("oldmessages", messageList)
                    .addObject("username", session.getAttribute("username"));
        } else {
            return new ModelAndView("login");
        }
    }
    
    @PostMapping("/")
    public ModelAndView login(@RequestParam String username, String password, HttpSession session) {
        
        User user = repository.loadUser(username);
        
        // Om username inte finns bland inladdade users från MSSQL
        if (user == null) {
            System.out.println("null");
            return chat(session); // tillbaka till login
            
        } else {
            
            // Om lösenordet matchar
            if (BCrypt.checkpw(password, user.getPassword())) {
                session.setAttribute("username", username);
            }
            
            return chat(session); // vidare till chatt
        }
    }
}