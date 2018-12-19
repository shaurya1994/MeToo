package com.example.shauryatrivedi.metoo.Activities;

import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shauryatrivedi.metoo.Adapters.ViewPagerAdapter;
import com.example.shauryatrivedi.metoo.Fragments.Laws;
import com.example.shauryatrivedi.metoo.Fragments.MeToo;
import com.example.shauryatrivedi.metoo.Fragments.MeTooIn;
import com.example.shauryatrivedi.metoo.R;

public class MainActivity extends AppCompatActivity implements Laws.OnFragmentInteractionListener,MeTooIn.OnFragmentInteractionListener,MeToo.OnFragmentInteractionListener {


    private ViewGroup rootLayout;
    private Typeface fontTab;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = (ViewGroup)findViewById(R.id.view_root);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        fontTab = Typeface.createFromAsset(getAssets(),"fonts/title.ttf");
        tabLayout.addTab(tabLayout.newTab().setText("LAWS"));
        tabLayout.addTab(tabLayout.newTab().setText("#MeTooIndia"));
        tabLayout.addTab(tabLayout.newTab().setText("#MeToo"));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.vwPgr);
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
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

        setCustomFont();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m1 = getMenuInflater();
        getMenuInflater().inflate(R.menu.actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_share:
                Toast.makeText(MainActivity.this,"Playstore Link",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setCustomFont() {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    //Put your font in assests folder
                    //assign name of the font here (Must be case sensitive)
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getAssets(),"fonts/tab.ttf"));
                }
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
