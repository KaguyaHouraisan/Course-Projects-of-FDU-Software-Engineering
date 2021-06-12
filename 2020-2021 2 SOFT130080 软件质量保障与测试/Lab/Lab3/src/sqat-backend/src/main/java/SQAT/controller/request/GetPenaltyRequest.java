package SQAT.controller.request;

public class GetPenaltyRequest {
    private String token;
    private String accountNum;

    public GetPenaltyRequest() {
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
