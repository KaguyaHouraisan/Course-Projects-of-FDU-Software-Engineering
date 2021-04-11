package DataBase1.relation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ScoreChangeRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long targetId;//市场或者专家Id
    private String recordDate;
    private int scoreChange;
    private String reason;

    public ScoreChangeRecords(Long targetId, String recordDate, int scoreChange, String reason) {
        this.targetId = targetId;
        this.recordDate = recordDate;
        this.scoreChange = scoreChange;
        this.reason = reason;
    }

    public ScoreChangeRecords() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public int getScoreChange() {
        return scoreChange;
    }

    public void setScoreChange(int scoreChange) {
        this.scoreChange = scoreChange;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}