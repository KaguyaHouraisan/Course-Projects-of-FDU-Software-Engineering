package fudan.se.lab2.controller.request;

public class VerifyMeetingsRequest {
    private String meetingName;
    private String attitude;
    public VerifyMeetingsRequest(){}
    public void setAttitude(String attitude) {
        this.attitude = attitude;
    };
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
