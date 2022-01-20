package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class showactivity extends AppCompatActivity {
    //新建一个timer
    private final Timer timer = new Timer();
    private TimerTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showactivity);
        //对该定时器
        task = new TimerTask() {
            @Override
            public void run() {
                finish();

            }
        };

        timer.schedule(task, 1300,1000);//每一秒运行该定时器一次
    }
    @Override
    protected void onStop() {
        //在本Activity销毁时终止该计时器
        timer.cancel();
        super.onStop();
    }
}