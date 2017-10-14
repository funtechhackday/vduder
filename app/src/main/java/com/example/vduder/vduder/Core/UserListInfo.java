package com.example.vduder.vduder.Core;

/**
 * Created by torte on 14.10.2017.
 */

public class UserListInfo {
    public String userId;
    public String userName;
    public String status;

    public UserListInfo() {}

    public UserListInfo(String userId, String userName, String status)
    {
        this.userId = userId;
        this.userName = userName;
        this.status = status;
    }
}
