package com.example.vduder.vduder.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vduder.vduder.Core.ImageManager;
import com.example.vduder.vduder.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        Bitmap image1 = BitmapFactory.decodeResource(getResources(), R.drawable.test_ava);
//        Bitmap image2 = BitmapFactory.decodeResource(getResources(), R.drawable.test_ava2);
//
////        ImageManager.UploadUserAvatar("kX4H6gteAibkIzrLLpIMfBo8td13", image1);
////        ImageManager.UploadUserAvatar("AQu96QfvLESkbflFUiwFxk955UZ2", image2);
////        ImageManager.UploadUserAvatar("LhqfozBmBVX7A2bBJyl9O7DrUpD3", image1);
////        ImageManager.UploadUserAvatar("Soepha5QrVQv524VXwqGb8tCTny1", image2);
////        ImageManager.UploadUserAvatar("UsLi0wKbBiZBiDsb0hEEMvwCrVu2", image1);
////        ImageManager.UploadUserAvatar("rcJMXhRejFZ67cVxAqXXUOHKwds1", image2);
//
////
//        Bitmap res = ImageWizzard.MakeMagic(image1, image2, "Ivan", "Boris");
//
//        ImageManager.UploadInterviewAvatar("1", res);
//        ImageManager.UploadInterviewAvatar("1508013307913", res);
//        ImageManager.UploadInterviewAvatar("test", res);
////
//        ImageView view = (ImageView) findViewById(R.id.testImageView);
//        view.setImageBitmap(image1);
////        ImageManager.DownloadInterviewAvatar("1", view, this);
////        ImageManager.UploadInterviewAvatar("0", res);
////        ImageManager.DownloadUserAvatar("1", view, this);
////        view.setImageBitmap(res);

        Button button = (Button)findViewById(R.id.cameraButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(TestActivity.this.getPackageManager()) != null) {
                    startActivityForResult(intent, PICK_USER_PROFILE_IMAGE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_USER_PROFILE_IMAGE) {
                if (resultCode == RESULT_OK) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    //your compressed rotated bitmap here
                    ImageManager.UploadInterviewAvatar("dima", photo);
                }
            }
        }
    }

    public static final int PICK_USER_PROFILE_IMAGE = 1000;
}

