package com.paintGame.paintGame.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.CrossOrigin;

@Document (collection = "Game")
@CrossOrigin(origins = "*")
public class Game {

    @Id
    private String id;
    private String playerId;
    private boolean gameState;
    private byte[] mainPicture;
    private byte[] playerPicture1;
    private byte[] playerPicture2;
    private byte[] playerPicture3;
    private byte[] playerPicture4;

    public Game() {
    }

    public Game(String id, String playerId, boolean gameState, byte[] mainPicture, byte[] playerPicture1, byte[] playerPicture2,
            byte[] playerPicture3, byte[] playerPicture4) {
        this.id = UUID.randomUUID().toString();
        this.playerId = playerId;
        this.gameState = gameState;
        this.mainPicture = mainPicture;
        this.playerPicture1 = playerPicture1;
        this.playerPicture2 = playerPicture2;
        this.playerPicture3 = playerPicture3;
        this.playerPicture4 = playerPicture4;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public boolean isGameState() {
        return gameState;
    }

    public void setGameState(boolean gameState) {
        this.gameState = gameState;
    }

    public byte[] getMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(byte[] mainPicture) {
        this.mainPicture = mainPicture;
    }

    public byte[] getPlayerPicture1() {
        return playerPicture1;
    }

    public void setPlayerPicture1(byte[] playerPicture1) {
        this.playerPicture1 = playerPicture1;
    }

    public byte[] getPlayerPicture2() {
        return playerPicture2;
    }

    public void setPlayerPicture2(byte[] playerPicture2) {
        this.playerPicture2 = playerPicture2;
    }

    public byte[] getPlayerPicture3() {
        return playerPicture3;
    }

    public void setPlayerPicture3(byte[] playerPicture3) {
        this.playerPicture3 = playerPicture3;
    }

    public byte[] getPlayerPicture4() {
        return playerPicture4;
    }

    public void setPlayerPicture4(byte[] playerPicture4) {
        this.playerPicture4 = playerPicture4;
    }

    
    
}
