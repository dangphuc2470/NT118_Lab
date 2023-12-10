package com.dnhp.lab4_2_quanlysinhvien;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAdapter
{
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static final int DATABASE_VERSION = 2;
    private final Context context;

    private static final String DATABASE_NAME = "students.db";
    private static final String TABLE_NAME = "students";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "full_name";
    public static final String COLUMN_YEAR_OF_BIRTH = "year_of_birth";
    public static final String COLUMN_CLASS_NAME = "class_name";

    public DatabaseAdapter(Context context)
    {
        this.context = context;
    }

    public DatabaseAdapter open()
    {
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        dbHelper.close();
    }

    public void createStudent(Student student)
    {
        ContentValues inititalValues = new ContentValues();
        inititalValues.put(COLUMN_ID, student.getStudentId());
        inititalValues.put(COLUMN_NAME, student.getFullName());
        inititalValues.put(COLUMN_YEAR_OF_BIRTH, student.getYearOfBirth());
        inititalValues.put(COLUMN_CLASS_NAME, student.getClassName());
        sqLiteDatabase.insert(TABLE_NAME, null, inititalValues);
    }

    public int updateStudent(Student student)
    {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(COLUMN_NAME, student.getFullName());
        updatedValues.put(COLUMN_YEAR_OF_BIRTH, student.getYearOfBirth());
        updatedValues.put(COLUMN_CLASS_NAME, student.getClassName());

        return sqLiteDatabase.update(TABLE_NAME, updatedValues, COLUMN_ID + " = ?",
                new String[]{String.valueOf(student.getStudentId())});

//        String sql = "UPDATE " + TABLE_NAME +
//                " SET " + COLUMN_CLASS_NAME + " = '" + student.getClassName() + "', " +
//                COLUMN_NAME + " = '" + student.getFullName() + "', " +
//                COLUMN_YEAR_OF_BIRTH + " = " + student.getYearOfBirth() +
//                " WHERE " + COLUMN_ID + " = " + student.getStudentId();
//
//        Log.i("DB","execute: "+ sql);
//        sqLiteDatabase.execSQL(sql);
    }



    public void deleteStudent(String Id)
    {
        sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=" + Id,
                null);
    }

    public void deleteAllStudents()
    {
        sqLiteDatabase.delete(TABLE_NAME, null, null);
    }

    public Cursor getAllStudents()
    {
        return sqLiteDatabase.query(TABLE_NAME, new String[]{COLUMN_ID,
                COLUMN_NAME, COLUMN_YEAR_OF_BIRTH, COLUMN_CLASS_NAME}, null, null, null, null, null);
    }

    public boolean checkIfIdExists(int id)
    {
        //Cursor cursor = db.rawQuery("SELECT * FROM students WHERE _id = ?", new String[]{String.valueOf(id)});
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }


    //Logcat
    public String getAllStudentIDs()
    {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_CLASS_NAME}, null, null, null, null, null);
        StringBuilder idStringBuilder = new StringBuilder();

        while (cursor.moveToNext())
        {
            int studentID = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String studentClass = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CLASS_NAME));
            idStringBuilder.append(studentID).append(studentClass).append(", "); // Separating IDs with a comma
        }

        cursor.close();
        return idStringBuilder.toString();
    }

}
