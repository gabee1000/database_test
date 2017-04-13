package com.example.gabee1000.myapplication.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by gabee1000 on 2017. 04. 13..
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db_name.db";
    private static final String TABLE_NAME = "user_table";
    private static final String ID_KEY = "id";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ( " + ID_KEY + " INTEGER PRIMARY KEY, " + NAME + " TEXT, " + PASSWORD + " TEXT );";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
