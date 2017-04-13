package com.example.gabee1000.myapplication.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.gabee1000.myapplication.MainActivity;

/**
 * Created by gabee1000 on 2017. 04. 13..
 */

public class UserDatabase {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public UserDatabase(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
}
