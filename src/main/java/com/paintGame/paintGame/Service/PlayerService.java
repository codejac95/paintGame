package com.paintGame.paintGame.Service;

import com.paintGame.paintGame.Player;
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

    private int nextId = 1; // Variabel för att generera unika ID:n

    public void createNewPlayer(String playerName) {
        Player player = new Player(nextId++, playerName, 0);
        mongoTemplate.save(player, "players"); // Sparar spelaren direkt
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
            player.setScore(newScore);
            mongoTemplate.save(player);
        }
    }

    public List<Player> getAllPlayers() {
        return mongoTemplate.findAll(Player.class);
    }

    public void loginPlayer(String username) {

        System.out.println("Logging in player: " + username);
    }

    public int getPlayerID(String username) {

        Player player = mongoTemplate.findOne(
                new Query(Criteria.where("name").is(username)), Player.class);

        return player != null ? player.getId() : -1;
    }
}
