package fudan.se.lab2.controller.request;

/**
 * @author Renhao Liu
 */
public class ContributeRequest {
    private String pcmemberName;
    private String chairName;
    private String region;
    private String email;
    private String conferenceName;

    public ContributeRequest() {

    }

    public ContributeRequest(String PCmemberName, String chairName, String region, String email, String conferenceName) {
        this.pcmemberName = PCmemberName;
        this.chairName = chairName;
        this.region = region;
        this.email = email;
        this.conferenceName = conferenceName;
    }

    public String getpcmemberName() {
        return pcmemberName;
    }

    public void setpcmemberName(String pcmemberName) {
        this.pcmemberName = pcmemberName;
    }

    public String getChairName() {
        return chairName;
    }

    public void setChairName(String chairName) {
        this.chairName = chairName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }
}


