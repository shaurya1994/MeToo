package com.example.shauryatrivedi.metoo.Retrofit;

import java.util.ArrayList;

public class MainPojo {

    String page;
    ArrayList<data> data;

    public MainPojo(String page, ArrayList<data> data) {

        this.page = page;
        this.data = data;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public ArrayList<data> getData() {
        return data;
    }

    public void setData(ArrayList<data> data) {
        this.data = data;
    }

}
