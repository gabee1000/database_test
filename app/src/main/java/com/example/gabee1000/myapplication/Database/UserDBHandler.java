package com.example.gabee1000.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gabee1000.myapplication.User.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by gabee1000 on 2017. 04. 13..
 */

public class UserDBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db_name.db";
    private static final String TABLE_NAME = "user_table";
    private static final String ID_KEY = "id";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String AGE = "age";

    public UserDBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ( " + ID_KEY + " INTEGER PRIMARY KEY, " + NAME + " TEXT, " + PASSWORD + " TEXT, " + AGE + " INTEGER );";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_KEY, user.getId());
        values.put(NAME, user.getName());
        values.put(PASSWORD, user.getPassword());
        values.put(AGE, user.getAge());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int getNewId() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        db.close();
        cursor.close();
        return count;
    }

    public Collection<? extends User> getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> userList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;
    }
}
