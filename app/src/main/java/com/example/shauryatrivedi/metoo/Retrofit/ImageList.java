package com.example.shauryatrivedi.metoo.Retrofit;

import com.example.shauryatrivedi.metoo.Adapters.LawRvAdapter;

import java.util.ArrayList;

public class ImageList {
    public LawRvAdapter lawRvAdapter;
    private ArrayList<TweetList> tweetList;

    public ImageList(ArrayList<TweetList> tweetList) {
        this.tweetList = tweetList;
    }

    public ArrayList<TweetList> getTweetList() {
        return tweetList;
    }

    public void setTweetList(ArrayList<TweetList> tweetList) {
        this.tweetList = tweetList;
    }
}
