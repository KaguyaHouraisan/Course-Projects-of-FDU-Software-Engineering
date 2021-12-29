package com.example.lab2;

import android.widget.ImageView;
import com.example.lab2.response.PostInitResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AccessNetworkWithInit implements Runnable {
    private static final String URL = "http://1.15.221.182:8081/";
    private final ImageView imageView;

    public AccessNetworkWithInit(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public void run() {
        try {
            String response = Util.postInit(URL, imageView.getWidth(), imageView.getHeight(), 0, 0);
            ObjectMapper objectMapper = new ObjectMapper();
            PostInitResponse postInitResponse = objectMapper.readValue(response, PostInitResponse.class);
            if (postInitResponse.getResponseBody().getInfo().equals("ground exist")) {
                System.out.println("**Networking.WithInit.run Succeed " + imageView.getWidth() + " " + imageView.getHeight());
            }
        } catch (Exception e) {
            System.out.println("***Networking.WithInit.run Failed. ");
        }
    }
}
