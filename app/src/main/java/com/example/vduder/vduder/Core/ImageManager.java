package com.example.vduder.vduder.Core;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.vduder.vduder.Core.LogHelper;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class ImageManager
{
    private static final String userDir = "users";
    private static final String interviewDir = "interviews";

    private static StorageReference storage = FirebaseStorage.getInstance().getReference();

    public static void UploadUserAvatar(String userId, Bitmap bitmap)
    {
        UploadImage(userDir, userId, bitmap);
    }

    private static void UploadImage(String dir, String fileName, Bitmap bitmap)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = storage.child(dir).child(fileName).putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                LogHelper.ShowDataBaseError("error upload image");
            }
        })
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //...
                    }
                });
    }

    public static void UploadInterviewAvatar(String interviewId, Bitmap bitmap)
    {
        UploadImage(interviewDir, interviewId, bitmap);
    }

    public static void DownloadUserAvatar(String userId, ImageView view, Context context) {
        DownloadImage(userDir, userId, view, context);
    }

    public static void DownloadInterviewAvatar(String interviewId, ImageView view, Context context) {
        DownloadImage(interviewDir, interviewId, view, context);
    }

    private static void DownloadImage(String dir, String fileName, ImageView view, Context context)
    {
        StorageReference ref = storage.child(dir).child(fileName);
        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(ref)
                .into(view);
    }
}
