package com.example.vduder.vduder.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.vduder.vduder.Core.UserListInfo;
import com.example.vduder.vduder.Core.UserListViewAdapter;
import com.example.vduder.vduder.Model.User;
import com.example.vduder.vduder.R;
import com.google.firebase.auth.UserInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserListActivity extends AppCompatActivity {

    private static final String ATTRIBUTE_USER_NAME = "userName";
    private static final String ATTRIBUTE_BUTTON_STATUS = "statusText";

    private static final String[] listViewFrom = { ATTRIBUTE_USER_NAME, ATTRIBUTE_BUTTON_STATUS };
    private static final int[] listViewTo = { R.id.userNameTextView, R.id.userActionButton};

    private ListView userListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        userListView = (ListView) findViewById(R.id.userListView);

        LoadAll();
    }

    private void LoadAll()
    {
        UserListInfo[] users = GetUsers();
        SetViewDataList(users);
    }

    private UserListInfo[] GetUsers() //TODO !!!
    {
        int count = 5;
        UserListInfo[] res = new UserListInfo[count];
        for (int i = 0; i < count; ++i)
        {
            UserListInfo info = new UserListInfo();
            info.userId = "" + i;
            info.userName = "name(" + i + ")";
            info.status = "send";

            res[i] = info;
        }
        return res;
    }

    private void SetViewDataList(UserListInfo[] users)
    {
        UserListViewAdapter adapter = new UserListViewAdapter(
                                                this,
                                                new ArrayList<>(Arrays.asList(users)));
        userListView.setAdapter(adapter);
    }
}