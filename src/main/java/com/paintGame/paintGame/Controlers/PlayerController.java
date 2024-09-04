package com.paintGame.paintGame.Controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.paintGame.paintGame.Player;
import com.paintGame.paintGame.Service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/create")
    public void createNewPlayer(@RequestBody PlayerRequest playerRequest) {
        playerService.createNewPlayer(playerRequest.getPlayerName());
    }

    @DeleteMapping("/delete/{playerId}")
    public void deletePlayer(@PathVariable int playerId) {
        playerService.deletePlayer(playerId);
    }

    @PutMapping("/update/{playerId}")
    public void updatePlayerScore(@PathVariable int playerId, @RequestBody UpdateScoreRequest updateScoreRequest) {
        playerService.updatePlayerScore(playerId, updateScoreRequest.getNewScore());
    }

    @PostMapping("/login")
    public void loginPlayer(@RequestParam String username) {
        playerService.loginPlayer(username);
    }

    @GetMapping("/getId/{username}")
    public int getPlayerID(@PathVariable String username) {
        return playerService.getPlayerID(username);
    }

    @GetMapping("/all")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    public static class UpdateScoreRequest {
        private int newScore;

        public int getNewScore() {
            return newScore;
        }

        public void setNewScore(int newScore) {
            this.newScore = newScore;
        }
    }
}

class PlayerRequest {
    private String playerName;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
