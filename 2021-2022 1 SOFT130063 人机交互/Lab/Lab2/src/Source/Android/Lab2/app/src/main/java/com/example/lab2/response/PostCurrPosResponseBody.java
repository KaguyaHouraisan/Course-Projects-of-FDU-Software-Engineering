package com.example.lab2.response;

public class PostCurrPosResponseBody {
    private String update;

    public PostCurrPosResponseBody() {
    }

    public PostCurrPosResponseBody(String update) {
        this.update = update;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }
}
