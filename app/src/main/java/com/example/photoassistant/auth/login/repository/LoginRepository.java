package com.example.photoassistant.auth.login.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.photoassistant.config.App;
import com.example.photoassistant.db.Repository;

public class LoginRepository extends Repository {

    private final String TABLE_NAME = "login_info";

    public LoginRepository(Context context) {
        super(context, App.SQLite_DATABASE_NAME);
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

    public boolean isTokenExists() {
        return isExists(1);
    }

    public String getToken() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + getTableName() + " where id = 1", new String[]{});
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex("api_token");
        String token = cursor.getString(columnIndex);
        cursor.close();
        sqLiteDatabase.close();
        return token;
    }

    public void updateToken(String token) {
        ContentValues values = new ContentValues();
        values.put("api_token", token);
        insert(values);
        close();
    }
}