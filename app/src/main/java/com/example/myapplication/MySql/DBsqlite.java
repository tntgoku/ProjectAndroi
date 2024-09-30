package com.example.myapplication.MySql;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBsqlite extends SQLiteOpenHelper {
    private  static final  String DB="test1.sqlite";
    private static final int VERSION = 1;
    public DBsqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DBsqlite(@Nullable Context context) {
        super(context, DB, null, VERSION);
    }
    public DBsqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_create_table = "create table tblFruit(id integer primary key autoincrement, " +
                "name text, descs text, avatar integer)";
        db.execSQL(sql_create_table);
        Log.d("AAAA", "db create successfull");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void execSQL (String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
    public Cursor selectData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }
}
