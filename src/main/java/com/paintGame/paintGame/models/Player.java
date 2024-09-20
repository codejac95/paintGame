package com.paintGame.paintGame.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.CrossOrigin;

@Document(collection = "Players")
@CrossOrigin(origins = "*")
public class Player {

    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String password;
    private List<Double> scoreList;
    private boolean isLoggedIn;

    public Player() {
    }

    public Player(String username, String password, List<Double> scoreList) {

        this.username = username;
        this.password = password;
        this.scoreList = scoreList;
    }

    public Player(String id, String username, String password, List<Double> scoreList, boolean isLoggedIn) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.scoreList = scoreList;
        this.isLoggedIn = isLoggedIn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Double> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Double> scoreList) {
        this.scoreList = scoreList;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

}