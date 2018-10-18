package com.example.shauryatrivedi.metoo.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.shauryatrivedi.metoo.Adapters.LawRvAdapter;
import com.example.shauryatrivedi.metoo.Interface.ApiInterface;
import com.example.shauryatrivedi.metoo.R;
import com.example.shauryatrivedi.metoo.Retrofit.ApiClient;
import com.example.shauryatrivedi.metoo.Retrofit.TweetList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LawActivity extends AppCompatActivity {

    private String laws,id;
    List<TweetList> lawList;
    private ProgressDialog pDialogue;
    private String TAG = LawActivity.class.getSimpleName();

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law);
        laws = getIntent().getStringExtra("LawImage");
        recyclerView = this.findViewById(R.id.lawRV);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        getLawImgs();
        showProgDiag();
    }

    public interface ClickListener{
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private LawActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final LawActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }

    private void showProgDiag(){
        pDialogue = new ProgressDialog(LawActivity.this);
        pDialogue.setMessage("Loading photos");
        pDialogue.setCancelable(true);
        pDialogue.show();
    }

    private void getLawImgs(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<TweetList>> call = apiService.get_tweet(id,laws);

        call.enqueue(new Callback<List<TweetList>>() {
            @Override
            public void onResponse(Call<List<TweetList>> call, Response<List<TweetList>> response) {
                if (pDialogue.isShowing())
                    pDialogue.dismiss();

                lawList = response.body();
                if (lawList != null){
                    Log.d(TAG, "Number of Celeb received: " + lawList.size());

                    LawRvAdapter newAdaptr = new LawRvAdapter(LawActivity.this,lawList);
                    recyclerView.setAdapter(newAdaptr);
                    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
                        @Override
                        public void onClick(View view, int position) {
//                            Intent int1 = new Intent(LawActivity.this,CelebPhotoActivity.class);
//                            int1.putExtra("url", lawList.get(position).getImage());
//                            startActivity(int1);
                        }

                        @Override
                        public void onLongClick(View view, int position) {
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<List<TweetList>> call, Throwable t) {
                Log.e(TAG, t.toString());
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
