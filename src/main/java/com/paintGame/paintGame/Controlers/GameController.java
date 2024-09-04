package com.paintGame.paintGame.Controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paintGame.paintGame.Service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/start")
    public void startGame() {
        gameService.startGame();
    }

    @PostMapping("/end")
    public void endGame() {
        gameService.endGame();
    }

    @GetMapping("/getPicture")
    public void getPicture() {
        gameService.getPicture();
    }
}
