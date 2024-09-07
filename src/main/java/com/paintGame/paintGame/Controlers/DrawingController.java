package com.paintGame.paintGame.Controlers;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.paintGame.paintGame.models.DrawMessage;
import com.paintGame.paintGame.models.ImageMessage;

@Controller
@CrossOrigin("*")
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

     
    @MessageMapping("/showImage")
     @SendTo("/topic/showImage")
     public ImageMessage broadcastImage(ImageMessage imageMessage) throws Exception {
      System.out.println("Broadcasting the image: " + imageMessage.getImage());
      return imageMessage;
     }

  
}
