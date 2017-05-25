package com.example.kate.serviceforload;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        Intent intent = new Intent(MainActivity.this, ServiceForLoadImage.class);
        intent.putExtra(ServiceForLoadImage.URL, "http://www.topnews.ru/upload/topka/2016/10/a0e0b729/a0e0b729_1.jpg");

        ServiceForLoadImage.setImageView(image);
        ServiceForLoadImage.setProgressBar(progressBar);
        MainActivity.this.startService(intent);
    }

    private void initView(){
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        image = (ImageView) findViewById(R.id.imageView);
    }

}
