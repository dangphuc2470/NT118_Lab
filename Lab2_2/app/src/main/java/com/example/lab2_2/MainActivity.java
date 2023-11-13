package com.example.lab2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lvPerson = (ListView)findViewById(R.id.lv_person);

        ArrayList names = new ArrayList<String>();
        names.add("Tèo");
        names.add("Tý");
        names.add("Bin");
        names.add("Bo");

        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, names);
        lvPerson.setAdapter(adapter);

        TextView tvSelection = (TextView)findViewById(R.id.tvSelection);

        lvPerson.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?>
                                                    parent, View view, int position, long id) {
                        //đối số arg2 là vị trí phần tử trong DataSource (arr)

                        tvSelection.setText("position :" + position + "; value =" + names.get(position));
                    }
                });

        Button btInput = (Button)findViewById(R.id.btInput);
        EditText etInput = (EditText)findViewById(R.id.etInput);
        btInput.setBackgroundColor(Color.argb(255,73,103,39));
        //@Override
        btInput.setOnClickListener(v -> {
            String inputText = etInput.getText().toString();
            names.add(inputText);
            adapter.notifyDataSetChanged();

        });



        lvPerson.setOnItemLongClickListener((parent, view, position, id) -> {
            names.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        });


    }
}