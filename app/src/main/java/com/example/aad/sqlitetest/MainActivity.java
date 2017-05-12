package com.example.aad.sqlitetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.aad.sqlitetest.DAO.CommentsDAO;
import com.example.aad.sqlitetest.DAO.UserDAO;
import com.example.aad.sqlitetest.Metier.Comment;
import com.example.aad.sqlitetest.Metier.Library;
import com.example.aad.sqlitetest.Metier.User;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Library library = new Library();
        library.setName("Asif's library");
        library.setCreatedDate(new Date());

        User user = new User();
        user.setLibrary(library);
        user.setFirstName("Asif");
        user.setLastName("Adamsha");
        user.setEmail("adamsha.asif@gmail.com");
        user.setBirthDate();

        userDAO.addUser();


    }
}
