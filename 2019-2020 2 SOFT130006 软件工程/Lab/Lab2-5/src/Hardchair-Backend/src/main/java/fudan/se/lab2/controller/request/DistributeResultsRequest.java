package fudan.se.lab2.controller.request;

public class DistributeResultsRequest {
    private String meetingName;
    private String attitude;
    public DistributeResultsRequest(){}
    public void setAttitude(String attitude) {
        this.attitude = attitude;
    }
    public String getAttitude() {
        return attitude;
    }
    public void setMeetingName(String meetingName) {
        this.meetingName=meetingName;
    }
    public String getMeetingName() {
        return meetingName;
    }
}
