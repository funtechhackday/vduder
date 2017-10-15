package com.example.vduder.vduder.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vduder.vduder.R;

import java.util.Timer;
import java.util.TimerTask;

public class SoundActivity extends AppCompatActivity {

    private int delay = 10 * 1000;

    Timer timer;
    TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                finish();
            }
        };

        timer.schedule(timerTask, delay);
    }
}
