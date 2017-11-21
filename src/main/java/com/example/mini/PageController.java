package com.example.mini;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @GetMapping("/chat")
    public ModelAndView chat(HttpSession session){
        if(session.getAttribute("name") != null){
            return new ModelAndView("chat");
        }
        else{
            return new ModelAndView("login");
        }
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String user, String password, HttpSession session){
        if(user.equals("admin") && password.equals("123")){
            return new ModelAndView("chat").addObject("user", user);
        }
        else{
            return new ModelAndView("login").addObject("user", user);
        }
    }
}
