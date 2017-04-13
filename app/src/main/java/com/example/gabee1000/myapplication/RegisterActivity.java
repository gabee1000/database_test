package com.example.gabee1000.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gabee1000.myapplication.Database.UserDBHandler;
import com.example.gabee1000.myapplication.User.User;

/**
 * Created by gabee1000 on 2017. 04. 13..
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText nameET;
    private EditText passwdET;
    private EditText ageET;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        actions();
    }

    private void init() {
        nameET = (EditText) findViewById(R.id.new_name);
        passwdET = (EditText) findViewById(R.id.new_password);
        ageET = (EditText) findViewById(R.id.new_age);
        saveBtn = (Button) findViewById(R.id.save);
    }

    private void actions() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = false;
                if (nameET.getText().toString().isEmpty()) {
                    nameET.setError(getString(R.string.required_str));
                    flag = true;
                }
                if (passwdET.getText().toString().isEmpty()) {
                    passwdET.setError(getString(R.string.required_str));
                    flag = true;
                }
                if (ageET.getText().toString().isEmpty()) {
                    ageET.setError(getString(R.string.required_str));
                    flag = true;
                }
                if (flag) {
                    return;
                }
                UserDBHandler dbHandler = new UserDBHandler(v.getContext());
                User user = new User(dbHandler.getNewId(), nameET.getText().toString(), passwdET.getText().toString(), Integer.parseInt(ageET.getText().toString()));
                dbHandler.addUser(user);
            }
        });
    }

}
