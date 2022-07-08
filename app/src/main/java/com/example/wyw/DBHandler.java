package com.example.wyw;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "ordersdb";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "myorders";

    private static final String ID_COL = "id";

    private static final String ADDRESS_COL = "name";
    private static final String PHONE_COL = "duration";
    private static final String REQUIREMENT_COL = "description";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ADDRESS_COL + " TEXT,"
                + PHONE_COL + " TEXT,"
                + REQUIREMENT_COL + " TEXT)";

        db.execSQL(query);
    }

    public void addNewData(String address, String phone,String requirement) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(ADDRESS_COL, address);
        values.put(PHONE_COL, phone);
        values.put(REQUIREMENT_COL, requirement);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public ArrayList<Modal> readData() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<Modal> modalArrayList = new ArrayList<>();

        if (cursorCourses.moveToFirst()) {
            do {

                modalArrayList.add(new Modal(cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3)));
            } while (cursorCourses.moveToNext());
        }

        cursorCourses.close();
        return modalArrayList;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

