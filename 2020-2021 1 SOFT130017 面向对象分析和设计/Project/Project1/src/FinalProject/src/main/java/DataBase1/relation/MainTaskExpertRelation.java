package DataBase1.relation;

import DataBase1.constant.Score;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class MainTaskExpertRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long expertId;
    private Long mainTaskId;
    private String mainTaskName;
    private int score;
    private boolean isDone;
    private String deadline;


    public MainTaskExpertRelation(Long expertId, Long mainTaskId, String mainTaskName, int score, boolean isDone, String deadline) {
        this.expertId = expertId;
        this.mainTaskId = mainTaskId;
        this.mainTaskName = mainTaskName;
        this.score = score;
        this.isDone = isDone;
        this.deadline = deadline;
    }

    public MainTaskExpertRelation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpertId() {
        return expertId;
    }

    public void setExpertId(Long expertId) {
        this.expertId = expertId;
    }

    public String getMainTaskName() {
        return mainTaskName;
    }

    public void setMainTaskName(String mainTaskName) {
        this.mainTaskName = mainTaskName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Long getMainTaskId() {
        return mainTaskId;
    }

    public void setMainTaskId(Long mainTaskId) {
        this.mainTaskId = mainTaskId;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
