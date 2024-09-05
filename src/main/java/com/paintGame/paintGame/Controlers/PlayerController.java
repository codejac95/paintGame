package com.paintGame.paintGame.Controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.paintGame.paintGame.models.Player;
import com.paintGame.paintGame.Service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/create")
    public String createNewPlayer(@RequestBody Player player) {
        System.out.println("/player/create");
        try {
            Player existingPlayer = playerService.getUsername(player.getUsername());
            if (existingPlayer.getUsername().equals(null)) {

                return playerService.createNewPlayer(player);
            } else {
                return "User already exists";
            }
        } catch (Exception e) {
            return "Something went wrong";
        }
    }

    @DeleteMapping("/delete/{playerId}")
    public void deletePlayer(@PathVariable String playerId) {
        playerService.deletePlayer(playerId);
    }

    @PutMapping("/update/{playerId}")
    public void updatePlayerScore(@PathVariable String playerId, @RequestBody UpdateScoreRequest updateScoreRequest) {
        playerService.updatePlayerScore(playerId, updateScoreRequest.getNewScore());
    }

    @PostMapping("/login")
    public String loginPlayer(@RequestBody Player player) {

        try {
            Player existingPlayer = playerService.getUsername(player.getUsername());
            if (existingPlayer.getUsername() != null
                    && existingPlayer.getPassword().equals(existingPlayer.getPassword())) {
                return existingPlayer.getUsername();
            } else {
                System.out.println("Wrong password");
                return "Wrong password";
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
            return "Something went wrong";
        }

    }

    @GetMapping("/getId/{username}")
    public String getUsername(@RequestBody String username) {
        return playerService.getUsername(username).getId();
    }

    @GetMapping("/getAll")
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
