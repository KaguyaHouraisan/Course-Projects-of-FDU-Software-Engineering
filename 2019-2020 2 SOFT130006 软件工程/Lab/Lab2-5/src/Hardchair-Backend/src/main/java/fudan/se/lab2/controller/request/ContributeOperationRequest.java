package fudan.se.lab2.controller.request;

public class ContributeOperationRequest {
    private String conferenceName;
    private String username;

    public ContributeOperationRequest() {

    }

    public ContributeOperationRequest(String conferenceName, String username) {
        this.conferenceName = conferenceName;
        this.username = username;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
