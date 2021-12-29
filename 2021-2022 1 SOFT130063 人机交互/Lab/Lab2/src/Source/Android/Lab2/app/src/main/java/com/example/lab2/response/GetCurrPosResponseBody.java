package com.example.lab2.response;

public class GetCurrPosResponseBody {
    private int width;
    private int height;
    private float x;
    private float y;

    public GetCurrPosResponseBody() {
    }

    public GetCurrPosResponseBody(int width, int height, float x, float y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
