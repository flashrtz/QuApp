package com.example.quoteapp.Model;

public class Band {


    private String imageUrl;
    private String category;

public Band(){}
    public Band(String imageUrl, String category) {
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public Band(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
