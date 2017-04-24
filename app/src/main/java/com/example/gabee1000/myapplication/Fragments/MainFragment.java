package com.example.gabee1000.myapplication.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.gabee1000.myapplication.Database.UserDBHandler;
import com.example.gabee1000.myapplication.Listeners.RegisterClickListener;
import com.example.gabee1000.myapplication.R;
import com.example.gabee1000.myapplication.User.DisplayUsers;
import com.example.gabee1000.myapplication.User.User;

/**
 * Created by gabee1000 on 2017. 04. 24..
 */

public class MainFragment extends Fragment {
    private UserDBHandler dbHandler;
    private EditText nameET;
    private EditText passwdET;
    private Button btnLogin;
    private Button btnRegister;
    private Button btnShowAll;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_main, container, false);
        init();
        load();
        actions();
        return view;
    }

    private void init() {
        dbHandler = new UserDBHandler(this.getContext());
        nameET = (EditText) view.findViewById(R.id.name);
        passwdET = (EditText) view.findViewById(R.id.password);
        btnLogin = (Button) view.findViewById(R.id.login);
        btnRegister = (Button) view.findViewById(R.id.register);
        btnRegister.setOnClickListener(new RegisterClickListener(getActivity()));
        btnShowAll = (Button) view.findViewById(R.id.show_all_user);
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
                if (dbHandler.isUserInDatabase(new User(0, nameET.getText().toString(), passwdET.getText().toString(), 0))) {
                    dialog.setTitle("Sikeres bejelentkezés");
                    dialog.setMessage("Ön bejelentkezett [" + nameET.getText().toString() + "] névvel!");
                    Drawable drawable = v.getContext().getDrawable(android.R.drawable.ic_dialog_info);
                    assert drawable != null;
                    drawable.setTint(Color.argb(255, 100, 100, 100));
                    dialog.setIcon(drawable);
                    dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().finish();
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
}
