package com.dnhp.lab3_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class Lab_3_4 extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab34);
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        MyPagerAdapter adapter = new MyPagerAdapter(this); // Thay "this" bằng FragmentActivity nếu bạn sử dụng trong Fragment
        viewPager2.setAdapter(adapter);
    }
}