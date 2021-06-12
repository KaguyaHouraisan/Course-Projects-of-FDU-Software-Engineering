package SQAT.response;

public class LoginResponse {
    private String expireTime;
    private String token;

    public LoginResponse() {
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String toString() {
        return "Login Response Form: " + "expireTime=" + getExpireTime() + " & " + "token=" + getToken();
    }
}
