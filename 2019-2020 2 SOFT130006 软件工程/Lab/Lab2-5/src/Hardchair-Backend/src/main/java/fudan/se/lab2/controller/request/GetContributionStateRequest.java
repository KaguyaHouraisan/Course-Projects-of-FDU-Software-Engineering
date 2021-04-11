package fudan.se.lab2.controller.request;

public class GetContributionStateRequest {
    private String meetingName;
    private String username;
    public GetContributionStateRequest(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName=meetingName;
    }
    public String getMeetingName() {
        return meetingName;
    }
}
