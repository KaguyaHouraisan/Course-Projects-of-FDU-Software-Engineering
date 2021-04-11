package fudan.se.lab2.controller.request;

public class GetContributRequest {
    private String meetingName;
    private Long contributionID;
    public GetContributRequest(){}

    public Long getContributionID() {
        return contributionID;
    }

    public void setContributionID(Long contributionID) {
        this.contributionID = contributionID;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName=meetingName;
    }
    public String getMeetingName() {
        return meetingName;
    }
}
