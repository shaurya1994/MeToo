package com.example.shauryatrivedi.metoo;

import java.util.ArrayList;

public class TweetLis {

String pages;
ArrayList<data> data;

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public ArrayList<com.example.shauryatrivedi.metoo.data> getData() {
        return data;
    }

    public void setData(ArrayList<com.example.shauryatrivedi.metoo.data> data) {
        this.data = data;
    }

    public TweetLis(String pages, ArrayList<com.example.shauryatrivedi.metoo.data> data) {
        this.pages = pages;
        this.data = data;
    }
}
