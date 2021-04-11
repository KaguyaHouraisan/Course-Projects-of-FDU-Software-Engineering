package fudan.se.lab2.controller.request;

public class ReviewMeetingsRequest {
    private String meetingName;
    private String reviewModl;

    public ReviewMeetingsRequest() {
    }

    public void setReviewModl(String reviewModl) {
        this.reviewModl = reviewModl;
    }

    public String getReviewModl() {
        return reviewModl;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getMeetingName() {
        return meetingName;
    }
}
