package com.example.shauryatrivedi.metoo.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.shauryatrivedi.metoo.Interface.ApiInterface;
import com.example.shauryatrivedi.metoo.R;
import com.example.shauryatrivedi.metoo.Retrofit.ApiClient;
import com.example.shauryatrivedi.metoo.Retrofit.TweetList;
import com.example.shauryatrivedi.metoo.Retrofit.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Twitter extends AppCompatActivity {

    private String type, page;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);

        type = getIntent().getStringExtra("Type");

        Getfeed();
    }

    private void Getfeed()
    {
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<List<data>> calll=api.get_dat("Type","1");

        calll.enqueue(new Callback<List<data>>() {
            @Override
            public void onResponse(Call<List<data>> call, Response<List<data>> response) {

                Toast.makeText(getApplicationContext(), "Retrofit Working", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<data>> call, Throwable t) {

                Log.e(TAG, t.toString());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
