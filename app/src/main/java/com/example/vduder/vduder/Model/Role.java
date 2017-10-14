package com.example.vduder.vduder.Model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Objects;

//Роль юзера
@IgnoreExtraProperties
public class Role {
    public static final String RoleIntentKey = "role";
    public static final String VdudRole = "dude";
    public static final String GuestRole = "noDude";

    public String ID;
    public String roleType;
    public String userID;

    public Role() {}

    public Role(String roleType, String userID)
    {
        this.roleType = roleType;
        this.userID = userID;
    }

    public static String GetOpponentRole(String role)
    {
        return Objects.equals(role, VdudRole)
                ? GuestRole
                : VdudRole;

    }

}
