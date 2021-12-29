package com.example.lab2.response;

public class PostCurrPosResponse {
    private int responseCode;
    private String responseMessage;
    private PostCurrPosResponseBody responseBody;

    public PostCurrPosResponse() {
    }

    public PostCurrPosResponse(int responseCode, String responseMessage, PostCurrPosResponseBody responseBody) {
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

    public PostCurrPosResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(PostCurrPosResponseBody responseBody) {
        this.responseBody = responseBody;
    }
}
