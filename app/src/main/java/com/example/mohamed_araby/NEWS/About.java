package com.example.mohamed_araby.NEWS;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mohamed_araby.fuck.R;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ListView listView=(ListView) findViewById(R.id.list);
        String []names={
                "MUhammed Saeed Hosny",
                "Muhammed Abd El Rahman Araby",
                "Muhammed Reda Mansour",
                "Muhammed Abd El Gafar Muhammed ",
                "Muhammed Abd El Samad Helmy ",
                "Muhammed Abd El Fattah Mansour Gabal"
        };

        ArrayAdapter<String> Adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, names);
        listView.setAdapter(Adapter1);
    }
}
