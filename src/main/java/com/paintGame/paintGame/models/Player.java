package com.paintGame.paintGame.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.CrossOrigin;

@Document(collection = "Players")
@CrossOrigin(origins = "*")
public class Player {

    @Id
    private String id;
    private String username;
    private String password;
    private List<Integer> scoreList;

    public Player() {

    }

    public Player(String username, String password, List<Integer> scoreList) {
        this.username = username;
        this.password = password;
        this.scoreList = new ArrayList<>();
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

    public List<Integer> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Integer> scoreList) {
        this.scoreList = scoreList;
    }

}
