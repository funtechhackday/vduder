package com.example.vduder.vduder.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.vduder.vduder.Core.LogHelper;
import com.example.vduder.vduder.Model.Interview;
import com.example.vduder.vduder.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InterviewListActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private DatabaseReference dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_list);

        gridLayout = (GridLayout) findViewById(R.id.interviewGridView);
        dataBase = FirebaseDatabase.getInstance().getReference();

        InitInterviewLoad();
    }

    private void InitInterviewLoad()
    {
        dataBase
                .child("interview")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            Interview interview = snapshot.getValue(Interview.class);
                            AddInterviewToLayout(interview);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        LogHelper.ShowDataBaseError("error interview load");
                    }
                });
    }

    private void AddInterviewToLayout(final Interview interview) {
        ImageButton view = new ImageButton(this);
        view.setImageResource(R.drawable.test_ava);
        view.setTag(interview.id);
        //view.image = interview;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToFullInterviewView(interview);
            }
        });
        gridLayout.addView(view);
    }

    private void GoToFullInterviewView(Interview interviewId)
    {
        Intent intent = new Intent(this, FullInterviewActivity.class);
        startActivity(intent);
    }
}
