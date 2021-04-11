package fudan.se.lab2.controller.request;

/**
 * @author LRS
 */
public class FindUserByRealnameRequest {
    private String realname;
    private String meetingName;

    public FindUserByRealnameRequest() {}

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
