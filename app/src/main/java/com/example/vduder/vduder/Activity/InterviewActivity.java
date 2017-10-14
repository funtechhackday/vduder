package com.example.vduder.vduder.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vduder.vduder.Core.IdGenerator;
import com.example.vduder.vduder.Model.Interview;
import com.example.vduder.vduder.Model.Message;
import com.example.vduder.vduder.Model.Role;
import com.example.vduder.vduder.Model.User;
import com.example.vduder.vduder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InterviewActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private EditText answerEdit;
    private Button btnSend;
    private String myRole;
    private FirebaseUser user;
    private String text;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);

        answerEdit = (EditText) findViewById(R.id.answer_edit);
        btnSend = (Button) findViewById(R.id.btn_send);
        myRole = getIntent().getStringExtra(Role.RoleIntentKey);
        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("interview")
                .orderByChild("id")
                .equalTo("1")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        List<Interview> mShops = new ArrayList<>();
                        for (DataSnapshot noteSnapshot: snapshot.getChildren()){
                            Interview interview = noteSnapshot.getValue(Interview.class);
                            Log.e("log", interview.vdudUserId);
                            mShops.add(interview);
                        }
                        for (Interview inter : mShops) {
                            Log.e("mShops", inter.guestUserId);
                           id = inter.id;
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = answerEdit.getText().toString();
                writeMessage(user.getUid(), id, myRole, text);
            }
        });
    }

    private void writeMessage(String authorId, String interviewId, String typeMessage, String text){
        String id = IdGenerator.GenerateId();
        Message mes = new Message(id,  authorId,  interviewId,  myRole,  text);

        mDatabase.child("message").child(id).setValue(mes);
    }

    private void sendMessage() {
        Log.e("log", "ddd");


    }
}
