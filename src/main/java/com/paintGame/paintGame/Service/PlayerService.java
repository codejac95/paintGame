package com.paintGame.paintGame.Service;

import com.paintGame.paintGame.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void createNewPlayer(Player player) {
        List<Integer> scoreList = null;
        player = new Player(player.getUsername(), player.getPassword(), scoreList);
        mongoTemplate.save(player, "Players"); // Sparar spelaren direkt
    }

    public void deletePlayer(int playerId) {
        Player player = mongoTemplate.findById(playerId, Player.class);
        if (player != null) {
            mongoTemplate.remove(player);
        }
    }

    public void updatePlayerScore(int playerId, int newScore) {
        Player player = mongoTemplate.findById(playerId, Player.class);
        if (player != null) {
            player.getScoreList().add(newScore);
            mongoTemplate.save(player);
        }
    }

    public List<Player> getAllPlayers() {
        return mongoTemplate.findAll(Player.class);
    }

    public void loginPlayer(String username) {

        System.out.println("Logging in player: " + username);
    }

    public String getPlayerID(String username) {

        Player player = mongoTemplate.findOne(
                new Query(Criteria.where("name").is(username)), Player.class);

        return player != null ? player.getId() : "User not found";
    }
}
