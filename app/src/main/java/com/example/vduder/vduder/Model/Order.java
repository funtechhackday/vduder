package com.example.vduder.vduder.Model;

import com.google.firebase.database.IgnoreExtraProperties;

//Заявка на интервью
@IgnoreExtraProperties
public class Order {
    public final String WaitStatus = "WaitConfirmation";
    public final String ConfirmedStatus = "Confirmed";
    public final String CompleteStatus = "Completed";

    public String ID;
    public String fromUserId;
    public String toUserId;
    public String status;

    public Order() {}

    public Order(String id, String fromUserId, String toUserId)
    {
        this.ID = id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;

        this.status = WaitStatus;
    }
}

