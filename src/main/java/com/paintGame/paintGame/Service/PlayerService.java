package com.paintGame.paintGame.Service;

import com.paintGame.paintGame.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Player createNewPlayer(Player player) {
        List<Double> scoreList = new ArrayList<>();
        player = new Player(player.getUsername(), player.getPassword(), scoreList);
        player.setLoggedIn(true);
        mongoTemplate.save(player, "Players");
        return player;
    }

    public void deletePlayer(String playerId) {
        Player player = mongoTemplate.findById(playerId, Player.class);
        if (player != null) {
            mongoTemplate.remove(player);
        }
    }

    public Player updatePlayerScore(String playerId, double newScore) {
        Player player = mongoTemplate.findById(playerId, Player.class);
        if (player != null) {
            player.getScoreList().add(newScore);
            return mongoTemplate.save(player);
        }
        return null;
    }

    public List<Player> getAllPlayers() {
        return mongoTemplate.findAll(Player.class);
    }

    public Player loginPlayer(Player player) {
        Query query = Query
                .query(Criteria.where("username").is(player.getUsername()).and("password").is(player.getPassword()));
        Player existingPlayer = mongoTemplate.findOne(query, Player.class);
        if (existingPlayer != null && !existingPlayer.isLoggedIn()) {
            existingPlayer.setLoggedIn(true);
            mongoTemplate.save(existingPlayer);
            return existingPlayer;
        } else {
            return null;
        }
    }

    public void logoutPlayer(Player player) {
        Query query = Query.query(Criteria.where("username").is(player.getUsername()));
        Player existingPlayer = mongoTemplate.findOne(query, Player.class);
        if (existingPlayer != null) {
            existingPlayer.setLoggedIn(false);
            mongoTemplate.save(existingPlayer);
        }
    }

    public Player getUsername(String username) {
        Player player = mongoTemplate.findOne(new Query(Criteria.where("username").is(username)), Player.class);
        return player;
    }
}
