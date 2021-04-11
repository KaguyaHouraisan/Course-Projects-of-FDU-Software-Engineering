package DataBase1.relation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SamplingInspectionExpertRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long expertId;
    private Long regulatorySingleTaskId;

    public SamplingInspectionExpertRelation() {
    }

    public SamplingInspectionExpertRelation(Long expertId, Long regulatorySingleTaskId) {
        this.expertId = expertId;
        this.regulatorySingleTaskId = regulatorySingleTaskId;
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

    public Long getRegulatorySingleTaskId() {
        return regulatorySingleTaskId;
    }

    public void setRegulatorySingleTaskId(Long regulatoryResultId) {
        this.regulatorySingleTaskId = regulatoryResultId;
    }
}
