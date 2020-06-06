package com.example.photoassistant.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class Repository extends SQLiteOpenHelper {

    public Repository(Context context, String database) {
        super(context, database, null, 1);
    }

    public long insert(ContentValues contentValues) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.insert(getTableName(), null, contentValues);
    }

    protected abstract String getTableName();
}

