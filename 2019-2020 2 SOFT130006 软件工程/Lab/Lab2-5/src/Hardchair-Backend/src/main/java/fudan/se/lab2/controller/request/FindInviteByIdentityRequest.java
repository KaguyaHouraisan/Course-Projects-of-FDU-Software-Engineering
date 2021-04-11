package fudan.se.lab2.controller.request;

public class FindInviteByIdentityRequest {
    private String username;
    private String identity;

    public FindInviteByIdentityRequest() {

    }

    public FindInviteByIdentityRequest(String username) {
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
