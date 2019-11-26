package com.example.quoteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button admin_btn;
    Button motivation_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        admin_btn = findViewById(R.id.admin_btn);
        motivation_btn = findViewById(R.id.motivation_btn);

        admin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Admin.class);
                startActivity(intent);
            }
        });



        motivation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Motivation.class);
                startActivity(i);
            }
        });
    }
}
