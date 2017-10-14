package com.example.vduder.vduder.Model;

import android.media.Image;

import com.google.firebase.database.IgnoreExtraProperties;

//Интервью
@IgnoreExtraProperties
public class Interview {
    public String id;
    public String vdudUserId;
    public String guestUserId;
    //public Image image;

    public Interview() {}

    public Interview(String id, String vdudUserId, String guestUserId)
    {
        this.id = id;
        this.vdudUserId = vdudUserId;
        this.guestUserId = guestUserId;
       // this.image = null; //TODO !
    }
}

