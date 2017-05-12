package com.example.aad.sqlitetest.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aad.sqlitetest.Metier.Library;
import com.example.aad.sqlitetest.MySQLiteHelper;
import com.example.aad.sqlitetest.Utils;

/**
 * Created by AAD on 12/05/2017.
 */

public class LibraryDAO {

    public static final String TABLE_NAME = "library";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CREATED_DATE = "created_date";

    public static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "( " + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null"
            + COLUMN_CREATED_DATE + " text not null"
            + ");";


    private MySQLiteHelper mySQLiteHelper;
    private SQLiteDatabase myDatabase;

    private String[] allColums = {
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_CREATED_DATE
    };

    public LibraryDAO(Context context) {
        mySQLiteHelper = new MySQLiteHelper(context);
    }

    public void addLibrary(Library library) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, library.getName());
        values.put(COLUMN_CREATED_DATE, Utils.dateToString(library.getCreatedDate()));

        myDatabase = mySQLiteHelper.getWritableDatabase(); //open db

        long addId = myDatabase.insert(TABLE_NAME, null, values);

        myDatabase.close();
    }

    public Library getLibraryWithId(int id) {

        myDatabase = mySQLiteHelper.getReadableDatabase();

        Cursor cursor = myDatabase.query(TABLE_NAME, allColums, COLUMN_ID + " = " + id,
                null, null, null, null, null);

        cursor.close();
        myDatabase.close();

        return cursorToLibrary(cursor);
    }

    private Library cursorToLibrary(Cursor cursor) {

        Library library = new Library();

        library.setId(cursor.getInt(0));
        library.setName(cursor.getString(1));
        library.setCreatedDate(Utils.stringToDate(cursor.getString(2)));

        return library;
    }
}
