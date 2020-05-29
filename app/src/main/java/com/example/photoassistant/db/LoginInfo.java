package com.example.photoassistant.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class LoginInfo extends Repository {

    private final String TABLE_NAME = "login_info";

    public LoginInfo(Context context, String database) {
        super(context, database);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "api_token VARCHAR(50) " +
                ");";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
    }

    protected String getTableName() {
        return TABLE_NAME;
    }
}