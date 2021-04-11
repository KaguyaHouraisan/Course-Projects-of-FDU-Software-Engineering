package DataBase1.relation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bed_Patient_Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;
    private Long bedId;
    private String treatRegion;

    public Bed_Patient_Relation(){}

    public Bed_Patient_Relation(Long patientId, Long bedId, String treatRegion) {
        this.patientId = patientId;
        this.bedId = bedId;
        this.treatRegion = treatRegion;
    }

    public String getTreatRegion() {
        return treatRegion;
    }

    public void setTreatRegion(String treatRegion) {
        this.treatRegion = treatRegion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getBedId() {
        return bedId;
    }

    public void setBedId(Long bedId) {
        this.bedId = bedId;
    }
}
