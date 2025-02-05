package com.paintGame.paintGame.Controlers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.paintGame.paintGame.models.DrawMessage;
import com.paintGame.paintGame.models.ImageMessage;
import com.paintGame.paintGame.models.PercentMessage;

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

    @MessageMapping("/showComponent")
    @SendTo("/topic/showImage")
    public ImageMessage broadcastShowImage() {
        return new ImageMessage(null, "showImage");
    }

    @MessageMapping("/broadcastImage")
    @SendTo("/topic/showImage")
    public ImageMessage broadcastImage(ImageMessage imageMessage) {
        return new ImageMessage(imageMessage.getImage(), "startCountdown");
    }

    @MessageMapping("/countdownEnded")
    public void handleCountdownEnded() {
        messagingTemplate.convertAndSend("/topic/showImage", "{\"action\":\"countdownEnded\"}");
    }

    @MessageMapping("/percentMatch")
    @SendTo("/topic/percent")
    public PercentMessage processPercentMatch(PercentMessage message, SimpMessageHeaderAccessor headerAccessor) {

        return message;
    }

    @MessageMapping("/countdownStartedDraw")
    public void handleCountdownStarted() {
        messagingTemplate.convertAndSend("/topic/drawingCountdown", "{\"action\":\"startCountdownDraw\"}");
    }

    @MessageMapping("/countdownEndedDraw")
    public void handleCountdownEndedDraw() {
        messagingTemplate.convertAndSend("/topic/drawingCountdown", "{\"action\":\"countdownEndedDraw\"}");
    }

}
