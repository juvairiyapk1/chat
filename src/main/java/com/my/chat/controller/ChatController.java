package com.my.chat.controller;

import com.my.chat.modal.ChatMessage;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage){

        return chatMessage;
    }

    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ){
//        add username in websocket session
        headerAccessor.getSessionAttributes().put("username",chatMessage);
        return chatMessage;
    }
}
