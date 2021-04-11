package DataBase1.relation;

import DataBase1.domain.Bed;
import DataBase1.domain.Patient;

public class Bed_Patient_Info {

    private Bed bed;
    private Patient patient;

    public Bed_Patient_Info(Bed bed, Patient patient) {
        this.bed = bed;
        this.patient = patient;
    }

    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
