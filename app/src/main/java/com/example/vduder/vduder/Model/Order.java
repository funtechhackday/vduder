package com.example.vduder.vduder.Model;

import com.google.firebase.database.IgnoreExtraProperties;

//Заявка на интервью
@IgnoreExtraProperties
public class Order {
    public static final String WaitStatus = "WaitConfirmation";
    public static final String ConfirmedStatus = "Confirmed";
    public static final String CompleteStatus = "Completed";

    public String Id;
    public String fromUserId;
    public String toUserId;
    public String status;

    public Order() {}

    public Order(String id, String fromUserId, String toUserId)
    {
        this.Id = id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;

        this.status = WaitStatus;
    }
}

