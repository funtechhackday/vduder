package com.example.vduder.vduder.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.vduder.vduder.Core.ImageWizzard;
import com.example.vduder.vduder.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Bitmap image1 = BitmapFactory.decodeResource(getResources(), R.drawable.test_ava);
        Bitmap image2 = BitmapFactory.decodeResource(getResources(), R.drawable.test_ava2);

        Bitmap res = ImageWizzard.MakeMagic(image1, image2, "Ivan", "Boris");

        ImageView view = (ImageView) findViewById(R.id.testImageView);
        view.setImageBitmap(res);
    }
}