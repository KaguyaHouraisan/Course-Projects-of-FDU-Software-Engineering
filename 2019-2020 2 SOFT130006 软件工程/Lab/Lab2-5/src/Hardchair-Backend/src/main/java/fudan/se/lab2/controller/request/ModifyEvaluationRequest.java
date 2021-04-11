package fudan.se.lab2.controller.request;

/**
 * @author LBW
 */
public class ModifyEvaluationRequest {
    private int score;
    private String reviews;
    private int confidence;
    private Long evaluationID;
    private String modifyStage;
    private Long conferenceId;

    public void setModifyStage(String modifyStage) {
        this.modifyStage = modifyStage;
    }

    public String getModifyStage() {
        return modifyStage;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public int getConfidence() {
        return confidence;
    }

    public String getReviews() {
        return reviews;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public Long getEvaluationID() {
        return evaluationID;
    }

    public void setEvaluationID(Long evaluationID) {
        this.evaluationID = evaluationID;
    }

    public void setConferenceId(Long conferenceId) {
        this.conferenceId = conferenceId;
    }

    public Long getConferenceId() {
        return conferenceId;
    }
}
