package com.example.shauryatrivedi.metoo.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shauryatrivedi.metoo.R;

public class TweetRvAdapter extends BaseAdapter{

    Context context;
    String[] created,tweet_txt,scr_name;
    LayoutInflater layoutInflater;

    public TweetRvAdapter(Context context, String[] created, String[] tweet_txt, String[] scr_name) {
        this.context = context;
        this.created = created;
        this.tweet_txt = tweet_txt;
        this.scr_name = scr_name;
        layoutInflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return created.length;
    }

    @Override
    public Object getItem(int position) {
        return getItemId(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.tweet_cardvw,null);
        TextView creat = (TextView)convertView.findViewById(R.id.created_on);
        TextView scrnam= (TextView)convertView.findViewById(R.id.screen_name);
        TextView twtext = (TextView)convertView.findViewById(R.id.tweet_text);
        creat.setText(created[position]);
        scrnam.setText(scr_name[position]);
        twtext.setText(tweet_txt[position]);
        return convertView;

    }
}
