package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    Spinner textwuy,textwu;
    TextView wendu,zi,t1;
    Button bt01,bt02;
    List<acclass> AcClass = new ArrayList<>();//空调类
    List<String> fu1 = new ArrayList<>();//空调类
    List<ac_fuclass> ac_fuclasses = new ArrayList<>();

    List<String> list = new ArrayList<String>();
    List<String> list1 = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initview();
        Acf();
        for (int i = 0; i < AcClass.size(); i++) {
            fu1.add(AcClass.get(i).getLou());
        }
        sqAdapter();

        bt01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = wendu.getText().toString().substring(0,2);
                int c =Integer.parseInt(a);
                if (c >= 32){
                    Toast.makeText(MainActivity2.this, "不能再加了！", Toast.LENGTH_SHORT).show();
                }else{
                    int b =Integer.parseInt(a)+1;
                    wendu.setText(b+"℃");
                    if (b>25){ zi.setText("温度过高"); zi.setTextColor(Color.RED);}else{
                        zi.setText("温度适宜"); zi.setTextColor(Color.parseColor("#009688"));
                    }
                }
            }
        });
        bt02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = wendu.getText().toString().substring(0,2);
                int c =Integer.parseInt(a);
                if (c <= 16){
                    Toast.makeText(MainActivity2.this, "不能再减了！", Toast.LENGTH_SHORT).show();
                }else{
                    int b =Integer.parseInt(a)-1;
                    wendu.setText(b+"℃");
                    if (b<18){ zi.setText("温度过低"); zi.setTextColor(Color.BLUE);}else{
                        zi.setText("温度适宜"); zi.setTextColor(Color.parseColor("#009688"));
                    }
                }
            }
        });
    }


    private void sqAdapter() {

        //第二步：为下拉列表定义一个适配器

        //第四步：将适配器添加到下拉列表上

        //第五步：添加监听器，为下拉列表设置事件的响应
        list1.add("a101");
        list1.add("a102");
        list1.add("a103");
        list1.add("a104");
        list1.add("a105");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fu1);
        //第三步：设置下拉列表下拉时的菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
      /*  textwu.setAdapter(adapter);*/
        textwuy.setAdapter(adapter);
        textwuy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ac_fuclasses = AcClass.get(position).getXinxi();
                list.clear();
                for (int i = 0; i < ac_fuclasses.size(); i++) {
                    list.add(ac_fuclasses.get(i).getQuyu());
                }
                adapter1 = new ArrayAdapter<String>(MainActivity2.this, android.R.layout.simple_spinner_item, list);
                //第三步：设置下拉列表下拉时的菜单样式
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                textwu.setAdapter(adapter1);
                textwu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        t1.setText("气温："+ac_fuclasses.get(position).getQiwen()+"℃");
                        wendu.setText(ac_fuclasses.get(position).getAc()+"℃");
                        int as = Integer.parseInt(ac_fuclasses.get(position).getAc());
                        if (as>25){ zi.setText("温度过热"); zi.setTextColor(Color.BLUE);}else{
                            zi.setText("温度适宜"); zi.setTextColor(Color.parseColor("#009688"));
                        }
                        if (as<=18){ zi.setText("温度过冷"); zi.setTextColor(Color.BLUE);}else{
                            zi.setText("温度适宜"); zi.setTextColor(Color.parseColor("#009688"));
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void initview() {
        wendu=findViewById(R.id.wendu);
        zi=findViewById(R.id.zi);
        bt01=findViewById(R.id.bt01);
        bt02=findViewById(R.id.bt02);
        textwuy=findViewById(R.id.textwuy);
        textwu= findViewById(R.id.textwu);
        t1 = findViewById(R.id.t1);
    }

    private void Acf() {
        List<ac_fuclass> aclist = new ArrayList<>();
        ac_fuclass a1 = new ac_fuclass();
        a1.setAc("21");
        a1.setLiyong("34");
        a1.setQiwen("25");
        a1.setQuyu("a101");
        ac_fuclass a2 = new ac_fuclass();
        a2.setAc("22");
        a2.setLiyong("35");
        a2.setQiwen("23");
        a2.setQuyu("a102");
        aclist.add(a1);
        aclist.add(a2);
        acclass ac = new acclass();
        ac.setLou("西区9楼");
        ac.setXinxi(aclist);
        AcClass.add(ac);
        List<ac_fuclass> aclist1 = new ArrayList<>();
        ac_fuclass a11 = new ac_fuclass();
        a11.setAc("31");
        a11.setLiyong("24");
        a11.setQiwen("19");
        a11.setQuyu("a101");
        ac_fuclass a21 = new ac_fuclass();
        a21.setAc("16");
        a21.setLiyong("47");
        a21.setQiwen("20");
        a21.setQuyu("a102");
        aclist1.add(a11);
        aclist1.add(a21);
        acclass ac1 = new acclass();
        ac1.setLou("西区3楼");
        ac1.setXinxi(aclist1);
        AcClass.add(ac1);
        List<ac_fuclass> aclist11 = new ArrayList<>();
        ac_fuclass a111 = new ac_fuclass();
        a111.setAc("25");
        a111.setLiyong("35");
        a111.setQiwen("22");
        a111.setQuyu("a101");
        ac_fuclass a211 = new ac_fuclass();
        a211.setAc("22");
        a211.setLiyong("34");
        a211.setQiwen("22");
        a211.setQuyu("a102");
        ac_fuclass a2112 = new ac_fuclass();
        a2112.setAc("20");
        a2112.setLiyong("8");
        a2112.setQiwen("22");
        a2112.setQuyu("a103");
        aclist11.add(a111);
        aclist11.add(a211);
        aclist11.add(a2112);
        acclass ac11 = new acclass();
        ac11.setLou("西区1楼");
        ac11.setXinxi(aclist11);
        AcClass.add(ac11);
    }
}