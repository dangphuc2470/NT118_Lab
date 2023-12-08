package com.dnhp.lab4_2_quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dnhp.lab4_2_quanlysinhvien.databinding.ActivityMainBinding;
import com.dnhp.lab4_2_quanlysinhvien.databinding.RecyclerViewItemBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private DatabaseAdapter databaseAdapter;
    private Cursor cursor;
    private ArrayList<Student> students;
    private ActivityMainBinding AMBinding;
    private RecyclerViewItemBinding recyclerViewItemBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        AMBinding = ActivityMainBinding.inflate(getLayoutInflater());
        recyclerViewItemBinding = RecyclerViewItemBinding.inflate(getLayoutInflater());
        setContentView(AMBinding.getRoot());

        databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter.open();
        databaseAdapter.deleteAllStudents();
        Student Phuc = new Student(21522470, "Đặng Nguyễn Hoàng Phúc", 2003, "MMTT2021");
        databaseAdapter.createStudent(Phuc);

        students = getData();
        showData(students);

        AMBinding.btAddStudent.setOnClickListener(v ->
        {
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(AMBinding.etID.getText().toString());
            arrayList.add(AMBinding.etName.getText().toString());
            arrayList.add(AMBinding.etYoB.getText().toString());
            arrayList.add(AMBinding.etClass.getText().toString());

            try
            {
                Student student = new Student();
                student.setStudentId(Integer.parseInt(arrayList.get(0)));
                student.setFullName(arrayList.get(1));
                student.setYearOfBirth(Integer.parseInt(arrayList.get(2)));
                student.setClassName(arrayList.get(3));

                AMBinding.etID.setText("");
                AMBinding.etName.setText("");
                AMBinding.etYoB.setText("");
                AMBinding.etClass.setText("");

                databaseAdapter.createStudent(student);
                students = getData();
                showData(students);
            } catch (NumberFormatException e)
            {
                Toast.makeText(getApplicationContext(), "Dữ liệu bị sai", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private ArrayList<Student> getData()
    {
        ArrayList<Student> students = new ArrayList<>();
        cursor = databaseAdapter.getAllStudents();
        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseAdapter.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseAdapter.COLUMN_NAME));
                int yearOfBirth = cursor.getInt(cursor.getColumnIndex(DatabaseAdapter.COLUMN_YEAR_OF_BIRTH));
                String className = cursor.getString(cursor.getColumnIndex(DatabaseAdapter.COLUMN_CLASS_NAME));

                Student student = new Student(id, name, yearOfBirth, className);
                students.add(student);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return students;
    }


    private void showData(ArrayList<Student> students)
    {
        RecyclerView recyclerView = findViewById(R.id.rsView);
        CustomRecyclerViewAdapter adapter = new CustomRecyclerViewAdapter(students);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

