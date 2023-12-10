package com.dnhp.lab4_2_quanlysinhvien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.dnhp.lab4_2_quanlysinhvien.databinding.ActivityMainBinding;
import com.dnhp.lab4_2_quanlysinhvien.databinding.CustomAlertDialogBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private DatabaseAdapter databaseAdapter;
    private Cursor cursor;
    private ArrayList<Student> students;
    private ActivityMainBinding AMBinding;
    private CustomAlertDialogBinding ADBinding;
    private String selectedStudentPosistion;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        AMBinding = ActivityMainBinding.inflate(getLayoutInflater());
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
                if (databaseAdapter.checkIfIdExists(Integer.parseInt(arrayList.get(0))))
                {
                    Toast.makeText(getApplicationContext(), "Dữ liệu bị trùng", Toast.LENGTH_SHORT).show();
                    return;
                }

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
                Log.i("DB", databaseAdapter.getAllStudentIDs());
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
                selectedStudentPosistion = clickedItem.getStudentId();
                showCustomAlertDialog(clickedItem);
            }
        });
        AMBinding.rsView.setAdapter(adapter);


    }



    private void showCustomAlertDialog(Student student)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialogStyle);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
        ADBinding = CustomAlertDialogBinding.bind(dialogView);
        ADBinding.RSEtID.setText(student.getStudentId());
        ADBinding.RSEtID.setFocusable(false);
        ADBinding.RSEtID.setFocusableInTouchMode(false);

        ADBinding.RSEtName.setText(student.getFullName());
        ADBinding.RSEtYoB.setText(student.getYearOfBirth());
        ADBinding.RSEtClass.setText(student.getClassName());

        ADBinding.btCancel.setOnClickListener(v ->
        {
            dialog.dismiss(); // Dismiss the dialog when cancel button is clicked
        });
        ADBinding.btDelete.setOnClickListener(v ->
        {
            databaseAdapter.deleteStudent(selectedStudentPosistion);
            dialog.dismiss(); // Dismiss the dialog when cancel button is clicked
            Log.i("DB", databaseAdapter.getAllStudentIDs());
            students = getData();
            showData(students);
        });
        ADBinding.btModify.setOnClickListener(v->
        {
            Student studentUpdate = new Student();
            studentUpdate.setStudentId(Integer.parseInt(String.valueOf(ADBinding.RSEtID.getText())));
            studentUpdate.setFullName(String.valueOf(ADBinding.RSEtName.getText()));
            studentUpdate.setClassName(String.valueOf(ADBinding.RSEtClass.getText()));
            studentUpdate.setYearOfBirth(Integer.parseInt(String.valueOf(ADBinding.RSEtYoB.getText())));
            int rowAffected = databaseAdapter.updateStudent(studentUpdate);
            if (rowAffected>=1)
            {
                Toast.makeText(getApplicationContext(), "Updated successfully!", Toast.LENGTH_SHORT).show();
                ADBinding.btCancel.setText(R.string.done);
            }
            else
                Toast.makeText(getApplicationContext(), "Update failed!", Toast.LENGTH_SHORT).show();

            students = getData();
            showData(students);
        });


        builder.setView(dialogView);
        dialog = builder.create();
        dialog.show();
    }


}

