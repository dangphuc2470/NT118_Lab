package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lvPerson = findViewById(R.id.lv_person);

        final String arr[] = {"Tèo", "Tý", "Bin", "Bo"};
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, arr);
        lvPerson.setAdapter(adapter);

        TextView tvSelection = (TextView)findViewById(R.id.tvSelection);
        //tvSelection.setBackgroundColor(Color.argb(100, 201,238,158));

        lvPerson.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?>
                                                    arg0, View arg1, int arg2, long arg3) {
                        //đối số arg2 là vị trí phần tử trong DataSource (arr)

                        tvSelection.setText("position :" + arg2 + "; value =" + arr[arg2]);
                    }
                });

    }
}