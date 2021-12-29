package com.example.lab2.response;

public class GetCurrPosResponse {
    private int responseCode;
    private String responseMessage;
    private GetCurrPosResponseBody responseBody;

    public GetCurrPosResponse() {
    }

    public GetCurrPosResponse(int responseCode, String responseMessage, GetCurrPosResponseBody responseBody) {
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

    public GetCurrPosResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(GetCurrPosResponseBody responseBody) {
        this.responseBody = responseBody;
    }
}
