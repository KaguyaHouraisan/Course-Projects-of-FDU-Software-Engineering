package fudan.se.lab2.controller.request;

/**
 * @author Renhao Liu
 */
public class FindMeetingByIdentityRequest {
    private String username;
    private String identity;

    public FindMeetingByIdentityRequest() {

    }

    public FindMeetingByIdentityRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentity() {
        return this.identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
