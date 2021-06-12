package SQAT.controller.request;

public class GetLoanDetailRequest {
    String iouNum;
    String token;

    public GetLoanDetailRequest() {
    }

    public String getIouNum() {
        return iouNum;
    }

    public void setIouNum(String iouNum) {
        this.iouNum = iouNum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
