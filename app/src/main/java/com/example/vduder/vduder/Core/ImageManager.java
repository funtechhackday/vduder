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
    public static void UploadUserAvatar(String userId, Bitmap bitmap)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        StorageReference ref = FirebaseStorage.getInstance().getReference();
        UploadTask uploadTask = ref.child("users").child(userId).putBytes(data);
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

    public static void DownloadUserAvatar(String userId, ImageView view, Context context) {
        StorageReference ref = FirebaseStorage.getInstance().getReference().child("users").child(userId);
        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(ref)
                .into(view);
    }
}
