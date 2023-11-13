package com.example.lab1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_minor);
        Toast.makeText(this, "OnCreate", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_minor);
    }



}