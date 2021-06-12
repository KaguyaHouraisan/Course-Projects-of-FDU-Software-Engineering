package SQAT.controller.request;

public class GetProductRequest {
    private String token;
    private String accountNum;

    public GetProductRequest() {
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
