package com.example.gabee1000.myapplication.User;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gabee1000.myapplication.Database.UserDBHandler;
import com.example.gabee1000.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabee1000 on 2017. 04. 13..
 */

public class DisplayUsers extends AppCompatActivity {
    private List<User> userList = new ArrayList<>();
    private ListView showAllListView;
    private UserDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_users);
        init();
        load();
        actions();
    }

    private void init() {
        dbHandler = new UserDBHandler(this);
        showAllListView = (ListView) findViewById(R.id.show_all_user_listview);
    }

    private void load() {
        userList.addAll(dbHandler.getAllUsers());
    }

    private void actions() {
        showAllListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return userList.size();
            }

            @Override
            public Object getItem(int position) {
                return userList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent) {
                if (view == null) {
                    LayoutInflater inflater = getLayoutInflater();
                    view = inflater.inflate(R.layout.show_all_list_view_item, parent, false);
                }

                User currentUser = (User) getItem(position);

                TextView name = (TextView) view.findViewById(R.id.lv_item_name);
                TextView age = (TextView) view.findViewById(R.id.lv_item_age);
                name.setText(currentUser.getName());
                age.setText(String.valueOf(currentUser.getAge()));

                return view;
            }
        });
    }
}
