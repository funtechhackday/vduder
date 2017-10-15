package com.example.vduder.vduder.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vduder.vduder.Core.IdGenerator;
import com.example.vduder.vduder.Model.Interview;
import com.example.vduder.vduder.Model.Message;
import com.example.vduder.vduder.Model.Order;
import com.example.vduder.vduder.Model.Role;
import com.example.vduder.vduder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InterviewActivity extends AppCompatActivity {

    private DatabaseReference dataBase;
    private EditText answerEdit;
    private TextView answerText;
    private TextView questionText;
    private TextView finalText;
    private Button btnSend;
    private String myRole;
    private FirebaseUser user;
    private String text;
    private String id;
    private  ArrayList<Message> dbMessageAnswerYourRole;
    private  ArrayList<Message> dbMessageQuestion;
    private int numberAnswerYourRole;
    private int numberQuestion;
    private String userId;
    private String noDudeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);

        answerEdit = (EditText) findViewById(R.id.answer_edit);
        answerText = (TextView) findViewById(R.id.answer_text);
        questionText = (TextView) findViewById(R.id.question_text);
        finalText = (TextView) findViewById(R.id.final_text);
        btnSend = (Button) findViewById(R.id.btn_send);
        myRole = getIntent().getStringExtra(Role.RoleIntentKey);
        id = getIntent().getStringExtra("interviewId");
       // id = "1508046731037";
        user = FirebaseAuth.getInstance().getCurrentUser();
        dataBase = FirebaseDatabase.getInstance().getReference();
        InitUserLoading();
        getUsersId();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = answerEdit.getText().toString();
                numberAnswerYourRole = numberAnswerYourRole + 1;
                writeMessage(numberAnswerYourRole,user.getUid(), id, myRole, text);
                answerEdit.setText("");
                if (numberAnswerYourRole == 3) {
                    Intent intent = new Intent(InterviewActivity.this, SoundActivity.class);
                    intent.putExtra("isAdv", true);
                    startActivityForResult(intent, 1234);
                }

            }
        });
    }

    private void writeMessage(int number, String authorId, String interviewId, String typeMessage, String text){
        String id = IdGenerator.GenerateId();
        Message mes = new Message(id, number, authorId,  interviewId,  myRole,  text);
       // dataBase.child("interview").child(id).setValue(mes);
        dataBase.child("message").child(id).setValue(mes);
        if (numberAnswerYourRole == 5 && myRole.equals("dude")){
            goToTheInterview();
        }
        if (numberAnswerYourRole == 5 && myRole.equals("noDude")){
            goToTheInterviewWithDelete();
        }
    }

    private void textRole(String role) {
        if(role.equals("dude")) {
            questionText.setText(text);
        } else {
            answerText.setText(text);
        }
    }

    private void InitUserLoading()
    {
        dataBase
                .child("message")
                .orderByChild("interviewId")
                .equalTo(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<Message> messagesRole = new ArrayList<>();
                        ArrayList<Message> messagesNoRole = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            Message message = snapshot.getValue(Message.class);
                            if (message.typeMessage.equals(myRole)) {
                                messagesRole.add(message);
                            } else {
                                messagesNoRole.add(message);
                            }
                        }
                        if (messagesRole != null) {
                            numberAnswerYourRole = messagesRole.size();
                            dbMessageAnswerYourRole = messagesRole;
                        }
                        if (messagesNoRole != null) {
                            numberQuestion = messagesNoRole.size();
                            dbMessageQuestion = messagesNoRole;
                        }
                        if (myRole.equals("dude")) {
                            dudeRole();
                        } else {
                            noDudeRole();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("error role load");
                    }
                });
    }


    private void dudeRole() {
        String itemRole;
        String item;
            if (dbMessageAnswerYourRole != null && dbMessageAnswerYourRole.size() != 0) {
                itemRole = dbMessageAnswerYourRole.get(dbMessageAnswerYourRole.size() - 1).text;
                if (dbMessageQuestion != null && dbMessageQuestion.size() != 0) {
                    item = dbMessageQuestion.get(dbMessageQuestion.size() - 1).text;
                    answerText.setText(item);
                } else {
                    answerText.setText("Жди ответ");
                }
                questionText.setText(itemRole);
                if (size()) {
                    visibleView(View.VISIBLE);
                } else {
                    visibleView(View.INVISIBLE);
                }
            } else {
                questionText.setText("Зайдай вопрос наконец!");
                answerText.setText("Здесь ответ будет");
            }
    }
    private void noDudeRole() {
        String itemRole;
        String item;
        if (dbMessageQuestion != null && dbMessageQuestion.size() != 0) {
            itemRole = dbMessageQuestion.get(dbMessageQuestion.size() - 1).text;
            if (dbMessageAnswerYourRole != null && dbMessageAnswerYourRole.size() != 0) {
                item = dbMessageAnswerYourRole.get(dbMessageAnswerYourRole.size() - 1).text;
                questionText.setText(item);
            }
            answerText.setText(itemRole);
            if (size()) {
                visibleView(View.INVISIBLE);
            } else {
                visibleView(View.VISIBLE);
            }
        } else {
            questionText.setText("Жди вопрос");
            answerText.setText("Все ждут твой ответ");
            visibleView(View.INVISIBLE);
        }
    }
     private void visibleView(int visible) {
         answerEdit.setVisibility(visible);
         btnSend.setVisibility(visible);
     }
    private boolean putin() {
        return dbMessageAnswerYourRole != null
                && dbMessageAnswerYourRole.get(dbMessageAnswerYourRole.size() - 1).number+1 == 2;
    }
    private boolean size() {
        return dbMessageQuestion.size() == dbMessageAnswerYourRole.size();
    }

    private void getUsersId()
    {
        dataBase
                .child("interview")
                .orderByChild("id")
                .equalTo(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<Interview> interviews = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            Interview interview = snapshot.getValue(Interview.class);
                            interviews.add(interview);
                        }
                        userId = interviews.get(0).vdudUserId;
                        noDudeId = interviews.get(0).guestUserId;
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("error role load");
                    }
                });
    }
    private void  goToTheInterview() {
        Intent intent = new Intent(this, FullInterviewActivity.class);
        intent.putExtra("interviewId", id);
        startActivity(intent);
    }
    private void  goToTheInterviewWithDelete() {
        Intent intent = new Intent(this, FullInterviewActivity.class);
        intent.putExtra("interviewId", id);
        Order.RemoveFromDataBase(userId, noDudeId);
        startActivity(intent);
    }

}
