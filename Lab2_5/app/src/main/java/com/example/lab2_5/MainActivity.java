package com.example.lab2_5;

import static com.example.lab2_5.Thumbnail.THUMBNAIL_1;
import static com.example.lab2_5.Thumbnail.THUMBNAIL_2;
import static com.example.lab2_5.Thumbnail.THUMBNAIL_3;
import static com.example.lab2_5.Thumbnail.THUMBNAIL_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    String selectedItemText;

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
        CustomSpinnerAdapter spinnerAdaper = new CustomSpinnerAdapter(this, thumbnails);
        spinner.setAdapter(spinnerAdaper);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                // Retrieve the selected item
                selectedItemText = ((TextView) view.findViewById(R.id.tvSpinner)).getText().toString();
                // Do something with the selected item
                //Toast.makeText(null, selectedItemText, Toast.LENGTH_SHORT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        EditText editText = findViewById(R.id.etName);
        Button btAdd = findViewById(R.id.btAddDish);
        btAdd.setOnClickListener((v ->
        {
            Dish dish = new Dish(editText.getText().toString(), thumbnails[]);
            dishes.add(dish);
            adapter.notifyDataSetChanged();
        }));

    }
}


