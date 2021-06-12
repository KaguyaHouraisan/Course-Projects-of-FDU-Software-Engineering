package SQAT.controller.request;

public class PayPenaltyRequest {
    String token;
    String accountNum;

    public PayPenaltyRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }
}
