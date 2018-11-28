package com.example.shauryatrivedi.metoo.Retrofit;

import java.util.ArrayList;

public class data {

    private ArrayList<JSON> data;

    public data(ArrayList<JSON> data) {
        this.data = data;
    }

    public ArrayList<JSON> getData() {
        return data;
    }

    public void setData(ArrayList<JSON> data) {
        this.data = data;
    }
}
