package com.example.mohamed_araby.NEWS;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.example.mohamed_araby.fuck.R;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Main.this.setTitle("WELCOME");
        setContentView(R.layout.main1);
        ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.showContextMenu();
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent();
                    intent.setClass(Main.this, MainActivity.class);
                    Main.this.finish();
                    startActivity(intent);
                }
            }
        };
        timer.start();
    }
}
