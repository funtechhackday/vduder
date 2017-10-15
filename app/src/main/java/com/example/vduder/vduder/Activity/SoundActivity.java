package com.example.vduder.vduder.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.TimedText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.vduder.vduder.R;

import java.util.Timer;
import java.util.TimerTask;

public class SoundActivity extends AppCompatActivity {

    private int delay = 5000;

    ImageView imageView;

    Timer timer;
    TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        Intent intent = getIntent();
        Boolean isAdvertisment = intent.getBooleanExtra("isAdv", false);
        setResult(RESULT_OK, intent);


        imageView = (ImageView)findViewById(R.id.soundImage);
        timer = new Timer();

        if (!isAdvertisment)
        {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.budet_dut);
            mp.setLooping(true);
            mp.start();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    mp.stop();
                    SoundActivity.this.finish();
                }
            };
            timer.schedule(timerTask, delay * 2, delay);
        }
        else
        {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.budet_dut); //TODO
            mp.setLooping(true);
            mp.start();

            timerTask = new TimerTask() {
                int counter = 0;

                @Override
                public void run() {
                    if (counter == 0)
                    {
//                        imageView.setImageResource();
                    }
                    else if (counter == 1)
                    {

                    }
                    else
                    {
                        mp.stop();
                        SoundActivity.this.finish();
                    }
                    ++counter;
                }
            };
            timer.schedule(timerTask, delay, delay);
        }
    }
}
