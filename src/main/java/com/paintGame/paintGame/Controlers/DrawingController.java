package com.paintGame.paintGame.Controlers;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.paintGame.paintGame.models.DrawMessage;

@Controller
public class DrawingController {

    private final SimpMessagingTemplate messagingTemplate;

    public DrawingController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

     @MessageMapping("/draw")
     @SendTo("/topic/drawings") 
     public DrawMessage broadcastDrawing(DrawMessage drawMeassage) {
        return drawMeassage; 
     }

     @MessageMapping("/drawHandle")
     public void handleDrawing(@Payload DrawMessage drawMeassage) {
        messagingTemplate.convertAndSend("/topic/drawings", drawMeassage);
     }

}
