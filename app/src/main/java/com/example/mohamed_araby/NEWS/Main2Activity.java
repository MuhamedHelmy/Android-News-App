package com.example.mohamed_araby.NEWS;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.mohamed_araby.fuck.R;

public class Main2Activity extends AppCompatActivity {

    private  ImageView b3 ;
    private  ImageView b4;
    private  ImageView b5;
    private  ImageView b6;
    private  ImageView b7;
    private  ImageView b8 ;
    private  ImageView b9;
    private  ImageView b10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b3=(ImageView)findViewById(R.id.youm7);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youm7.com"));
                startActivity(browserIntent);
            }
        });
        b4=(ImageView)findViewById(R.id.elmasry);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.almasryalyoum.com"));
                startActivity(browserIntent);
            }
        });
        b5=(ImageView)findViewById(R.id.elahram);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ahram.org.eg"));
                startActivity(browserIntent);
            }
        });
        b6=(ImageView)findViewById(R.id.yahoo);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://login.yahoo.com/?.src=ym&.intl=us&.lang=en-US&.done=https%3a//mail.yahoo.com"));
                startActivity(browserIntent);
            }
        });
        b7=(ImageView)findViewById(R.id.face);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/?stype=lo&jlou=AfcW1EfxxBnxQ_8o1kJ9mTPt4LiuctGjAJPLs1vn-MtPZ1Gxl6i7Kex8l8J5_9lyVr9EiBtEqmsMxrgCrucBFQRzrJnJaxFcrQKqwHsuf-cS9g&smuh=55320&lh=Ac9pR7E6wsHtb8sA"));
                startActivity(browserIntent);
            }
        });
        b8=(ImageView)findViewById(R.id.instgram);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com"));
                startActivity(browserIntent);
            }
        });
        b9=(ImageView)findViewById(R.id.twiter);
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twiter.com"));
                startActivity(browserIntent);
            }
        });


    }



}


