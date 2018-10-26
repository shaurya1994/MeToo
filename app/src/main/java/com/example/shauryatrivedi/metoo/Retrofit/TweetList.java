package com.example.shauryatrivedi.metoo.Retrofit;

public class TweetList {
String id;
String image;
String thumb_image;

    public TweetList(String id, String image, String thumb_image) {

        this.id = id;
        this.image = image;
        this.thumb_image = thumb_image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setThumb_image(String thumb_image) {
        this.thumb_image = thumb_image;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getThumb_image() {
        return thumb_image;
    }

}
