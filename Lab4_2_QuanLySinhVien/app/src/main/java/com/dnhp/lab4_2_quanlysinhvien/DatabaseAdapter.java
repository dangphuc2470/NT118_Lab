package com.dnhp.lab4_2_quanlysinhvien;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAdapter {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static final int DATABASE_VERSION = 2;
    private final Context context;

    private static final String DATABASE_NAME = "students.db";
    private static final String TABLE_NAME = "students";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "full_name";
    private static final String COLUMN_YEAR_OF_BIRTH = "year_of_birth";
    private static final String COLUMN_CLASS_NAME = "class_name";

    public DatabaseAdapter(Context context) {
        this.context = context;
    }
    public DatabaseAdapter open() {
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }

    //Todo: Sửa lại hết bên dưới
    public long createStudent(String id, String name, String yob, String cl)
    {
        ContentValues inititalValues = new ContentValues();
        inititalValues.put(COLUMN_ID, id);
        inititalValues.put(COLUMN_NAME, name);
        inititalValues.put(COLUMN_YEAR_OF_BIRTH, yob);
        inititalValues.put(COLUMN_CLASS_NAME, cl);
        return sqLiteDatabase.insert(TABLE_NAME, null, inititalValues);
    }
    public boolean deleteStudent(long rowId) {
        return sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=" + rowId,
                null) > 0;
    }
    public boolean deleteAllStudents() {
        return sqLiteDatabase.delete(TABLE_NAME, null, null) > 0;
    }
    public Cursor getAllStudents() {
        return sqLiteDatabase.query(TABLE_NAME, new String[]{COLUMN_ID,
                COLUMN_NAME}, null, null, null, null, null);
    }
}
