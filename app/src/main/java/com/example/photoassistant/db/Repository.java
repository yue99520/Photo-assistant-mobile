package com.example.photoassistant.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class Repository extends SQLiteOpenHelper {

    public Repository(Context context, String database) {
        super(context, database, null, 1);
    }

    public long insert(ContentValues contentValues) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long id = sqLiteDatabase.insert(getTableName(), null, contentValues);
        sqLiteDatabase.close();
        return id;
    }

    public long update(long id, ContentValues contentValues) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.update(
                getTableName(),
                contentValues,
                "where id = ?",
                new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return id;
    }

    public boolean isExists(long id) {
        boolean isExists;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + getTableName() + " where id = ?", new String[]{String.valueOf(id)});
        isExists = cursor.getCount() == 1;
        cursor.close();
        sqLiteDatabase.close();
        return isExists;
    }

    protected abstract String getTableName();
}

