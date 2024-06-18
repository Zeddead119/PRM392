package com.example.exercise18_thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.Looper;

public class ImageDownloader {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final ImageView imageView;

    public ImageDownloader(ImageView imageView) {
        this.imageView = imageView;
    }

    public void downloadImage(String imageUrl) {
        new Thread(() -> {
            Bitmap bitmap = null;
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }

            final Bitmap finalBitmap = bitmap;
            handler.post(() -> {
                if (finalBitmap != null) {
                    imageView.setImageBitmap(finalBitmap);
                }
            });
        }).start();
    }
}
