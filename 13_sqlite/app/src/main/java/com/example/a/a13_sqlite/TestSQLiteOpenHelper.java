package com.example.a.a13_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by a on 2016-07-27.
 */
public class TestSQLiteOpenHelper extends SQLiteOpenHelper {
    public TestSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // 최초 설치시
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " name TEXT, age INTEGER, address TEXT);" ;
        db.execSQL(sql);
    }

    // 변경 되었을경우
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
