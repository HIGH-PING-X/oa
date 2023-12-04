package games.highping.server.controller;

import games.highping.server.pojo.Admin;
import games.highping.server.pojo.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleMsg(Authentication authentication, ChatMessage chatMessage){
        Admin admin = (Admin) authentication.getPrincipal();
        chatMessage.setFrom(admin.getUsername());
        chatMessage.setFromNickName(admin.getName());
        chatMessage.setDate(LocalDateTime.now());
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getTo(),"/queue/chat",chatMessage);
    }

}
