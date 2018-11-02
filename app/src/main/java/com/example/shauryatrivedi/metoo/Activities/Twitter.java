package com.example.shauryatrivedi.metoo.Activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shauryatrivedi.metoo.Adapters.TweetRvAdapter;
import com.example.shauryatrivedi.metoo.Interface.ApiInterface;
import com.example.shauryatrivedi.metoo.R;
import com.example.shauryatrivedi.metoo.Retrofit.ApiClient;
import com.example.shauryatrivedi.metoo.Retrofit.MainPojo;
import com.example.shauryatrivedi.metoo.Retrofit.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Twitter extends AppCompatActivity {

    private List<data> data;
    private String type, page;
    private String TAG = MainActivity.class.getSimpleName();
    ListView tweets1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);
        tweets1 = (ListView)findViewById(R.id.tweet_list);
        type = getIntent().getStringExtra("Type");
        //page=1
        Getfeed();
    }

    private void Getfeed()
    {
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
      //Call<MainPojo> calll=api.get_dat(type,page+1);
        Call<MainPojo> calll=api.get_dat(type,"1");

        calll.enqueue(new Callback<MainPojo>() {
            @Override
            public void onResponse(Call<MainPojo> call, Response<MainPojo> response) {
                MainPojo pojo = response.body();
                data = pojo.getData();
                tweets1.setAdapter(new TweetRvAdapter(Twitter.this,data));
            }

            @Override
            public void onFailure(Call<MainPojo> call, Throwable t) {
                Log.e(TAG, t.toString());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}