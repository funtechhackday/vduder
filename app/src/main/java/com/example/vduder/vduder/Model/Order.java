package com.example.vduder.vduder.Model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//Заявка на интервью
@IgnoreExtraProperties
public class Order {
    public static final String WaitStatus = "Wait";
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

    public static void RemoveFromDataBase(final String user1Id, final String user2Id)
    {
        FirebaseDatabase.getInstance().getReference()
                .child("Orders")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<String> orderIds = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            Order order = snapshot.getValue(Order.class);
                            if (order.Contains(user1Id, user2Id))
                            {
                                orderIds.add(order.Id);
                            }
                        }
                        RemoveFromDataBase(orderIds);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("Error", "error when delete order");
                    }
                });
    }

    //заявка касается указанных пользователей
    private Boolean Contains(final String user1Id, final String user2Id)
    {
        Boolean b1 = user1Id.equals(toUserId) || user1Id.equals(fromUserId);
        Boolean b2 = user2Id.equals(toUserId) || user2Id.equals(fromUserId);
        return b1 && b2;
    }

    private static void RemoveFromDataBase(ArrayList<String> orderIds)
    {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        for (String orderId : orderIds)
        {
            db.child("Orders").child(orderId).removeValue();
        }
    }
}

