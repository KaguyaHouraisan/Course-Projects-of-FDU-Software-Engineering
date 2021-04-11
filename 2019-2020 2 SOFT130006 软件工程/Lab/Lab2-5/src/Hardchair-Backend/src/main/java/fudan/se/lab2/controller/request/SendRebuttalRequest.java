package fudan.se.lab2.controller.request;

public class SendRebuttalRequest {
    Long paperId;
    String rebuttal;

    public void setRebuttal(String rebuttal) {
        this.rebuttal = rebuttal;
    }

    public String getRebuttal() {
        return rebuttal;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public Long getPaperId() {
        return paperId;
    }
}
