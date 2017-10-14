package com.example.vduder.vduder.Model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by dmitry on 13.10.17.
 */

@IgnoreExtraProperties
public class User {
    public String ID;
    public String username;
    public String email;
    public String money;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String id, String username, String email, String money) {
        this.ID = id;
        this.username = username;
        this.email = email;
        this.money = money;
    }
}

