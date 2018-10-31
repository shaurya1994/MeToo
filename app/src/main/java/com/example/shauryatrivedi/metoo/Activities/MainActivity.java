package com.example.shauryatrivedi.metoo.Activities;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.design.widget.TabLayout;

import com.example.shauryatrivedi.metoo.R;

public class MainActivity extends AppCompatActivity {

    private Button BtnLaws,BtnMetoo,BtnMetooIndia;
    private ViewGroup rootLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = (ViewGroup)findViewById(R.id.view_root);
        viewPager = (ViewPager)findViewById(R.id.vwPgr);
//        BtnLaws=(Button)findViewById(R.id.btnLaws);
//        BtnMetoo=(Button)findViewById(R.id.btnMeToo);
//        BtnMetooIndia=(Button)findViewById(R.id.btnMeTooIndia);



//        BtnLaws.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            startActivity(new Intent(MainActivity.this,LawActivity.class));
//            }
//        });
//
//        BtnMetoo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,Twitter.class);
//                intent.putExtra("Type","MeToo");
//                MainActivity.this.startActivity(intent);
//
//            }
//        });
//
//        BtnMetooIndia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,Twitter.class);
//                intent.putExtra("Type","MeTooIndia");
//                MainActivity.this.startActivity(intent);
//
//            }
//        });
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("LAWS"));
        tabLayout.addTab(tabLayout.newTab().setText("#MeTooIndia"));
        tabLayout.addTab(tabLayout.newTab().setText("#MeToo"));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.vwPgr);
        final PagerAdapter adapter = new com.example.shauryatrivedi.metoo.Adapters.PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
