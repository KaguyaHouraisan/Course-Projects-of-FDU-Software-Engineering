package fudan.se.lab2.controller.request;
public class GetTopicByMeetingIDRequest {
    private String meetingName;
    private Long meetingID;
    public GetTopicByMeetingIDRequest(){}

    public Long getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(Long meetingID) {
        this.meetingID = meetingID;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName=meetingName;
    }
    public String getMeetingName() {
        return meetingName;
    }
}
