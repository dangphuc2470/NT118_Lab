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
    public long createUser(String name) {
        ContentValues inititalValues = new ContentValues();
        inititalValues.put(KEY_NAME, name);
        return sqLiteDatabase.insert(TABLE_NAME, null, inititalValues);
    }
    public boolean deleteUser(long rowId) {
        return sqLiteDatabase.delete(TABLE_NAME, KEY_ID + "=" + rowId,
                null) > 0;
    }
    public boolean deleteAllUsers() {
        return sqLiteDatabase.delete(TABLE_NAME, null, null) > 0;
    }
    public Cursor getAllUsers() {
        return sqLiteDatabase.query(TABLE_NAME, new String[]{KEY_ID,
                KEY_NAME}, null, null, null, null, null);
    }
}
