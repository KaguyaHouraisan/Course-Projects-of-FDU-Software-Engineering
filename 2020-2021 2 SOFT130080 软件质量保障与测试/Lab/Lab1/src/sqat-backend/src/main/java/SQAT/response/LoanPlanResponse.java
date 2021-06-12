package SQAT.response;

import SQAT.domain.LoanPlan;

public class LoanPlanResponse {
    private int code;
    private long count;
    private LoanPlan[] data;
    private boolean flag;
    private String message;

    public LoanPlanResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public LoanPlan[] getData() {
        return data;
    }

    public void setData(LoanPlan[] data) {
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "Loan Plan Form: " + "code=" + getCode() + " & " + "count=" + getCount() + " & " +
                "flag=" + isFlag() + " & " + "message=" + getMessage() + " & " + "data=" + getData();
    }
}
