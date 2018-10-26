package com.example.shauryatrivedi.metoo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shauryatrivedi.metoo.R;
import com.example.shauryatrivedi.metoo.Retrofit.MainPojo;
import com.example.shauryatrivedi.metoo.Retrofit.TweetList;

import java.util.ArrayList;
import java.util.List;

public class TweetRvAdapter extends RecyclerView.Adapter<TweetRvAdapter.Myviewholder> {

    private Context context;
    public static String transfer;
    ArrayList<MainPojo> data;

    public static class Myviewholder extends RecyclerView.ViewHolder {
        TextView txtTweetHandle;
        TextView txtTweet;
        TextView txtDate;

        public Myviewholder(@NonNull View itemView) {
            super( itemView );
            this.txtTweetHandle = itemView.findViewById(R.id.txttwhn);
            this.txtTweet=itemView.findViewById(R.id.txttweet);
            this.txtDate=itemView.findViewById(R.id.txtdate);
        }

    }
    public TweetRvAdapter(Context context, ArrayList<MainPojo> data) {
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweets_cards ,parent,false);
        Myviewholder myviewholder = new Myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull TweetRvAdapter.Myviewholder myviewholder, int i) {

        TextView txttweethandle=myviewholder.txtTweetHandle;
        TextView txttweet=myviewholder.txtTweet;
        TextView txttweetdate=myviewholder.txtDate;

        String txttwee=data.get(i).g

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
