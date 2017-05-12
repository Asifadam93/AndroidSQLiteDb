package com.example.aad.sqlitetest.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aad.sqlitetest.Metier.Comment;
import com.example.aad.sqlitetest.Metier.Library;
import com.example.aad.sqlitetest.Metier.User;
import com.example.aad.sqlitetest.MySQLiteHelper;
import com.example.aad.sqlitetest.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AAD on 12/05/2017.
 */

public class UserDAO {

    private static final String TABLE_NAME = "user";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_ID_LIBRARY = "_id_library";
    private static final String COLUMN_FIRST_NAME = "firstname";
    private static final String COLUMN_LAST_NAME = "lastname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_BIRTH_DATE = "birthdate";
    private static final String COLUMN_CREATED_DATE = "created_date";

    public static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "( " + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_ID_LIBRARY + " integer, "
            + COLUMN_FIRST_NAME + " text not null, "
            + COLUMN_LAST_NAME + " text not null, "
            + COLUMN_EMAIL + " text, "
            + COLUMN_BIRTH_DATE + " text, "
            + COLUMN_CREATED_DATE + " text not null"
            + ");";


    private MySQLiteHelper mySQLiteHelper;
    private SQLiteDatabase myDatabase;

    private String[] allColums = {
            COLUMN_ID,
            COLUMN_ID_LIBRARY,
            COLUMN_FIRST_NAME,
            COLUMN_LAST_NAME,
            COLUMN_EMAIL,
            COLUMN_BIRTH_DATE,
            COLUMN_CREATED_DATE
    };

    private LibraryDAO libraryDAO;

    public UserDAO(Context context) {
        mySQLiteHelper = new MySQLiteHelper(context);
        libraryDAO = new LibraryDAO(context);
    }

    public void addUser(User user) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_LIBRARY, user.getLibrary().getId());
        values.put(COLUMN_FIRST_NAME, user.getFirstName());
        values.put(COLUMN_LAST_NAME, user.getLastName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_BIRTH_DATE, Utils.dateToString(user.getBirthDate()));
        values.put(COLUMN_BIRTH_DATE, Utils.dateToString(user.getCreatedDate()));

        myDatabase = mySQLiteHelper.getWritableDatabase(); //open db

        long addId = myDatabase.insert(TABLE_NAME, null, values);

        myDatabase.close();
    }

    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        myDatabase = mySQLiteHelper.getReadableDatabase(); //open db

        Cursor cursor = myDatabase.query(TABLE_NAME, allColums, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            userList.add(user);
            cursor.moveToNext();
        }

        cursor.close();
        myDatabase.close();

        return userList;
    }

    private User cursorToUser(Cursor cursor){

        User user = new User();
        user.setId(cursor.getInt(0));
        user.setLibrary(libraryDAO.getLibraryWithId(cursor.getInt(1)));
        user.setFirstName(cursor.getString(2));
        user.setLastName(cursor.getString(3));
        user.setEmail(cursor.getString(4));
        user.setBirthDate(Utils.stringToDate(cursor.getString(5)));
        user.setCreatedDate(Utils.stringToDate(cursor.getString(6)));

        return user;
    }
}
