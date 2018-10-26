package com.example.shauryatrivedi.metoo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shauryatrivedi.metoo.R;
import com.example.shauryatrivedi.metoo.Retrofit.data;

import java.util.List;

public class TweetRvAdapter extends BaseAdapter{

    private Context context;
    private List<data> data;
    LayoutInflater layoutInflater;

    public TweetRvAdapter(Context context, List<data> data) {
        this.data = data;
        this.context = context;
        layoutInflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return data.size();
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
        TextView scrnam= (TextView)convertView.findViewById(R.id.screen_name);
        TextView twtext = (TextView)convertView.findViewById(R.id.tweet_text);
        TextView creat = (TextView)convertView.findViewById(R.id.created_on);
        com.example.shauryatrivedi.metoo.Retrofit.data info = data.get(position);
        String screename = info.getScreen_name();
        String tweetxt = info.getTweet_text();
        String created = info.getCreated_at();
        scrnam.setText("@"+screename);
        twtext.setText(tweetxt);
        creat.setText(created);
        return convertView;

    }
}
