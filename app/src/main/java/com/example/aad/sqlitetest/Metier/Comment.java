package com.example.aad.sqlitetest.Metier;

/**
 * Created by AAD on 12/05/2017.
 */

public class Comment {

    private long id;
    private String comment;

    public Comment() {
        this.id = 0;
        this.comment = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
