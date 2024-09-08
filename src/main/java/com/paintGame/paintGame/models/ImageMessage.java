package com.paintGame.paintGame.models;

public class ImageMessage {
    private String image;
    private String action;

    public ImageMessage() {
    }

    public ImageMessage(String image, String action) {
        this.image = image;
        this.action = action;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    

}
