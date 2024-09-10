package com.paintGame.paintGame.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.paintGame.paintGame.models.Player;
import com.paintGame.paintGame.Service.PlayerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/player")

public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/create")
    public String createNewPlayer(@RequestBody Player player) {
        System.out.println("/player/create");
        Player existingPlayer = playerService.getUsername(player.getUsername());
        if (existingPlayer == null) {  
            return playerService.createNewPlayer(player);
        } else {
            return "User already exists";
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
        Player existingPlayer = playerService.getUsername(player.getUsername());
            if (existingPlayer != null && existingPlayer.getPassword().equals(player.getPassword())) {
                return existingPlayer.getUsername();
            } else {
                System.out.println("Wrong username or password");
                return "Wrong username or password";
            }
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
            List<Integer> scores = player.getScoreList();
            if (!scores.isEmpty()) {
                double average = scores.stream().mapToInt(Integer::intValue).average().orElse(0.0);
                playerAverages.add(new PlayerAverageScore(player.getUsername(), average));
            } else {
                playerAverages.add(new PlayerAverageScore(player.getUsername(), 0.0));
            }
        }

        return playerAverages;
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


