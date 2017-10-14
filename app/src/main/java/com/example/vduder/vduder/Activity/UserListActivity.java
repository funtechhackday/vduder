package com.example.vduder.vduder.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.vduder.vduder.Model.User;
import com.example.vduder.vduder.R;

import java.util.ArrayList;
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

        Update();
    }

    private void Update()
    {
        User[] users = GetUsers();
        String[] names = SelectNames(users);
        String[] statuses = GetOrderStatuses(users);
        SetViewDataList(names, statuses);
    }

    private User[] GetUsers() //TODO !!!
    {
        int count = 5;
        User[] res = new User[count];
        for (int i = 0; i < count; ++i)
        {
            User user = new User();
            user.ID = "" + i;
            user.username = "name(" + i + ")";

            res[i] = user;
        }
        return res;
    }

    private String[] GetOrderStatuses(User[] users) //TODO !!!
    {
        return new String[] { "send", "send", "send", "send", "send" };
    }

    private static String[] SelectNames(User[] users)
    {
        String[] res = new String[users.length];
        for (int i = 0; i < users.length; ++i)
        {
            res[i] = users[i].username;
        }
        return res;
    }

    private void SetViewDataList(String[] userNames, String[] buttonStatuses)
    {
        ArrayList<Map<String, Object>> data = CreateListViewAdapterData(userNames, buttonStatuses);
        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.user_list_item,
                                        listViewFrom, listViewTo);
        userListView.setAdapter(sAdapter);
    }

    private static ArrayList<Map<String, Object>> CreateListViewAdapterData(
                                                            String[] userNames,
                                                            String[] buttonStatuses)
    {
        ArrayList<Map<String, Object>> res = new ArrayList<>(userNames.length);

        Map<String, Object> m;
        for (int i = 0; i < userNames.length; ++i)
        {
            m = new HashMap<>();
            m.put(ATTRIBUTE_USER_NAME, userNames[i]);
            m.put(ATTRIBUTE_BUTTON_STATUS, buttonStatuses[i]);
            res.add(m);
        }
        return res;
    }
}
