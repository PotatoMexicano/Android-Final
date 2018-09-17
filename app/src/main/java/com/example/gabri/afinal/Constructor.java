package com.example.gabri.afinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Constructor extends SQLiteOpenHelper{


    public Constructor(Context context) {
        super(context, "Banana", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USER (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT NOT NULL," +
                "LOGIN TEXT NOT NULL," +
                "PASSWORD TEXT NOT NULL)");

        db.execSQL("CREATE TABLE EVENT (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TYPE TEXT NOT NULL," +
                "DATE TEXT NOT NULL," +
                "TIME TEXT NOT NULL," +
                "DESCRIPTION TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
