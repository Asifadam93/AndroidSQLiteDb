package com.example.aad.sqlitetest.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aad.sqlitetest.Metier.Comment;
import com.example.aad.sqlitetest.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AAD on 12/05/2017.
 */

public class CommentsDAO {

    private MySQLiteHelper mySQLiteHelper;
    private SQLiteDatabase myDatabase;

    private String[] allColums = {
            MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT
    };

    public CommentsDAO(Context context) {
        mySQLiteHelper = new MySQLiteHelper(context);
    }

    public void open() {
        myDatabase = mySQLiteHelper.getWritableDatabase();
    }

    public void close(){
        myDatabase.close();
    }

    public void addComment(String commentToAdd) {

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, commentToAdd);

        long addId = myDatabase.insert(MySQLiteHelper.TABLE_COMMENTS,null,values);
    }

    public List<Comment> getAllComments(){

        List<Comment> commentList = new ArrayList<>();

        Cursor cursor = myDatabase.query(MySQLiteHelper.TABLE_COMMENTS,allColums,null,null,null,null,null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Comment comment = cursorToComment(cursor);
            commentList.add(comment);
            cursor.moveToNext();
        }

        cursor.close();
        return commentList;
    }

    private Comment cursorToComment(Cursor cursor){

        Comment comment = new Comment();

        comment.setId(cursor.getInt(0));
        comment.setComment(cursor.getString(1));

        return comment;

    }
}
