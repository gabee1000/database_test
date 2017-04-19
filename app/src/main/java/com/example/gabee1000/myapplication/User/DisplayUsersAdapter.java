package com.example.gabee1000.myapplication.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gabee1000.myapplication.R;

import java.util.List;

/**
 * Created by gabee1000 on 2017. 04. 19..
 */

public class DisplayUsersAdapter extends BaseAdapter {
    private Context context;
    private List<User> userList;

    public DisplayUsersAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.show_all_list_view_item, parent, false);
        }

        User currentUser = (User) getItem(position);

        TextView name = (TextView) view.findViewById(R.id.lv_item_name);
        TextView age = (TextView) view.findViewById(R.id.lv_item_age);
        name.setText(currentUser.getName());
        age.setText(String.valueOf(currentUser.getAge()));

        return view;
    }

    public void updateList(List<User> userList) {
        this.userList = userList;
        this.notifyDataSetChanged();
    }
}
