package DataBase1.relation;

import DataBase1.domain.Patient;

public class Patient_Waiting_Info {

    private Patient patient;
    private String targetRegion;

    public Patient_Waiting_Info() {
    }

    public Patient_Waiting_Info(Patient patient, String targetRegion) {
        this.patient = patient;
        this.targetRegion = targetRegion;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getTargetRegion() {
        return targetRegion;
    }

    public void setTargetRegion(String targetRegion) {
        this.targetRegion = targetRegion;
    }
}
