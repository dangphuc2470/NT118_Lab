package com.example.lab2_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ArrayList EmployeeList;
    Employee employee;
    String text;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView lvPerson = findViewById(R.id.lv_person);
        TextView tvSelection = findViewById(R.id.tvSelection);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button btInput = findViewById(R.id.btInput);


        EmployeeList = new ArrayList<Employee>();

        adapter = new
                ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, EmployeeList);
        lvPerson.setAdapter(adapter);

        lvPerson.setOnItemClickListener(
                (parent, view, position, id) -> tvSelection.setText("position :" + position + "; value =" + EmployeeList.get(position).toString()));



        radioGroup.setOnCheckedChangeListener((group, checkedId) ->
        {
            RadioButton checkedRadioButton = findViewById(checkedId);
            text = checkedRadioButton.getText().toString();
        });

        btInput.setOnClickListener(v->
        {
            addNewEmployee();
        });
    }


    public void addNewEmployee() {
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        EditText etID = findViewById(R.id.etMaNV);
        EditText etName = findViewById(R.id.etTenNV);
        //Lấy ra đúng id của Radio Button được checked
        int radId = radioGroup.getCheckedRadioButtonId();
        String id = etID.getText().toString();
        String name = etName.getText().toString();
        if (radId == R.id.rbChinhThuc) {
            //tạo instance là FullTime
            employee = new EmployeeFullTime();
        } else {
            //Tạo instance là Partime
            employee = new EmployeePartTime();
        }
        //FullTime hay Partime thì cũng là Employee nên có các hàm này là hiển nhiên
        employee.setId(id);
        employee.setName(name);
        //Đưa employee vào ArrayList
        EmployeeList.add(employee);
        //Cập nhập giao diện
        adapter.notifyDataSetChanged();
    }
}



