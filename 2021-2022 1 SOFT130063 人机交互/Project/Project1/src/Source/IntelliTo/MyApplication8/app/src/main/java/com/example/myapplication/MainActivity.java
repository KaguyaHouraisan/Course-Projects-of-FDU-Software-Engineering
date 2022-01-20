package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout top_01,top_02,top_03;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        top_01 = findViewById(R.id.top_01);
        top_02 = findViewById(R.id.top_02);
        top_03 = findViewById(R.id.top_03);
        top_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        top_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AC_Activity.class);
                startActivity(intent);
            }
        });
        top_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,waimai.class);
                startActivity(intent);
            }
        });
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, showactivity.class);
        startActivity(intent);
    }
}