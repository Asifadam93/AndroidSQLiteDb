package com.example.aad.sqlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aad.sqlitetest.DAO.LibraryDAO;
import com.example.aad.sqlitetest.DAO.UserDAO;

/**
 * Created by AAD on 12/05/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "commments.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_COMMENTS = "comments";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COMMENT = "comment";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_COMMENT
            + " text not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL(DATABASE_CREATE);
        sqLiteDatabase.execSQL(UserDAO.CREATE_TABLE);
        sqLiteDatabase.execSQL(LibraryDAO.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        Log.i("MySQLiteHelper", "onUpgrade from " + oldVersion + " to " + newVersion);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS); // delete old db
        onCreate(sqLiteDatabase); // recreate new db
    }
}
