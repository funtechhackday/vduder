package com.example.vduder.vduder.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vduder.vduder.Core.IdGenerator;
import com.example.vduder.vduder.Model.Message;
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
    private Button btnSend;
    private String myRole;
    private FirebaseUser user;
    private String text;
    private String id;
    private  ArrayList<Message> dbMessageAnswerYourRole;
    private  ArrayList<Message> dbMessageQuestion;
    private int numberAnswerYourRole;
    private int numberQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);

        answerEdit = (EditText) findViewById(R.id.answer_edit);
        answerText = (TextView) findViewById(R.id.answer_text);
        questionText = (TextView) findViewById(R.id.question_text);
        btnSend = (Button) findViewById(R.id.btn_send);
        myRole = getIntent().getStringExtra(Role.RoleIntentKey);
        id = getIntent().getStringExtra("interviewId");
        user = FirebaseAuth.getInstance().getCurrentUser();
        dataBase = FirebaseDatabase.getInstance().getReference();
        InitUserLoading();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = answerEdit.getText().toString();
                numberAnswerYourRole = numberAnswerYourRole + 1;
                if (numberAnswerYourRole < 10) {
                writeMessage(numberAnswerYourRole,user.getUid(), id, myRole, text);
                answerEdit.setText("");
                } else {
                    answerEdit.setText("Оказавшись перед путиным, что ты ему скажешь?");
                }
            }
        });
    }

    private void writeMessage(int number, String authorId, String interviewId, String typeMessage, String text){
        String id = IdGenerator.GenerateId();
        Message mes = new Message(id, number, authorId,  interviewId,  myRole,  text);
       // dataBase.child("interview").child(id).setValue(mes);
        dataBase.child("message").child(id).setValue(mes);
        textRole(myRole);
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
                        roleMessage();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("error role load");
                    }
                });
    }

    private void roleMessage() {
        String itemRole;
        String item;
        if (myRole.equals("dude")) {
            if (dbMessageAnswerYourRole != null && dbMessageAnswerYourRole.size() != 0) {
                itemRole = dbMessageAnswerYourRole.get(dbMessageAnswerYourRole.size() - 1).text;
                if (dbMessageQuestion != null && dbMessageQuestion.size() != 0) {
                    item = dbMessageQuestion.get(dbMessageQuestion.size() - 1).text;
                    answerText.setText(item);
                } else {
                    answerText.setText("Здесь ответ будет");
                }
                questionText.setText(itemRole);
            } else {
                questionText.setText("Зайдай вопрос наконец!");
                answerText.setText("Здесь ответ будет");
            }
        } else {
            if (dbMessageQuestion != null && dbMessageQuestion.size() != 0) {
                itemRole = dbMessageQuestion.get(dbMessageQuestion.size() - 1).text;
                if (dbMessageQuestion != null && dbMessageQuestion.size() != 0) {
                    item = dbMessageQuestion.get(dbMessageQuestion.size() - 1).text;
                    questionText.setText(item);
                } else {
                    questionText.setText("Жди вопрос");
                }
                answerText.setText(itemRole);
            } else {
                questionText.setText("Жди вопрос");
                answerText.setText("Все ждут твой ответ");

            }
        }
    }

}
