package SQAT.response;

public class CheckIdResponse {
    private int code;
    private long count;
    private Object data;
    private boolean flag;
    private String message;

    public CheckIdResponse() {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
        return "Check ID Form: " + "code=" + getCode() + " & " + "count=" + getCount() + " & " +
                "flag=" + isFlag() + " & " + "message=" + getMessage();
    }
}
