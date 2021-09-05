package com.example.food_organizer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapterNew extends FragmentStateAdapter {


    public FragmentAdapterNew(FragmentManager fm, Lifecycle lifecycle) {
        super(fm,lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position)
        {
            case 1:
                return new SignupFragmentNew();
        }
        return new LoginFragmentNew();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
