package com.example.vduder.vduder.Core;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by torte on 15.10.2017.
 */
public class ImageWizzard
{
    public static Bitmap MakeMagic(Bitmap image1, Bitmap image2, String text1, String text2)
    {
        Bitmap common = ConcateImages(image1, image2);
        DrawRectangle(common);
        DrawText(common, text1, text2);
        return common;
    }

    private static void DrawRectangle(Bitmap common)
    {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        Canvas canvas = new Canvas(common);
        canvas.drawRect(0, 90, common.getWidth(), 125, paint);
    }

    private static void DrawText(Bitmap common, String text1, String text2)
    {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(20);

        Canvas canvas = new Canvas(common);
        canvas.drawText(text1, 10, 109, paint);
        canvas.drawText(text2, 200, 109, paint);
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
