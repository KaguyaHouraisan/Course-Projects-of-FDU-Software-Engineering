package fudan.se.lab2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Evaluation")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long evaluationId;
    //，-2 到 2 分且不能给0分。4个评分分别对应4个状态 ：-2 -> reject， -1 -> weak reject， 1 -> weak accept， 2 -> accept
    private int score;
    //长度限制800个字符
    private String reviews;
    //一种四种：very low ,low , high ,very high
    private int confidence;
    private String pcmemberName;
    private String title;
    private String digest;
    //该pcmember审稿状态，pending（未审稿），finish（已审稿）
    private String reviewStage;
    //修改状态(unmodify/modify)
    private String modifyStage;

    public Evaluation(){}
    public Evaluation(String reviews, int score, int confidence, String title, String digest){
        this.confidence = confidence;
        this.digest = digest;
        this.score = score;
        this.title = title;
        this.reviews = reviews;
        this.modifyStage = "unmodify";
    }

    public void setReviewStage(String reviewStage) {
        this.reviewStage = reviewStage;
    }

    public String getReviewStage() {
        return reviewStage;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getDigest() {
        return digest;
    }

    public void setPcmemberName(String pcmemberName) {
        this.pcmemberName = pcmemberName;
    }

    public String getPcmemberName() {
        return pcmemberName;
    }

    public Long getEvaluationId() {
        return evaluationId;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getModifyStage() {
        return modifyStage;
    }

    public void setModifyStage(String modifyStage) {
        this.modifyStage = modifyStage;
    }
}
