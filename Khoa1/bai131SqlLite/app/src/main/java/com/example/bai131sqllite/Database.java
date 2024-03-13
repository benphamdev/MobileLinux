package com.example.bai131sqllite;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(
            @Nullable Context context,
            @Nullable String name,
            @Nullable SQLiteDatabase.CursorFactory factory, int version
    ) {
        super(context, name, factory, version);

    }

    public Database(
            @Nullable Context context,
            @Nullable String name,
            @Nullable SQLiteDatabase.CursorFactory factory, int version,
            @Nullable DatabaseErrorHandler errorHandler
    ) {
        super(context, name, factory, version, errorHandler);
    }

    public Database(
            @Nullable Context context,
            @Nullable String name, int version,
            @NonNull SQLiteDatabase.OpenParams openParams
    ) {
        super(context, name, version, openParams);
    }

    // truy van khong tra ket qua : CREATE, INSERT, UPDATE, DELETE, ..
    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    //truy van tra ve ket qua : SELECT
    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void updateTask(int id, String name) {
    }
}
