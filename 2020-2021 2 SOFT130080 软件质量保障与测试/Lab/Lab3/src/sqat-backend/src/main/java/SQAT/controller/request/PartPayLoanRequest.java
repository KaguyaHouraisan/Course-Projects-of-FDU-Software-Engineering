package SQAT.controller.request;

public class PartPayLoanRequest {

    String iouNum;
    String token;
    String updateTime;
    double partialRepayment;

    public PartPayLoanRequest(){}

    public double getPartialRepayment() {
        return partialRepayment;
    }

    public void setPartialRepayment(double partialRepayment) {
        this.partialRepayment = partialRepayment;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
