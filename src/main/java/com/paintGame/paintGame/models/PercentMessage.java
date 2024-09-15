package com.paintGame.paintGame.models;

public class PercentMessage {
    
    private String playerName;
    private double percent;

    public PercentMessage() {}

    public PercentMessage(String playerName, double percent) {
        this.playerName = playerName;
        this.percent = percent;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    
    
}
