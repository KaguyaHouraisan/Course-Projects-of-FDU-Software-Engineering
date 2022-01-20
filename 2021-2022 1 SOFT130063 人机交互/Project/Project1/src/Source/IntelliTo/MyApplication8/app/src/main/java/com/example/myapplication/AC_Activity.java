package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AC_Activity extends AppCompatActivity {
    Spinner s1, s2;
    ImageView shift;
    int a = 1,b = 1,c = 1;
    TextView t0001,t1,i33,i22,i11;
    Button i01,i02,i03,i04;
    private TimePicker timePicker;
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    Dialog dialog;
    List<acclass> AcClass = new ArrayList<>();//空调类
    List<String> fu1 = new ArrayList<>();//空调类
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac);
        initview();
        sqAdapter();
        click();





        t0001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        Random random = new Random();
        int time_0 = random.nextInt(99);
        int time_1 = random.nextInt(99);
        int time_2 = random.nextInt(99);
        int current_floor_1 = (random.nextInt(5) + 1) * 2;
        int current_floor_2 = (random.nextInt(5) + 1) * 2;
        int current_floor_3 = (random.nextInt(5) + 1) * 2;
        i11.setText("#" + current_floor_1 + "楼\n" + "适宜");
        if (time_0>80){
            i11.setTextColor(Color.RED);
            i11.setText("#" + current_floor_1 + "楼\n" + "拥挤");
        }
        i22.setText("#" + current_floor_2 + "楼\n" + "适宜");
        if (time_1>80){
            i22.setTextColor(Color.RED);
            i22.setText("#" + current_floor_2 + "楼\n" + "拥挤");
        }
        i33.setText("#" + current_floor_3 + "楼\n" + "适宜");
        if (time_2>80){
            i33.setTextColor(Color.RED);
            i33.setText("#" + current_floor_3 + "楼\n" + "拥挤");
        }
        dialog = new Dialog(AC_Activity.this);
        dialog.setContentView(R.layout.dil);
        timePicker = (TimePicker) dialog.findViewById(R.id.tpPicker);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay,
                                      int minute) {
                t0001.setText(hourOfDay + "小时" + minute + "分钟");

            }
        });


    }



    private void click() {

        i01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (a == 1){
                    a = 0;
                    i11.setTextColor(Color.BLUE);
                    i01.setText("取消");
                }else{
                    a = 1;
                    i01.setText("预约");
                    i11.setTextColor(Color.parseColor("#A8A7A7"));

                }



            }
        });
        i02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (b == 1){
                    b = 0;
                    i22.setTextColor(Color.BLUE);
                    i02.setText("取消");
                }else{
                    b = 1;

                    i02.setText("预约");
                    i22.setTextColor(Color.parseColor("#A8A7A7"));
                }
            }
        });
        i03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (c == 1){
                    c = 0;
                    i33.setTextColor(Color.BLUE);
                    i03.setText("取消");
                }else{
                    c = 1;
                    i03.setText("预约");
                    i33.setTextColor(Color.parseColor("#A8A7A7"));

                }
            }
        });
        shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int s1_val = s1.getSelectedItemPosition();
                int s2_val = s2.getSelectedItemPosition();
                s1.setSelection(s2_val);
                s2.setSelection(s1_val);
            }
        });

        i04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (d == 1){
                    d = 0;
                    a = 0;
                    i11.setTextColor(Color.BLUE);
                    i01.setText("取消");
                    i04.setText("取消预约");
                    Toast.makeText(AC_Activity.this, "选择电梯", Toast.LENGTH_SHORT).show();
                }else{
                    d = 1;
                    i04.setText("自动选择预约");
                    a = 1;
                    i01.setText("预约");
                    i11.setTextColor(Color.parseColor("#A8A7A7"));
                }



            }
        });
    }
    int d =1;
    private void sqAdapter() {
        list.add("西区10楼");
        list.add("西区8楼");
        list.add("西区6楼");
        list.add("西区4楼");
        list.add("西区2楼");
        //第二步：为下拉列表定义一个适配器
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        //第三步：设置下拉列表下拉时的菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        s1.setAdapter(adapter);
        s2.setAdapter(adapter);
        //第五步：添加监听器，为下拉列表设置事件的响应
    }

    private void initview() {
        s1 = findViewById(R.id.s1);
        s2 = findViewById(R.id.s2);
        t1 = findViewById(R.id.t01);
        shift = findViewById(R.id.shift);
        i11=findViewById(R.id.i11);
        i22=findViewById(R.id.i22);
        i33=findViewById(R.id.i33);
        i01=findViewById(R.id.i01);
        i02=findViewById(R.id.i02);
        i03=findViewById(R.id.i03);
        i04=findViewById(R.id.i04);
        t0001= findViewById(R.id.t0001);
    }


}
