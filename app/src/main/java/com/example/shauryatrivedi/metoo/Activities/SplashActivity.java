package com.example.shauryatrivedi.metoo.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shauryatrivedi.metoo.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT =3000;
    private int prgrsStatus = 0;
    Handler hd = new Handler();

    private  TextView message,meToo;
    private Typeface fontMsg,fontmeToo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        fontMsg = Typeface.createFromAsset(getAssets(),"fonts/message.ttf");
        fontmeToo = Typeface.createFromAsset(getAssets(),"fonts/title.ttf");
        message = (TextView)findViewById(R.id.message);
        message.setTypeface(fontMsg);
        meToo = (TextView)findViewById(R.id.meToo);
        meToo.setTypeface(fontmeToo);

        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
            }
        }, SPLASH_TIME_OUT);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (prgrsStatus < 100) {
                    prgrsStatus++;
                    android.os.SystemClock.sleep(10);
                    hd.post(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                }
                hd.post(new Runnable()
                {
                    @Override
                    public void run() {
                    }
                });
            }
        }).start();

    }
}
