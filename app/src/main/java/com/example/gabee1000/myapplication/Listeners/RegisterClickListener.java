package com.example.gabee1000.myapplication.Listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.gabee1000.myapplication.MainActivity;
import com.example.gabee1000.myapplication.MainContainerActivity;
import com.example.gabee1000.myapplication.RegisterActivity;

/**
 * Created by gabee1000 on 2017. 04. 13..
 */

public class RegisterClickListener implements View.OnClickListener {
    private Activity activity;

    public RegisterClickListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        MainContainerActivity activity = (MainContainerActivity) this.activity;
        activity.onButtonClicked(MainContainerActivity.REGISTER_BUTTON);
     }
}
