package com.example.shauryatrivedi.metoo.Retrofit;

public class TweetList {

    private String id, image;

    public TweetList(String id, String image) {

        this.id = id;
        this.image = image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

}
