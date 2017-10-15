package com.example.vduder.vduder.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.vduder.vduder.Core.ImageManager;
import com.example.vduder.vduder.Core.ImageWizzard;
import com.example.vduder.vduder.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Bitmap image1 = BitmapFactory.decodeResource(getResources(), R.drawable.test_ava);
        Bitmap image2 = BitmapFactory.decodeResource(getResources(), R.drawable.test_ava2);

        ImageManager.UploadUserAvatar("kX4H6gteAibkIzrLLpIMfBo8td13", image1);
        ImageManager.UploadUserAvatar("AQu96QfvLESkbflFUiwFxk955UZ2", image2);
        ImageManager.UploadUserAvatar("LhqfozBmBVX7A2bBJyl9O7DrUpD3", image1);
        ImageManager.UploadUserAvatar("Soepha5QrVQv524VXwqGb8tCTny1", image2);
        ImageManager.UploadUserAvatar("UsLi0wKbBiZBiDsb0hEEMvwCrVu2", image1);
        ImageManager.UploadUserAvatar("rcJMXhRejFZ67cVxAqXXUOHKwds1", image2);
//
//        Bitmap res = ImageWizzard.MakeMagic(image1, image2, "Ivan", "Boris");
//
        ImageView view = (ImageView) findViewById(R.id.testImageView);
        view.setImageBitmap(image1);
//        ImageManager.DownloadInterviewAvatar("1", view, this);
//        ImageManager.UploadInterviewAvatar("0", res);
//        ImageManager.DownloadUserAvatar("1", view, this);
//        view.setImageBitmap(res);
    }
}