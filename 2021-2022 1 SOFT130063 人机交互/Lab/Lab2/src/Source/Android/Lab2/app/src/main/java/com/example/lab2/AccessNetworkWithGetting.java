package com.example.lab2;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import com.example.lab2.response.GetCurrPosResponse;
import com.example.lab2.response.PostCurrPosResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

class AccessNetworkWithGetting implements Runnable {
    private static final String URL = "http://1.15.221.182:8081/";
    private static final int timeLoop = 100;
    private final ImageView imageView;
    private final Handler h;
    private int num = 0x72;

    public AccessNetworkWithGetting(ImageView imageView, Handler h) {
        this.imageView = imageView;
        this.h = h;
    }

    @Override
    public void run() {
        while (true) {
            if (MainActivity.state == 0) {
                try {
                    Thread.sleep(timeLoop);
                } catch (Exception e) {
                    System.out.println("***Networking.WithGetting.run.0 Sleeping failed. ");
                }

                Bitmap bitmap = Util.downloadFile();
                if (bitmap != null && imageView != null) {
                    Message m = new Message();
                    m.what = 0x71;
                    m.obj = bitmap;
                    h.sendMessage(m);
                    System.out.println("**Networking.WithGetting.run.0 Cloud has a photo. ");
                } else {
                    System.out.println("***Networking.WithGetting.run.0 Cloud has nothing. ");
                }
            } else if (MainActivity.state == 1) {
                String response = Util.getCurrPos(URL);
                GetCurrPosResponse getCurrPosResponse = null;

                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    getCurrPosResponse = objectMapper.readValue(response, GetCurrPosResponse.class);
                } catch (Exception e) {
                    System.out.println("***Networking.WithGetting.run.1 Json failed. ");
                }

                if (getCurrPosResponse == null) {
                    System.out.println("***Networking.WithGetting.run.1 GetCurrPosResponse null exception. ");
                    continue;
                }
                if (getCurrPosResponse.getResponseBody() == null) {
                    System.out.println("***Networking.WithGetting.run.1 GetCurrPosResponseBody null exception. ");
                    continue;
                }
                if (imageView == null) {
                    System.out.println("***Networking.WithGetting.run.1 ImageView null exception. ");
                    continue;
                }

                Message m = new Message();
                m.what = num;
                Point temp = new Point(getCurrPosResponse.getResponseBody().getX(), getCurrPosResponse.getResponseBody().getY());
                m.obj = temp;
                h.sendMessage(m);
                num++;
                System.out.println("**Networking.WithGetting.run.1 Getting new parm " + temp.getX() + " " + temp.getY());
            } else if (MainActivity.state == 2) {
                if (imageView == null) {
                    System.out.println("***Networking.WithGetting.run.2 ImageView null exception. ");
                    continue;
                }

                String response = Util.postCurrPos(URL, MainActivity.dx, MainActivity.dy);
                PostCurrPosResponse postCurrPosResponse = null;

                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    postCurrPosResponse = objectMapper.readValue(response, PostCurrPosResponse.class);
                } catch (Exception e) {
                    System.out.println("***Networking.WithGetting.run.2 Json failed. ");
                }

                if (postCurrPosResponse != null) {
                    if (postCurrPosResponse.getResponseBody() != null) {
                        if (postCurrPosResponse.getResponseBody().getUpdate().equals("success")) {
                            System.out.println("**Networking.WithGetting.run.2 Upload parm succeed " + MainActivity.dx + " " + MainActivity.dy);
                        }
                    } else {
                        System.out.println("***Networking.WithGetting.run.2 PostCurrPosResponseBody null exception. ");
                    }
                } else {
                    System.out.println("***Networking.WithGetting.run.2 PostCurrPosResponse null exception. ");
                }
            }
        }
    }
}
