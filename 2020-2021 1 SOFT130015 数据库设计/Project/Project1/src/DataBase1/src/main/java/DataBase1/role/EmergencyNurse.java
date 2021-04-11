package DataBase1.role;

import DataBase1.constant.DisChargeCondition;
import DataBase1.constant.WaitingType;
import DataBase1.domain.Bed;
import DataBase1.domain.NucleicAcidDetectionReceipt;
import DataBase1.domain.Patient;
import DataBase1.domain.User;
import DataBase1.errorcode.ErrorCode;
import DataBase1.relation.*;
import DataBase1.repository.*;
import java.util.ArrayList;

public class EmergencyNurse extends Role{

    public EmergencyNurse(Bed_Patient_Relation_Repository bed_patient_relation_repository, BedRepository bedRepository, DailyRegistrationRepository dailyRegistrationRepository, NucleicAcidDetectionRepository nucleicAcidDetectionRepository, Patient_WardNurse_Relation_Repository patient_wardNurse_relation_repository, PatientRepository patientRepository, UserRepository userRepository, WaitingRepository waitingRepository, NotificationRepository notificationRepository) {
        super(bed_patient_relation_repository, bedRepository, dailyRegistrationRepository, nucleicAcidDetectionRepository, patient_wardNurse_relation_repository, patientRepository, userRepository, waitingRepository, notificationRepository);
    }


    @Override
    public ArrayList<Patient> searchAllPatientThisRegion(String region) {
        throw new ErrorCode(ErrorCode.INSUFFICIENT_PERMISSIONS);
    }

    @Override
    public ArrayList<User> searchHeadNurse(User user) {
        throw new ErrorCode(ErrorCode.INSUFFICIENT_PERMISSIONS);
    }

    @Override
    public ArrayList<User> searchWardNurse(User user) {
        throw new ErrorCode(ErrorCode.INSUFFICIENT_PERMISSIONS);
    }

    @Override
    public String modifyPatientConditionRank(User user, Long patientId, String newRank) {
        throw new ErrorCode(ErrorCode.INSUFFICIENT_PERMISSIONS);
    }

    @Override
    public String modifyPatientLifeStatus(User user, Long patientId,String lifeStatus){
        throw new ErrorCode(ErrorCode.INSUFFICIENT_PERMISSIONS);
    }

    @Override
    public String nucleicAcidDetection(User user, Long patientId, String detectionResult) {
        throw new ErrorCode(ErrorCode.INSUFFICIENT_PERMISSIONS);
    }

    @Override
    public String checkForDisCharge(User user, Long patientId) {
        throw new ErrorCode(ErrorCode.INSUFFICIENT_PERMISSIONS);
    }

    @Override
    public String addWardNurse(User user, Long nurseId) {
        throw new ErrorCode(ErrorCode.INSUFFICIENT_PERMISSIONS);
    }

    @Override
    public String removeNurse(User user, Long nurseId) {
        throw new ErrorCode(ErrorCode.INSUFFICIENT_PERMISSIONS);

    }

    @Override
    public ArrayList<Bed_Patient_Info> searchBedPatientInfo(User user) {
        throw new ErrorCode(ErrorCode.INSUFFICIENT_PERMISSIONS);
    }

    //注意时间格式
    @Override
    public String registerPatientInfo(User user, String date, String detectionResult, String conditionRank, String name, String age, String gender, String lifeStatus) {
        Patient patient = new Patient(name, age, gender, lifeStatus, conditionRank, DisChargeCondition.UnAbleToDisCharge);
        getPatientRepository().save(patient);
        NucleicAcidDetectionReceipt nucleicAcidDetectionReceipt = new NucleicAcidDetectionReceipt(date, detectionResult, patient.getId(), conditionRank);
        getNucleicAcidDetectionRepository().save(nucleicAcidDetectionReceipt);

        boolean res = allocateResourcesForPatient(patient.getId(), conditionRank);
        if (res) {
            Bed_Patient_Relation bed_patient_relation = getBed_patient_relation_repository().findByPatientId(patient.getId());
            Patient_WardNurse_Relation patient_wardNurse_relation = getPatient_wardNurse_relation_repository().findByPatientId(patient.getId());
            Bed bed = getBedRepository().findById((long)bed_patient_relation.getBedId());
            return "\nPatient ID : " + patient.getId() + "\nRegion : " + bed_patient_relation.getTreatRegion()
                    + "\nWard ID : " + bed.getWardId() + "\nBed ID : " + bed.getId()
                    + "\nWard Nurse ID : " + patient_wardNurse_relation.getWardNurseId();
        } else {
            Waiting waiting = new Waiting(patient.getId(), conditionRank, WaitingType.IsolationZone);
            getWaitingRepository().save(waiting);
            return "\nUnable to allocate resources for the patient. \n";
        }
    }

    @Override
    public String dailyRegister(User user, Long patientId, double temperature, String symptom, String lifeStatus) {
        throw new ErrorCode(ErrorCode.INSUFFICIENT_PERMISSIONS);
    }
}
