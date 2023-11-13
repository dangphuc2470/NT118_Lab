package com.example.lab2_5;

import static com.example.lab2_5.Thumbnail.THUMBNAIL_1;
import static com.example.lab2_5.Thumbnail.THUMBNAIL_2;
import static com.example.lab2_5.Thumbnail.THUMBNAIL_3;
import static com.example.lab2_5.Thumbnail.THUMBNAIL_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Dish> dishes = new ArrayList<>();
        CustomGridViewAdapter adapter = new CustomGridViewAdapter(this, dishes);
        GridView gridView = findViewById(R.id.gridView_item);
        gridView.setAdapter(adapter);


        ArrayList<Thumbnail> thumbnails = new ArrayList<Thumbnail>()
        {{
            add(THUMBNAIL_1);
            add(THUMBNAIL_2);
            add(THUMBNAIL_3);
            add(THUMBNAIL_4);
        }};
        Spinner spinner = findViewById(R.id.spinner);
        CustomSpinnerAdapter spinnerAdaper= new CustomSpinnerAdapter(this, thumbnails);
        spinner.setAdapter(spinnerAdaper);


        Button btAdd = findViewById(R.id.btAddDish);
        btAdd.setOnClickListener((v ->
        {
            Dish dish = new Dish("123", THUMBNAIL_1);
            dishes.add(dish);
            adapter.notifyDataSetChanged();
        }));

    }
}


