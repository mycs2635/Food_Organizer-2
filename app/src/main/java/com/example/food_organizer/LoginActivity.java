package com.example.food_organizer;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;


public class LoginActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewpager;
    FloatingActionButton fb,google,twitter;

    @Override
    protected void onCreate(Bundle savedInsatanceState) {

        super.onCreate(savedInsatanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        viewpager = findViewById(R.id.view_pager);
        fb=findViewById(R.id.fab_fb);
        google=findViewById(R.id.fab_google);
        twitter =findViewById(R.id.fab_twitter);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("SignUp"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewpager.setCurrentItem(tab.getPosition());
//            }
//
////            @Override
////            public void onTabUnselected(TabLayout.Tab tab) {
////
////            }
////
////            @Override
////            public void onTabReselected(TabLayout.Tab tab) {
////
////            }
//        });
//        viewpage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                tabLayout.selectTab( tabLayout.getTabAt(position));
//            }
//        });

           // final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(),)

    }

}
