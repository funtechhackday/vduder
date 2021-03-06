package com.example.vduder.vduder.Model;

import com.google.firebase.database.IgnoreExtraProperties;

//Сообщения
@IgnoreExtraProperties
public class Message {
    public String ID;
    public int number;
    public String authorId;
    public String interviewId;
    public String typeMessage;
    public String text;

    public Message() {}

    public Message(String id, int number,String authorId, String interviewId, String typeMessage, String text)
    {
        this.ID = id;
        this.number = number;
        this.authorId = authorId;
        this.interviewId = interviewId;
        this.typeMessage = typeMessage;
        this.text = text;
    }
}

