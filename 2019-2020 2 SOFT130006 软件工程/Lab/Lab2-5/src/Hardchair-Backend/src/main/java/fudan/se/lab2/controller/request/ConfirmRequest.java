package fudan.se.lab2.controller.request;

public class ConfirmRequest {
    private Long evaluationID;
    private String modifyStage;
    private Long conferenceId;
    public ConfirmRequest(){}

    public void setEvaluationID(Long evaluationID) {
        this.evaluationID = evaluationID;
    }

    public Long getEvaluationID() {
        return evaluationID;
    }

    public String getModifyStage() {
        return modifyStage;
    }

    public void setModifyStage(String modifyStage) {
        this.modifyStage = modifyStage;
    }

    public Long getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Long conferenceId) {
        this.conferenceId = conferenceId;
    }
}
