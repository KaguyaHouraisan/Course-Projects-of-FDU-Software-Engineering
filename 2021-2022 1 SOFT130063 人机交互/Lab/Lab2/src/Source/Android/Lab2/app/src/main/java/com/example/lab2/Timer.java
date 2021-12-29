package com.example.lab2;

public class Timer implements Runnable {
    private static final String URL = "http://1.15.221.182:8081/";
    private long time;

    public Timer() {
        time = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (true) {
            if ((MainActivity.state == 2) && (System.currentTimeMillis() - time > 50)) {
                MainActivity.state = 1;

                Util.postCurrPos(URL, 0, 0);
                Util.postCurrPos(URL, 0, 0);
                Util.postCurrPos(URL, 0, 0);
                Util.postCurrPos(URL, 0, 0);
                Util.postCurrPos(URL, 0, 0);
                Util.postCurrPos(URL, 0, 0);
            }
        }
    }

    public void updateTimer() {
        MainActivity.state = 2;
        time = System.currentTimeMillis();
    }
}
