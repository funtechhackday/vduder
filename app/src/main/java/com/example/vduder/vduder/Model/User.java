package com.example.vduder.vduder.Model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by dmitry on 13.10.17.
 */

@IgnoreExtraProperties
public class User {

    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
