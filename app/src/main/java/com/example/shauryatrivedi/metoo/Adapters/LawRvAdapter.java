package com.example.shauryatrivedi.metoo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.shauryatrivedi.metoo.R;
import com.example.shauryatrivedi.metoo.Retrofit.TweetList;

import java.util.List;

public class LawRvAdapter extends RecyclerView.Adapter<LawRvAdapter.myViewHolder> {
   private Context context;
   private List<TweetList> list;

    public LawRvAdapter(Context context, List<TweetList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View v= LayoutInflater.from(context).inflate(R.layout.law_card,viewGroup,false);

       return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int i) {

        TweetList info=list.get(i);
        String imageurl=info.getImage();

        Glide.with(context).load(imageurl).into(holder.lawimg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class myViewHolder extends RecyclerView.ViewHolder {

        ImageView lawimg;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            lawimg=(ImageView)itemView.findViewById(R.id.law_img);
        }
    }

}

