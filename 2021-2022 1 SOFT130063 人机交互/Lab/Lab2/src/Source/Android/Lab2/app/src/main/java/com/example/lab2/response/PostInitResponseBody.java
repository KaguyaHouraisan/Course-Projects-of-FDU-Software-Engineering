package com.example.lab2.response;

public class PostInitResponseBody {
    private String info;

    public PostInitResponseBody() {
    }

    public PostInitResponseBody(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
