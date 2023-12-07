package com.dnhp.lab4_2_quanlysinhvien;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "students";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "full_name";
    private static final String COLUMN_YEAR_OF_BIRTH = "year_of_birth";
    private static final String COLUMN_CLASS_NAME = "class_name";

    private static final String DATABASE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_YEAR_OF_BIRTH + " INTEGER NOT NULL, " +
                    COLUMN_CLASS_NAME + " TEXT NOT NULL);";

    public DatabaseHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(DATABASE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS students");
        onCreate(db);
    }
}
