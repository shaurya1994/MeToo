package com.example.shauryatrivedi.metoo.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shauryatrivedi.metoo.R;
import com.example.shauryatrivedi.metoo.Retrofit.JSON;
import com.example.shauryatrivedi.metoo.Retrofit.data;

import java.util.List;

public class TweetRvAdapter extends BaseAdapter{

    private Context context;
    private List<JSON> json;
    private LayoutInflater layoutInflater;

    public TweetRvAdapter(Context context, List<JSON> json) {
        this.json = json;
        this.context = context;
        layoutInflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return json.size();
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
        TextView create = (TextView)convertView.findViewById(R.id.created_on);
        JSON info = json.get(position);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/tweet.ttf");
        twtext.setTypeface(typeface);
        scrnam.setTypeface(typeface);
        String screename = info.getScreen_name();
        String tweetxt = info.getTweet_text();
        String created = info.getCreated_at();
        scrnam.setText("@"+screename);
        twtext.setText(tweetxt);
        create.setText(created);
        return convertView;

    }
}
