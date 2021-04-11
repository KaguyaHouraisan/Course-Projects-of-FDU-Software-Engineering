package fudan.se.lab2.controller.request;

public class GetOldResultRequest {
    private Long paperId;
    private String pcMemberName;
    public GetOldResultRequest(){}
    public GetOldResultRequest(Long paperId,String pcMemberName) {
        this.paperId=paperId;
        this.pcMemberName=pcMemberName;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public String getPcMemberName() {
        return pcMemberName;
    }

    public void setPcMemberName(String pcMemberName) {
        this.pcMemberName = pcMemberName;
    }
}
