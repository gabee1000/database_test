package com.example.gabee1000.myapplication.Listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.gabee1000.myapplication.MainActivity;
import com.example.gabee1000.myapplication.RegisterActivity;

/**
 * Created by gabee1000 on 2017. 04. 13..
 */

public class RegisterClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), RegisterActivity.class);
        v.getContext().startActivity(intent);
    }
}
