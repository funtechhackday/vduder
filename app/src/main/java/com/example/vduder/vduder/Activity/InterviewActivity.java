package com.example.vduder.vduder.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.vduder.vduder.Model.Interview;
import com.example.vduder.vduder.Model.User;
import com.example.vduder.vduder.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InterviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = database;

        myRef.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
