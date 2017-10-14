package com.example.vduder.vduder.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vduder.vduder.Core.LogHelper;
import com.example.vduder.vduder.Model.Message;
import com.example.vduder.vduder.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FullInterviewActivity extends AppCompatActivity {

    DatabaseReference database;
    LinearLayout linearLayout;
    Button vkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_interview);

        database = FirebaseDatabase.getInstance().getReference();

        linearLayout = (LinearLayout) findViewById(R.id.fullInterviewLayout);
        vkButton = (Button) findViewById(R.id.sendToVkButton);
        vkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendSomethingToVk();
            }
        });

        Intent intent = getIntent();
        String interviewId = intent.getStringExtra("interviewId");

        LoadMessages(interviewId);
    }

    private void SendSomethingToVk() {
        Toast.makeText(this, "Vk.....", Toast.LENGTH_SHORT).show();
    }

    private void LoadMessages(String interviewId) {
        database
                .child("message")
                .orderByChild("interviewId")
                .equalTo(interviewId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<Message> messages = new ArrayList<Message>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            Message message = snapshot.getValue(Message.class);
                            messages.add(message);
                        }
                        ShowMessages(messages);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        LogHelper.ShowDataBaseError("error. message loading");
                    }
                });
    }

    private void ShowMessages(ArrayList<Message> messages)
    {
        for (Message message : messages)
        {
            TextView view = new TextView(this);
            view.setText(message.text);
            if (message.typeMessage.equals("dude"))
            {
                view.setGravity(Gravity.RIGHT);
            }
            else
            {
                view.setGravity(Gravity.LEFT);
            }
            linearLayout.addView(view);
        }
    }
}
