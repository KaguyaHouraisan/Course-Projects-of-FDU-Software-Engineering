package SELab.request;

public class PostCurrPosRequest {

    private double currX;
    private double currY;

    public PostCurrPosRequest() {
    }

    public PostCurrPosRequest(double currX, double currY) {
        this.currX = currX;
        this.currY = currY;
    }


    public double getCurrX() {
        return currX;
    }

    public void setCurrX(double currX) {
        this.currX = currX;
    }

    public double getCurrY() {
        return currY;
    }

    public void setCurrY(double currY) {
        this.currY = currY;
    }
}
