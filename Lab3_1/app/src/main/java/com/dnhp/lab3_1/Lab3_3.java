package com.dnhp.lab3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Lab3_3 extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab33);

        findViewById(R.id.btBack).setOnClickListener(v ->
        {
            finish();
            overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
        });
    }
}
