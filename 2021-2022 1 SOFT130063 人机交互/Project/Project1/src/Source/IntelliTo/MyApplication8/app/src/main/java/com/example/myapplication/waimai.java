package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class waimai extends AppCompatActivity {
    Button vin, self_serve, auto_deliver;
    TextView text01,text02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waimai);
        vin = findViewById(R.id.vin);
        self_serve = findViewById(R.id.self_serve);
        auto_deliver = findViewById(R.id.auto_deliver);

        text01 = findViewById(R.id.text01);
        text02 = findViewById(R.id.text02);
        Random random = new Random();
        int time_0 = random.nextInt(9);
        int time_1 = random.nextInt(51) + 10;
        text02.setText("1"+time_0+":"+time_1);
        text01.setText("西区"+time_0+1+"#电梯");
        vin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(waimai.this, "催促成功，请您耐心等待", Toast.LENGTH_LONG).show();
            }
        });
        self_serve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(waimai.this, "您的外卖正在返回一楼大堂", Toast.LENGTH_LONG).show();
                vin.setVisibility(View.GONE);
                self_serve.setVisibility(View.GONE);
                auto_deliver.setVisibility(View.VISIBLE);
                text02.setText("--:--");
            }
        });
        auto_deliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(waimai.this, "已经发送请求，请耐心等候~", Toast.LENGTH_LONG).show();
                vin.setVisibility(View.VISIBLE);
                self_serve.setVisibility(View.VISIBLE);
                auto_deliver.setVisibility(View.GONE);
                text02.setText("1"+time_0+":"+time_1);
            }
        });
    }
}