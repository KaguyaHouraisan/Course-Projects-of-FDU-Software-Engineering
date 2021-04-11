package DataBase1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String age;
    private String gender;//枚举类
    private String lifeStatus;//枚举类
    private String conditionRank;//枚举类
    private String disChargeCondition;


    public Patient(){}

    public Patient(String name, String age, String gender, String lifeStatus, String conditionRank, String disChargeCondition) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.lifeStatus = lifeStatus;
        this.conditionRank = conditionRank;
        this.disChargeCondition = disChargeCondition;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLifeStatus() {
        return lifeStatus;
    }

    public void setLifeStatus(String lifeStatus) {
        this.lifeStatus = lifeStatus;
    }

    public String getConditionRank() {
        return conditionRank;
    }

    public void setConditionRank(String conditionRank) {
        this.conditionRank = conditionRank;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisChargeCondition() {
        return disChargeCondition;
    }

    public void setDisChargeCondition(String disChargeCondition) {
        this.disChargeCondition = disChargeCondition;
    }
}
