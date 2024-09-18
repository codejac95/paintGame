package com.paintGame.paintGame.Controlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.paintGame.paintGame.models.Player;
import com.paintGame.paintGame.Service.PlayerService;
import com.paintGame.paintGame.dto.PlayerScoreDto;

@RestController
// @CrossOrigin("https://seashell-app-ia2eg.ondigitalocean.app")
@CrossOrigin("*")
@RequestMapping("/player")

public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/create")
    public Player createNewPlayer(@RequestBody Player player) {
        System.out.println("/player/create");
        Player existingPlayer = playerService.getUsername(player.getUsername());
        if (existingPlayer == null) {
            return playerService.createNewPlayer(player);
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete/{playerId}")
    public void deletePlayer(@PathVariable String playerId) {
        playerService.deletePlayer(playerId);
    }

    @PutMapping("/update/{playerId}")
    public Player updatePlayerScore(@PathVariable String playerId, @RequestBody UpdateScoreRequest updateScoreRequest) {
        System.out.println("update: newScore = " + updateScoreRequest.getNewScore());
        Player updatedPlayer = playerService.updatePlayerScore(playerId, updateScoreRequest.getNewScore());
        return updatedPlayer;
    }

    @PostMapping("/login")
    public Player loginPlayer(@RequestBody Player player) {
        Player loggedInPlayer = playerService.loginPlayer(player);
        if (loggedInPlayer != null) {
            loggedInPlayer.setPassword(""); 
            return loggedInPlayer;
        } else {
            return new Player();
        }
    }

    @PostMapping("/logout")
    public void logoutPlayer(@RequestBody Player player) {
            playerService.logoutPlayer(player);
        }
    

    @GetMapping("/getId/{username}")
    public String getUsername(@PathVariable String username) {
        Player player = playerService.getUsername(username);
        if (player != null) {
            return player.getId();
        } else {
            return "User not found";
        }
    }

    @GetMapping("/getAll")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/averageScorePerPlayer")
    public List<PlayerAverageScore> getAverageScorePerPlayer() {
        List<Player> allPlayers = playerService.getAllPlayers();

        List<PlayerAverageScore> playerAverages = new ArrayList<>();

        for (Player player : allPlayers) {
            List<Double> scores = player.getScoreList();
            if (!scores.isEmpty()) {
                double average = scores.stream().mapToInt(Double::intValue).average().orElse(0.0);
                playerAverages.add(new PlayerAverageScore(player.getUsername(), average));
            } else {
                playerAverages.add(new PlayerAverageScore(player.getUsername(), 0.0));
            }
        }

        return playerAverages;
    }

    @GetMapping("/loggedinPlayersScores")
    public List<PlayerScoreDto> getLoggedInPlayersWithLatestScore() {
        return playerService.getLoggedInPlayersWithLatestScore();
    }

    public static class UpdateScoreRequest {
        private double newScore;

        public double getNewScore() {
            return newScore;
        }

        public void setNewScore(double newScore) {
            this.newScore = newScore;
        }
    }

    public static class PlayerAverageScore {
        private String username;
        private double averageScore;

        public PlayerAverageScore(String username, double averageScore) {
            this.username = username;
            this.averageScore = averageScore;
        }

        public String getUsername() {
            return username;
        }

        public double getAverageScore() {
            return averageScore;
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
