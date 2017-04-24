package com.example.gabee1000.myapplication.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.gabee1000.myapplication.Database.UserDBHandler;
import com.example.gabee1000.myapplication.Listeners.OnClickButtonListener;
import com.example.gabee1000.myapplication.MainContainerActivity;
import com.example.gabee1000.myapplication.R;
import com.example.gabee1000.myapplication.User.User;

/**
 * Created by gabee1000 on 2017. 04. 24..
 */

public class RegisterFragment extends Fragment {
    private EditText nameET;
    private EditText passwdET;
    private EditText ageET;
    private Button saveBtn;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_register, container, false);
        init();
        actions();
        return view;
    }

    private void init() {
        nameET = (EditText) view.findViewById(R.id.new_name);
        passwdET = (EditText) view.findViewById(R.id.new_password);
        ageET = (EditText) view.findViewById(R.id.new_age);
        saveBtn = (Button) view.findViewById(R.id.save);
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
                User user = new User(dbHandler.getMaxId(), nameET.getText().toString(), passwdET.getText().toString(), Integer.parseInt(ageET.getText().toString()));
                dbHandler.addUser(user);
                clearAllInputText();
                MainContainerActivity activity = (MainContainerActivity) getActivity();
                activity.onButtonClicked(MainContainerActivity.SAVE_BUTTON);
            }
        });
    }

    private void clearAllInputText() {
        nameET.setText("");
        passwdET.setText("");
        ageET.setText("");
    }
}
