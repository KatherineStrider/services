package com.example.kate.serviceforload;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.InputStream;

/**
 * Created by Kate on 24.05.2017.
 */

public class ServiceForLoadImage extends Service {

    public static final String URL = "url";
    private static ImageView img;
    private static ProgressBar pBar;
    String url;
    Bitmap bitmap;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        url = intent.getStringExtra(URL);
        MyTask myTask = new MyTask();
        myTask.execute();
        return super.onStartCommand(intent, flags, startId);
    }

    public static void setImageView(ImageView imageView){
        img = imageView;
    }

    public static void setProgressBar(ProgressBar progressBar){
        pBar = progressBar;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class MyTask extends AsyncTask {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] params) {

            bitmap = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.d("Ошибка изображения", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {

            if (bitmap == null){
                Toast.makeText(ServiceForLoadImage.this, "Изображение не было загружено", Toast.LENGTH_LONG).show();
                return;
            }
            img.setImageBitmap(bitmap);
            pBar.setVisibility(View.INVISIBLE);
            img.setVisibility(View.VISIBLE);
            super.onPostExecute(o);
        }
    }
}
