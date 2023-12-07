package com.dnhp.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);
        Cursor cursor = db.getAllData();
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));

        // Reading all contacts
        Log.e("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        List<String> contactStrings = db.getAllContactString();

        for (Contact cn : contacts)
        {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + ",Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.e("Name: ", log);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactStrings);
        ListView listView = findViewById(R.id.listview);
//        CursorAdapter adapter = new CustomCursorAdapter(this, cursor, 0); // Thay this bằng context của bạn và cursor là Cursor từ cơ sở dữ liệu
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener((parent, view, position, id) ->
        {
            Contact contact = contacts.get(position);
            db.deleteContact(contact);
            contacts.remove(position);
            contactStrings.remove(position);
            adapter.notifyDataSetChanged();
            return false;
        });

    }
}