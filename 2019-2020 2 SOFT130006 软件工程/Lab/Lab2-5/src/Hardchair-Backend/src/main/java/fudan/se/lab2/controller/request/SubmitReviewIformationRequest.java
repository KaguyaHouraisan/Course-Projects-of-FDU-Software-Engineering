package fudan.se.lab2.controller.request;

public class SubmitReviewIformationRequest {
    private int score;
    private String reviews;
    private int confidence;
    //此处的conferenceId代表论文Id
    private Long conferenceID;
    private String username;

    public SubmitReviewIformationRequest() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setConferenceID(Long conferenceID) {
        this.conferenceID = conferenceID;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public int getConfidence() {
        return confidence;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setConferenceId(Long conferenceId) {
        this.conferenceID = conferenceId;
    }

    public Long getConferenceId() {
        return conferenceID;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getReviews() {
        return reviews;
    }

}