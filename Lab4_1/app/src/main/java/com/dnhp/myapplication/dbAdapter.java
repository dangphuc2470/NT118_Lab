//package com.dnhp.myapplication;
//
//import static com.dnhp.myapplication.DatabaseHandler.TABLE_CONTACTS;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.content.Context;
//import java.util.List;
//
//public class dbAdapter {
//
//    private static DatabaseHandler databaseHandler;
//
//    public dbAdapter(Context context) {
//        databaseHandler = new DatabaseHandler(context);
//    }
//
//    public void addContact(Contact contact) {
//        databaseHandler.addContact(contact);
//    }
//
//    public Contact getContact(int id) {
//        return databaseHandler.getContact(id);
//    }
//
//    public static List<Contact> getAllContacts() {
//        return databaseHandler.getAllContacts();
//    }
//
//    public int updateContact(Contact contact) {
//        return databaseHandler.updateContact(contact);
//    }
//
//    public void deleteContact(Contact contact) {
//        databaseHandler.deleteContact(contact);
//    }
//
//    public void deleteAllUsers() {
//        SQLiteDatabase db = databaseHandler.getWritableDatabase();
//        db.delete(TABLE_CONTACTS, null, null);
//        db.close();
//    }
//    public void open() {
//        databaseHandler.getWritableDatabase();
//    }
//
//}
