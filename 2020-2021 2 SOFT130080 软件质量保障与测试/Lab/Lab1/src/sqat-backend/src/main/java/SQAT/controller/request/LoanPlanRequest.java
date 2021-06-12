package SQAT.controller.request;

public class LoanPlanRequest {
    private String iouNum;
    private String repaymentStatus;
    private String token;

    public LoanPlanRequest() {
    }

    public String getIouNum() {
        return iouNum;
    }

    public void setIouNum(String iouNum) {
        this.iouNum = iouNum;
    }

    public String getRepaymentStatus() {
        return repaymentStatus;
    }

    public void setRepaymentStatus(String repaymentStatus) {
        this.repaymentStatus = repaymentStatus;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
