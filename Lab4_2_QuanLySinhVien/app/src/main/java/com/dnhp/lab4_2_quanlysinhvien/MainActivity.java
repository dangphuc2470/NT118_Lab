package com.dnhp.lab4_2_quanlysinhvien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.dnhp.lab4_2_quanlysinhvien.databinding.ActivityMainBinding;
import com.dnhp.lab4_2_quanlysinhvien.databinding.CustomAlertDialogBinding;
import com.dnhp.lab4_2_quanlysinhvien.databinding.RecyclerViewItemBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private DatabaseAdapter databaseAdapter;
    private Cursor cursor;
    private ArrayList<Student> students;
    private ActivityMainBinding AMBinding;
    private CustomAlertDialogBinding alertDialogBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        AMBinding = ActivityMainBinding.inflate(getLayoutInflater());
        alertDialogBinding = CustomAlertDialogBinding.inflate(getLayoutInflater());
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

            if (databaseAdapter.checkIfIdExists(Integer.parseInt(arrayList.get(0))))
            {
                Toast.makeText(getApplicationContext(), "Dữ liệu bị trùng", Toast.LENGTH_SHORT).show();
                return;
            }
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
        AMBinding.rsView.setLayoutManager(new LinearLayoutManager(this));
        CustomRecyclerViewAdapter adapter = new CustomRecyclerViewAdapter(students, new CustomRecyclerViewAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(int position)
            {
                Student clickedItem = students.get(position);
                showItemDetailsDialog(position);
            }
        });
        AMBinding.rsView.setAdapter(adapter);

//        RecyclerView recyclerView = findViewById(R.id.rsView);
//        CustomRecyclerViewAdapter adapter = new CustomRecyclerViewAdapter(students);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void showItemDetailsDialog(int position)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Details");

        // Get item details based on position
        Student item = students.get(position);

        // Construct detail message based on item details
        String detailMessage = "ID: " + item.getStudentId() + "\nName: " + item.getFullName() + "\nOther details...";

        builder.setMessage(detailMessage);

        // Set up a button to dismiss the dialog
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
            }
        });

        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showCustomAlertDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);


//        // Thiết lập dữ liệu hoặc sự kiện cho các thành phần trong Dialog
//        textView1.setText("Text 1");
//        textView2.setText("Text 2");
//        imageView.setImageResource(R.drawable.your_image); // Thay your_image bằng ID của hình ảnh bạn muốn hiển thị
//
//        button1.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                // Xử lý khi button1 được nhấn
//            }
//        });
//
//        button2.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                // Xử lý khi button2 được nhấn
//            }
//        });

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}

