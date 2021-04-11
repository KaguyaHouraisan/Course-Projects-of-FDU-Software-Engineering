package DataBase1.relation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Patient_WardNurse_Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;
    private Long wardNurseId;

    public Patient_WardNurse_Relation() {
    }

    public Patient_WardNurse_Relation(Long patientId, Long wardNurseId) {
        this.patientId = patientId;
        this.wardNurseId = wardNurseId;
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

    public Long getWardNurseId() {
        return wardNurseId;
    }

    public void setWardNurseId(Long wardNurseId) {
        this.wardNurseId = wardNurseId;
    }
}
