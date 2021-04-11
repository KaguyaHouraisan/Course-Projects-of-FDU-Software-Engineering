package DataBase1.relation;

import javax.persistence.*;
import java.util.Set;

@Entity
public class RegulatorySingleTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long farmProductMarketId;
    private Long regulatoryTaskId;

    private boolean whetherDone;
    private int score;
    private String doneDate;
    //标识该Task是否是一个抽检的Task
    private boolean ifSampling;

    @ElementCollection
    @Column(columnDefinition = "LONGVARBINARY")
    private Set<SingleCategoryResult> regulatorySingleTaskResult;

    public RegulatorySingleTask() {
    }

    public RegulatorySingleTask(Long farmProductMarketId, Long regulatoryTaskId, boolean whetherDone, int score, String doneDate, boolean ifSampling, Set<SingleCategoryResult> regulatorySingleTaskResult) {
        this.farmProductMarketId = farmProductMarketId;
        this.regulatoryTaskId = regulatoryTaskId;
        this.whetherDone = whetherDone;
        this.score = score;
        this.doneDate = doneDate;
        this.ifSampling = ifSampling;
        this.regulatorySingleTaskResult = regulatorySingleTaskResult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFarmProductMarketId() {
        return farmProductMarketId;
    }

    public void setFarmProductMarketId(Long farmProductMarketId) {
        this.farmProductMarketId = farmProductMarketId;
    }

    public Long getRegulatoryTaskId() {
        return regulatoryTaskId;
    }

    public void setRegulatoryTaskId(Long regulatoryTaskId) {
        this.regulatoryTaskId = regulatoryTaskId;
    }

    public boolean isWhetherDone() {
        return whetherDone;
    }

    public void setWhetherDone(boolean whetherDone) {
        this.whetherDone = whetherDone;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(String doneDate) {
        this.doneDate = doneDate;
    }

    public Set<SingleCategoryResult> getRegulatorySingleTaskResult() {
        return regulatorySingleTaskResult;
    }

    public void setRegulatorySingleTaskResult(Set<SingleCategoryResult> regulatorySingleTaskResult) {
        this.regulatorySingleTaskResult = regulatorySingleTaskResult;
    }

    public boolean isIfSampling() {
        return ifSampling;
    }

    public void setIfSampling(boolean ifSampling) {
        this.ifSampling = ifSampling;
    }
}
