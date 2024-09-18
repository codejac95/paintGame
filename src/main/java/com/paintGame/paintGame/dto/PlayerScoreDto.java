package com.paintGame.paintGame.dto;

public class PlayerScoreDto {

    private String username;
    private Double latestScore;

    public PlayerScoreDto(String username, Double latestScore) {
        this.username = username;
        this.latestScore = latestScore;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getLatestScore() {
        return latestScore;
    }

    public void setLatestScore(Double latestScore) {
        this.latestScore = latestScore;
    }
}

