package com.example.shauryatrivedi.metoo.Interface;

import com.example.shauryatrivedi.metoo.Retrofit.TweetList;
import com.example.shauryatrivedi.metoo.Retrofit.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("v1/twitter/sexual_harassment_law")
    Call<List<TweetList>> get_tweet();

    @GET("v1/twitter/twitter_feeds")
    Call<data> get_data(@Query("type") String type, @Query("page") String page);
}
