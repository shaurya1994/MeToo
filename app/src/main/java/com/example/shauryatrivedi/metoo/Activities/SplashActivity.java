package com.example.shauryatrivedi.metoo.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shauryatrivedi.metoo.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT =2000;
    private ProgressBar progressbar;
    private int prgrsStatus = 0;
    Handler hd = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressbar = (ProgressBar)findViewById(R.id.prgrsBar);

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
                            progressbar.setProgress(prgrsStatus);
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
