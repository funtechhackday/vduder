package com.example.vduder.vduder.Activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

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

class ImageWizzard
{
    public static Bitmap MakeMagic(Bitmap image1, Bitmap image2, String text1, String text2)
    {
        Bitmap common = ConcateImages(image1, image2);
        DrawRectangle(common);
        DrawText(common, text1, text2);
        return common;
    }

    private static void DrawRectangle(Bitmap common) {

    }

    private static void DrawText(Bitmap common, String text1, String text2)
    {

    }

    private static Bitmap ConcateImages(Bitmap first, Bitmap second)
    {
        int width, height = 0;

        if(first.getWidth() > second.getWidth()) {
            width = first.getWidth() + second.getWidth();
            height = first.getHeight();
        } else {
            width = second.getWidth() + second.getWidth();
            height = first.getHeight();
        }

        Bitmap common = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas comboImage = new Canvas(common);

        comboImage.drawBitmap(first, 0f, 0f, null);
        comboImage.drawBitmap(second, first.getWidth(), 0f, null);

        return common;
    }
}
