package com.paintGame.paintGame.Controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paintGame.paintGame.Service.GameService;

import java.util.List;

@RestController
// @CrossOrigin("https://seashell-app-ia2eg.ondigitalocean.app")
@CrossOrigin("*")
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public GameController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/assignSquare")
    public void assignSquare(int squareId) {
        gameService.assignSquare(squareId);
        messagingTemplate.convertAndSend("/topic/occupiedSquares", gameService.getOccupiedSquares());
    }
    

    @MessageMapping("/resetSquares")
    public void resetSquares() {
        gameService.resetSquares();
        messagingTemplate.convertAndSend("/topic/occupiedSquares", gameService.getOccupiedSquares());
    }

    @MessageMapping("/freeSquare")
    public void freeSquare(int squareId) {
        gameService.freeSquare(squareId);
        messagingTemplate.convertAndSend("/topic/occupiedSquares", gameService.getOccupiedSquares());
    }
    
}
