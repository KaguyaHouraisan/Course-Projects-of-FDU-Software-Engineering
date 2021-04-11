package fudan.se.lab2.controller.request;

public class GetConferenceByMeetingIDRequest {
    private String meetingName;
    private Long meetingID;
    public GetConferenceByMeetingIDRequest(){}

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
