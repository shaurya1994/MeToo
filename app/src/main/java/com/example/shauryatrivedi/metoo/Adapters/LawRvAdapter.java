package com.example.shauryatrivedi.metoo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.shauryatrivedi.metoo.R;
import com.bumptech.glide.Glide;
import com.example.shauryatrivedi.metoo.Retrofit.TweetList;

import java.util.List;

public class LawRvAdapter extends RecyclerView.Adapter<LawRvAdapter.Myviewholder> {
    private Context context;
    public static String transfer;
    List<TweetList> TweetList;
    public static class Myviewholder extends RecyclerView.ViewHolder {
        ImageView imageViewicon;
        public Myviewholder(@NonNull View itemView) {
            super( itemView );

            imageViewicon =(ImageView) itemView.findViewById(R.id.law_img);
        }
    }
    public LawRvAdapter(Context context, List<TweetList> list) {
        this.context = context;
        this.TweetList = list;
    }
    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.law_card ,parent,false);
        Myviewholder myviewholder = new Myviewholder( view );
        return myviewholder;
    }
    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {
        ImageView imageview = myviewholder.imageViewicon;
        com.example.shauryatrivedi.metoo.Retrofit.TweetList twee=TweetList.get(i);
        String imageurl=twee.getImage();
//        String imageurl = TweetList.get(i).getImage();
        try{
            Glide.with( context ).load(imageurl).into(imageview);
        }catch (IllegalArgumentException ex){
            Log.wtf("Glide-tag",String.valueOf(imageview.getTag()));}

        transfer = TweetList.get(i).getImage();
    }
    @Override
    public int getItemCount() {
        return TweetList.size();
    }
}