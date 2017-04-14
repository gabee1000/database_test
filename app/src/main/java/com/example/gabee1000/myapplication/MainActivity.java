package com.example.gabee1000.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gabee1000.myapplication.Database.UserDBHandler;
import com.example.gabee1000.myapplication.Listeners.RegisterClickListener;
import com.example.gabee1000.myapplication.User.DisplayUsers;
import com.example.gabee1000.myapplication.User.User;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private UserDBHandler dbHandler;
    private EditText nameET;
    private EditText passwdET;
    private Button btnLogin;
    private Button btnRegister;
    private Button btnShowAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        load();
        actions();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHandler = new UserDBHandler(this);
        nameET = (EditText) findViewById(R.id.name);
        passwdET = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.login);
        btnRegister = (Button) findViewById(R.id.register);
        btnRegister.setOnClickListener(new RegisterClickListener());
        btnShowAll = (Button) findViewById(R.id.show_all_user);
    }

    private void load() {

    }

    private void actions() {
        btnShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DisplayUsers.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(v.getContext(), R.style.AlertDialogTheme).create();
                if (dbHandler.isUserInDatabase(new User(0,nameET.getText().toString(), passwdET.getText().toString(), 0))) {
                    dialog.setTitle("Sikeres bejelentkezés");
                    dialog.setMessage("Ön bejelentkezett [" + nameET.getText().toString() + "] névvel!");
                    Drawable drawable = v.getContext().getDrawable(android.R.drawable.ic_dialog_info);
                    assert drawable != null;
                    drawable.setTint(Color.argb(255, 100, 100, 100));
                    dialog.setIcon(drawable);
                    dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    dialog.show();
                } else {
                    dialog.setTitle("Sikertelen bejelentkezés");
                    dialog.setMessage("Hibás, vagy nem létezik ez a felhasználó az adatbázisban!");
                    Drawable drawable = v.getContext().getDrawable(android.R.drawable.ic_dialog_alert);
                    assert drawable != null;
                    drawable.setTint(Color.argb(255, 255, 80, 80));
                    dialog.setIcon(drawable);
                    dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Újra", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            nameET.clearFocus();
                            passwdET.clearFocus();
                        }
                    });
                    dialog.show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
