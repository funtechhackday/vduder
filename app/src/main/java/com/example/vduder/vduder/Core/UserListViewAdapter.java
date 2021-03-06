package com.example.vduder.vduder.Core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vduder.vduder.Activity.UserListActivity;
import com.example.vduder.vduder.R;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by torte on 14.10.2017.
 */
public class UserListViewAdapter extends BaseAdapter
{
    private UserListActivity context;
    private LayoutInflater layoutInflater;
    private ArrayList<UserListInfo> objects;
    private ArrayList<View> views;

    private View.OnClickListener userButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            button.setBackgroundColor(Color.LTGRAY);
            UserListInfo info = (UserListInfo) getItem(button.getId());

            int buttonId = button.getId();
            String userId = info.userId;
            String action = button.getText().toString();
            context.OnListViewItemButtonClicked(buttonId, userId, action);
        }
    };;

    public UserListViewAdapter(UserListActivity parentActivity, ArrayList<UserListInfo> userInfos)
    {
        this.context = parentActivity;
        objects = userInfos;
        layoutInflater = (LayoutInflater) parentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        views = new ArrayList<>(userInfos.size());
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

        Button userButton = resView.findViewById(R.id.userActionButton);
        userButton.setText(info.status);
        userButton.setId(i);
        userButton.setOnClickListener(userButtonClickListener);

        ImageView imageView = (ImageView)resView.findViewById(R.id.userAvatarImageView);
        ImageManager.DownloadUserAvatar(info.userId, imageView, context);
        
        views.add(i, resView);
        return resView;
    }

    private UserListInfo GetUserListInfo(int i) {
        return (UserListInfo) getItem(i);
    }


    public void SetButtonAction(int i, String newStatus, Boolean enable)
    {
        View view = views.get(i);
        Button button = view.findViewById(i);
        button.setText(newStatus);
        button.setEnabled(enable);

        if (newStatus.equals("Интервью!"))
            button.setBackgroundColor(Color.rgb(208, 51, 72));
    }

    public void SetButtonAction(String userId, String newStatus, Boolean enable)
    {
        int i = FindIndex(userId);
        if (i == -1) return;
        SetButtonAction(i, newStatus, enable);
    }

    private int FindIndex(String searchedUserId) {
        for (int i = 0; i < objects.size(); ++i)
        {
            if (Objects.equals(objects.get(i).userId, searchedUserId))
                return i;
        }
        return -1;
    }
}
