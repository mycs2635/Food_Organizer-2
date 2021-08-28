package com.example.food_organizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class HomePage extends AppCompatActivity {
 TabLayout tabLayout;
 ViewPager2 viewpage;
 FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Intent intent = getIntent();
        tabLayout=findViewById(R.id.tablayout);
        viewpage=findViewById(R.id.viewpager2);
        FragmentManager fm=getSupportFragmentManager();
        adapter=new FragmentAdapter(fm,getLifecycle());
        viewpage.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("Inventory"));
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpage.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewpage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
               tabLayout.selectTab( tabLayout.getTabAt(position));
            }
        });



    }
}