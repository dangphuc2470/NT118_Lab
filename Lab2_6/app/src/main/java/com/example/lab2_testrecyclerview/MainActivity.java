package com.example.lab2_testrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity
{
    ArrayList<Employee> EmployeeList = new ArrayList<>();
    CustomRecyclerViewAdapter adapter = new CustomRecyclerViewAdapter(EmployeeList, item ->
    {
        Iterator<Employee> iterator = EmployeeList.iterator();
        while (iterator.hasNext())
        {
            Employee employee = iterator.next();
            if (employee.getName().equals(item.getName()))
            {
                iterator.remove(); // Xóa phần tử thỏa mãn điều kiện
            }
        }
        Toast.makeText(getApplicationContext(), "Delete " + item.getName(), Toast.LENGTH_LONG).show();
        updateList(EmployeeList);
    });

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayList<String> arr = new ArrayList<>();
//        arr.add("A");
//        arr.add("B");
//        arr.add("C");
//        arr.add("D");
//        arr.add("E");
//        arr.add("F");
//        String[] s = {"123123", "asdae", "afargs"};
//        RecyclerView recyclerView = findViewById(R.id.rsclView);
//        RecyclerViewAdapterClass1 adapterClass = new RecyclerViewAdapterClass1( arr);
//        recyclerView.setAdapter(adapterClass);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        Button button = (Button) findViewById(R.id.bbbton);
//        button.setOnClickListener(v ->
//        {
//            arr.add("ASdasd");
//            adapterClass.notifyDataSetChanged();
//        });

        RecyclerView recyclerView = findViewById(R.id.rsclView);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button btInput = findViewById(R.id.btInput);
        btInput.setOnClickListener(v ->
        {
            EditText etID = (EditText) findViewById(R.id.etMaNV);
            EditText etName = (EditText) findViewById(R.id.etTenNV);
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
            adapter.notifyDataSetChanged();


        });

    }

    private void updateList(ArrayList<Employee> EmployeeList)
    {
        adapter.updateList(EmployeeList);
    }
}

