package DataBase1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int wardId;
    private String treatRegion;//枚举类

    public Bed(){}

    public Bed(int wardId, String treatRegion) {
        this.wardId = wardId;
        this.treatRegion = treatRegion;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
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
}
