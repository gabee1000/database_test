package com.example.gabee1000.myapplication.User;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        final DisplayUsersAdapter adapter = new DisplayUsersAdapter(this, userList);
        showAllListView.setAdapter(adapter);
        showAllListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, final int position, long id) {
                final User user = userList.get(position);
                String name = userList.get(position).getName();
                AlertDialog dialog = new AlertDialog.Builder(DisplayUsers.this).create();
                dialog.setTitle("Rekord törlése");
                dialog.setMessage("Biztos, hogy törli a(z) [" + name + "] nevű felhasználót az adatbázisból?");
                Drawable drawable = v.getContext().getDrawable(android.R.drawable.ic_delete);
                assert drawable != null;
                dialog.setIcon(drawable);
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "IGEN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.removeUser(user);
                        userList.remove(position);
                        adapter.updateList(userList);
                        Toast.makeText(DisplayUsers.this, "Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NEM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return true;
            }
        });
    }
}
