package com.example.lab2_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EmployeeAdapter employeeAdapter;
    ArrayList EmployeeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EmployeeList = new ArrayList<Employee>();


        employeeAdapter = new EmployeeAdapter(this, android.R.layout.simple_list_item_1, EmployeeList);
        ListView lvPerson = findViewById(R.id.lv_person);

        lvPerson.setAdapter(employeeAdapter);

        Button btInput = findViewById(R.id.btInput);

        btInput.setOnClickListener(v->
        {
            Toast.makeText(this, "Clicked",Toast.LENGTH_SHORT);
            EditText etID = findViewById(R.id.etMaNV);
            EditText etName = findViewById(R.id.etTenNV);
            CheckBox cb = findViewById(R.id.checkBox);

            String id = etID.getText().toString();
            String name = etName.getText().toString();
            Employee employee;
            if (cb.isChecked() == true)
                employee = new Employee(true);
            else
                employee = new Employee(false);
            //FullTime hay Partime thì cũng là Employee nên có các hàm này là hiển nhiên
            employee.setId(id);
            employee.setName(name);
            //Đưa employee vào ArrayList
            EmployeeList.add(employee);
            //Cập nhập giao diện
            employeeAdapter.notifyDataSetChanged();

        });

    }
}


