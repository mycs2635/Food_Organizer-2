package com.example.food_organizer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {


    public FragmentAdapter(FragmentManager fm, Lifecycle lifecycle) {
        super(fm,lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position)
        {
            case 1:
                return new HomeFragment();
            case 2:
                return new ProfileFragment();
            case 3:
                return new DonationFragment();
        }
        return new InventoryFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
