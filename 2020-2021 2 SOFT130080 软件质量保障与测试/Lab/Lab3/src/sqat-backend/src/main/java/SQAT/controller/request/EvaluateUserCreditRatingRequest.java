package SQAT.controller.request;

public class EvaluateUserCreditRatingRequest {
    private String accountNum;
    private String token;

    public EvaluateUserCreditRatingRequest() {
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
