package com.example.lab2.response;

public class PostInitResponse {
    private int responseCode;
    private String responseMessage;
    private PostInitResponseBody responseBody;

    public PostInitResponse() {
    }

    public PostInitResponse(int responseCode, String responseMessage, PostInitResponseBody responseBody) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseBody = responseBody;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public PostInitResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(PostInitResponseBody responseBody) {
        this.responseBody = responseBody;
    }
}
