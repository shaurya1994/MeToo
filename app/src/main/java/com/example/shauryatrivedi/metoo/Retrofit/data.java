package com.example.shauryatrivedi.metoo.Retrofit;

public class data {

    private String screen_name,tweet_text,created_at;

    public data(String screen_name, String tweet_text, String created_at) {

        this.screen_name = screen_name;
        this.tweet_text = tweet_text;
        this.created_at = created_at;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getTweet_text() {
        return tweet_text;
    }

    public void setTweet_text(String tweet_text) {
        this.tweet_text = tweet_text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


}
