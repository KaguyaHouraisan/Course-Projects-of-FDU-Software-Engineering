package DataBase1.relation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long targetPersonId;

    private Long regulatorySingleTaskId;

    public Notification() {
    }

    public Notification(Long targetPersonId, Long regulatorySingleTaskId) {
        this.targetPersonId = targetPersonId;
        this.regulatorySingleTaskId = regulatorySingleTaskId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTargetPersonId() {
        return targetPersonId;
    }

    public void setTargetPersonId(Long targetPersonId) {
        this.targetPersonId = targetPersonId;
    }

    public Long getRegulatorySingleTaskId() {
        return regulatorySingleTaskId;
    }

    public void setRegulatorySingleTaskId(Long regulatorySingleTaskId) {
        this.regulatorySingleTaskId = regulatorySingleTaskId;
    }
}
