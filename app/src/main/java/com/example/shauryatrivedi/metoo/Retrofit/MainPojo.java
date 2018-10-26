package com.example.shauryatrivedi.metoo.Retrofit;

import java.util.ArrayList;

public class MainPojo {

     String page;
     ArrayList<data> Data;

    public MainPojo(String page, ArrayList<data> data) {
        this.page = page;
        Data = data;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public ArrayList<data> getData() {
        return Data;
    }

    public void setData(ArrayList<data> data) {
        Data = data;
    }
}
