package SQAT.response;

import SQAT.domain.LoanAccount;

public class GetLoanDetailResponse {
    private boolean flag;
    private int code;
    private String message;
    private LoanAccount data;
    private long count;

    public GetLoanDetailResponse() {
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoanAccount getData() {
        return data;
    }

    public void setData(LoanAccount data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String toString() {
        return "Get Loan Detail Form: " + "code=" + getCode() + " & " + "count=" + getCount() + " & " +
                "flag=" + isFlag() + " & " + "message=" + getMessage() + " & " + "data=" + getData().toString();
    }
}
