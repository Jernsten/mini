package com.example.mini;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/incoming/messages")
    public ReturnMessage message(Message message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new ReturnMessage(message.getMessage());
    }
}
