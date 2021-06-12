package SQAT.controller.request;

public class AutoPayLoanRequest {
    String token;
    String currentDate;

    public AutoPayLoanRequest(){}

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
