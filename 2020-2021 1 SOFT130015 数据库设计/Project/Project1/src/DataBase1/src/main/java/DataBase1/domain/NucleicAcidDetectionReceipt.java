package DataBase1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NucleicAcidDetectionReceipt  implements DateObject{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;
    private String result;//枚举类
    private Long patientId;
    private String conditionRank;//枚举类

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getConditionRank() {
        return conditionRank;
    }

    public void setConditionRank(String conditionRank) {
        this.conditionRank = conditionRank;
    }

    public NucleicAcidDetectionReceipt(){}

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public NucleicAcidDetectionReceipt(String date, String result, Long patientId, String conditionRank) {
        this.date = date;
        this.result = result;
        this.patientId = patientId;
        this.conditionRank = conditionRank;
    }
}
