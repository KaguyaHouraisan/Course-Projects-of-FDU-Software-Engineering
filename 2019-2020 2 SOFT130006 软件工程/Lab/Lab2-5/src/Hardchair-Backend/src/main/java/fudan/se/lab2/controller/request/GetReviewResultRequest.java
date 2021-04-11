package fudan.se.lab2.controller.request;

public class GetReviewResultRequest {
    private Long conbutrionID;
    public GetReviewResultRequest(){}

    public Long getConbutrionID() {
        return conbutrionID;
    }

    public void setConbutrionID(Long conbutrionID) {
        this.conbutrionID = conbutrionID;
    }
}
