package com.example.vduder.vduder.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.vduder.vduder.Core.IdGenerator;
import com.example.vduder.vduder.Core.UserListInfo;
import com.example.vduder.vduder.Core.UserListViewAdapter;
import com.example.vduder.vduder.Model.Role;
import com.example.vduder.vduder.Model.User;
import com.example.vduder.vduder.R;
import com.google.firebase.auth.UserInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserListActivity extends AppCompatActivity
{
    private String myRole;
    private ListView userListView;
    UserListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        myRole = getIntent().getStringExtra(Role.RoleIntentKey);

        userListView = (ListView) findViewById(R.id.userListView);

        LoadAll();
    }

    private void LoadAll()
    {
        UserListInfo[] users = GetUsers();
        SetViewDataList(users);
    }

    private final String[] buttonCaptions = { "send", "wait", "go"};

    private UserListInfo[] GetUsers() //TODO !!!
    {
        int count = 5;
        UserListInfo[] res = new UserListInfo[count];
        for (int i = 0; i < count; ++i)
        {
            UserListInfo info = new UserListInfo();
            info.userId = "" + i;
            info.userName = "name(" + i + ")";
            info.status = buttonCaptions[i % 3]; //TODO : seam

            res[i] = info;
        }
        return res;
    }

    private void SetViewDataList(UserListInfo[] users)
    {
        adapter = new UserListViewAdapter(
                                                this,
                                                new ArrayList<>(Arrays.asList(users)));
        userListView.setAdapter(adapter);
    }

    public void OnListViewItemButtonClicked(int i, String userId, String status)
    {
        switch (status)
        {
            case "send":
                SendOrder(userId, status);
                adapter.SetButtonAction(i, "wait", false);
                break;
            case "go":
                GoToInterview(userId);
                break;
        }
    }

    private void ActivateInterviewButton(String userId)
    {
        adapter.SetButtonAction(userId, "go", true);
    }

    private void GoToInterview(String userId) {
        Intent intent = new Intent(this, InterviewActivity.class);
        startActivity(intent);
    }

    private void SendOrder(String userId, String status)
	{
        Toast.makeText(this, IdGenerator.GenerateId(), Toast.LENGTH_SHORT).show();
    }
}