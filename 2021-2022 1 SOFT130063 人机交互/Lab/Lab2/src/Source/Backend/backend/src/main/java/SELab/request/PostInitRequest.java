package SELab.request;

public class PostInitRequest {

    int groundWidth;
    int groundHeight;
    double currX;
    double currY;

    public PostInitRequest() {
    }

    public PostInitRequest(int groundWidth, int groundHeight, double currX, double currY) {
        this.groundWidth = groundWidth;
        this.groundHeight = groundHeight;
        this.currX = currX;
        this.currY = currY;
    }

    public int getGroundWidth() {
        return groundWidth;
    }

    public void setGroundWidth(int groundWidth) {
        this.groundWidth = groundWidth;
    }

    public int getGroundHeight() {
        return groundHeight;
    }

    public void setGroundHeight(int groundHeight) {
        this.groundHeight = groundHeight;
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
