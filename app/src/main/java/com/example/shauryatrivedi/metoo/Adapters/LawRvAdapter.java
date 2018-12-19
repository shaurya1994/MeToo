package com.example.shauryatrivedi.metoo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
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

        TweetList info = list.get(i);
        String imageurl = info.getImage();
        Glide.with(context).load(imageurl).into(holder.lawimg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private ImageView lawimg;
        CardView cardView;

        public myViewHolder(View itemView) {
            super(itemView);

            lawimg = itemView.findViewById(R.id.law_img);
            cardView = itemView.findViewById(R.id.clbCrdVw);
            cardView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Spread the awareness");
            menu.add(this.getAdapterPosition(),001,0,"Share");
            menu.add(this.getAdapterPosition(),002,1,"Download");
        }
    }
}

