package DataBase1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DailyRegistration implements DateObject{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;
    private Long patientId;
    private Long workId;
    private double temperature;
    private String symptom;
    private String nucleicAcidDetectionResult;//枚举
    private String lifeStatus;//枚举类

    public DailyRegistration(){}

    public DailyRegistration(String date, Long patientId, Long workId, double temperature, String symptom, String nucleicAcidDetectionResult, String lifeStatus) {
        this.date = date;
        this.patientId = patientId;
        this.workId = workId;
        this.temperature = temperature;
        this.symptom = symptom;
        this.nucleicAcidDetectionResult = nucleicAcidDetectionResult;
        this.lifeStatus = lifeStatus;
    }

    public String getNucleicAcidDetectionResult() {
        return nucleicAcidDetectionResult;
    }

    public void setNucleicAcidDetectionResult(String nucleicAcidDetectionResult) {
        this.nucleicAcidDetectionResult = nucleicAcidDetectionResult;
    }

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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getLifeStatus() {
        return lifeStatus;
    }

    public void setLifeStatus(String lifeStatus) {
        this.lifeStatus = lifeStatus;
    }
}
