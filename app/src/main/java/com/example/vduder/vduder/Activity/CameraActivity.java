package com.example.vduder.vduder.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vduder.vduder.Core.ImageManager;
import com.example.vduder.vduder.Model.Role;
import com.example.vduder.vduder.R;

public class CameraActivity extends AppCompatActivity
{
    private String userId;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Intent intent = getIntent();
        role = intent.getStringExtra("role");
        userId = intent.getStringExtra("userId");

        TextView textView = (TextView)findViewById(R.id.cameraLabelTextView);
        Button button = (Button)findViewById(R.id.cameraStartButton);

        String label;
        if (role.equals(Role.VdudRole))
        {
            label = "Left";
        }
        else
        {
            label = "Right";
        }
        textView.setText(label);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(CameraActivity.this.getPackageManager()) != null) {
                    startActivityForResult(intent, PICK_USER_PROFILE_IMAGE);
                }
            }
        });
    }

    public static final int PICK_USER_PROFILE_IMAGE = 1000;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_USER_PROFILE_IMAGE) {
                if (resultCode == RESULT_OK) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    ImageManager.UploadUserAvatar(userId, photo);

                    Intent intent = new Intent(this, UserListActivity.class);
                    intent.putExtra(Role.RoleIntentKey, role);
                    startActivity(intent);
                }
            }
        }
    }
}
