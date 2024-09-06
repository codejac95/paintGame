package com.paintGame.paintGame.Controlers;

import java.util.ArrayList;
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
        if (existingPlayer != null) { // If player exists
            return "User already exists";
        } else { // If player doesn't exist
            return playerService.createNewPlayer(player);
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


