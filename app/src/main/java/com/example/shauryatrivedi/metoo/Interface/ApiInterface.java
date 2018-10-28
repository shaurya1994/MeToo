package com.example.shauryatrivedi.metoo.Interface;

import com.example.shauryatrivedi.metoo.Retrofit.MainPojo;
import com.example.shauryatrivedi.metoo.Retrofit.TweetList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("sexual_harassment_law")
    Call<List<TweetList>> get_tweet();

    @GET("twitter_feeds?type=MeToo&page=1")
    Call<MainPojo> get_dat(@Query("type") String type, @Query("page") String page);
}
