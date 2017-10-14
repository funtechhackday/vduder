package com.example.vduder.vduder.Model;

import com.google.firebase.database.IgnoreExtraProperties;

//Роль юзера
@IgnoreExtraProperties
public class Role {
    public final String VdudRole = "vDud";
    public final String GuestRole = "Guest";

    public String ID;
    public String roleType;
    public String userID;

    public Role() {}

    public Role(String id, String roleType, String userID)
    {
        this.ID = id;
        this.roleType = roleType;
        this.userID = userID;
    }
}
