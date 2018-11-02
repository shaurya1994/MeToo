package com.example.shauryatrivedi.metoo.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.shauryatrivedi.metoo.Adapters.LawRvAdapter;
import com.example.shauryatrivedi.metoo.Interface.ApiInterface;
import com.example.shauryatrivedi.metoo.R;
import com.example.shauryatrivedi.metoo.Retrofit.ApiClient;
import com.example.shauryatrivedi.metoo.Retrofit.TweetList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LawActivity extends AppCompatActivity {

    private String laws, id;
    private RecyclerView recyclerView;
    private ProgressDialog pDialogue;

    List<TweetList> lawList;

    private LawRvAdapter lawRvAdapter;
    private String TAG = LawActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law);

        recyclerView = (RecyclerView) findViewById(R.id.lawRV);
        recyclerView.setHasFixedSize(true);

        final ApiInterface api = ApiClient.getClient().create(ApiInterface.class);

        Call<List<TweetList>> call = api.get_tweet();

        call.enqueue(new Callback<List<TweetList>>() {
            @Override
            public void onResponse(Call<List<TweetList>> call, Response<List<TweetList>> response) {
                if (pDialogue.isShowing())
                    pDialogue.dismiss();

                List<TweetList> lawList=response.body();
                if(lawList!=null)
                {
                    Log.d(TAG,"Number of images recieved: "+ lawList.size());
                }

                lawRvAdapter=new LawRvAdapter(LawActivity.this,lawList);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(LawActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(lawRvAdapter);
//                lawRvAdapter.notifyDataSetChanged();
                Toast.makeText(LawActivity.this, "IN RETROFIT ACTIVITY" , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<TweetList>> call, Throwable t) {
                Log.e(TAG, t.toString());
                Toast.makeText(getApplicationContext(),"NOT IN RETROFIT ACTIVITY",Toast.LENGTH_LONG).show();

            }
        });
        showProgDiag();
    }

    private void showProgDiag() {
        pDialogue = new ProgressDialog(LawActivity.this);
        pDialogue.setMessage("Loading photos");
        pDialogue.setCancelable(true);
        pDialogue.show();
    }
}

