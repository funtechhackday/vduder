package com.example.vduder.vduder.Core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.vduder.vduder.R;

import java.util.ArrayList;

/**
 * Created by torte on 14.10.2017.
 */
public class UserListViewAdapter extends BaseAdapter
{
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<UserListInfo> objects;

    public UserListViewAdapter(Context context, ArrayList<UserListInfo> userInfos)
    {
        this.context = context;
        objects = userInfos;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return objects.size();
    }

    @Override
    public Object getItem(int i)
    {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent)
    {
        View resView = view;
        if (resView == null)
        {
            resView = layoutInflater.inflate(R.layout.user_list_item, parent, false);
        }

        UserListInfo info = GetUserListInfo(i);

        ((TextView) resView.findViewById(R.id.userNameTextView)).setText(info.userName);
        ((Button) resView.findViewById(R.id.userActionButton)).setText(info.status);

        return resView;
    }

    private UserListInfo GetUserListInfo(int i) {
        return (UserListInfo) getItem(i);
    }
}
