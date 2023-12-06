package com.dnhp.lab3_1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyPagerAdapter extends FragmentStateAdapter
{

    public MyPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Trả về Fragment tại vị trí position
        if (position == 0) {
            return new Fragment1();
        } else {
            return new Fragment2();
        }
    }

    @Override
    public int getItemCount() {
        // Số lượng Fragment trong ViewPager2
        return 2;
    }
}
