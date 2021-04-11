package fudan.se.lab2.controller.request;

public class GetReviewListRequest {
    private String username;
    private Long meetingID;
    private String meetingName;
    public GetReviewListRequest(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMeetingID(Long meetingID) {
        this.meetingID = meetingID;
    }

    public Long getMeetingID() {
        return meetingID;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName=meetingName;
    }
    public String getMeetingName() {
        return meetingName;
    }
}
