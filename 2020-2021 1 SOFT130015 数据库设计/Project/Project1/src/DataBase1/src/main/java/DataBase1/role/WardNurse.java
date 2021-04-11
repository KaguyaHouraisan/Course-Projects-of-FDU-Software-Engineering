package DataBase1.role;

import DataBase1.constant.NucleicAcidDetectionResult;
import DataBase1.domain.DailyRegistration;
import DataBase1.domain.NucleicAcidDetectionReceipt;
import DataBase1.domain.Patient;
import DataBase1.domain.User;
import DataBase1.errorcode.ErrorCode;
import DataBase1.relation.Bed_Patient_Info;
import DataBase1.relation.Patient_Waiting_Info;
import DataBase1.relation.Patient_WardNurse_Relation;
import DataBase1.repository.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WardNurse extends Role {

    public WardNurse(Bed_Patient_Relation_Repository bed_patient_relation_repository, BedRepository bedRepository, DailyRegistrationRepository dailyRegistrationRepository, NucleicAcidDetectionRepository nucleicAcidDetectionRepository, Patient_WardNurse_Relation_Repository patient_wardNurse_relation_repository, PatientRepository patientRepository, UserRepository userRepository, WaitingRepository waitingRepository, NotificationRepository notificationRepository) {
        super(bed_patient_relation_repository, bedRepository, dailyRegistrationRepository, nucleicAcidDetectionRepository, patient_wardNurse_relation_repository, patientRepository, userRepository, waitingRepository, notificationRepository);
    }


    @Override
    public ArrayList<Patient> searchPatientByRegionAndDisChargeConditions(User user, String disChargeCondition, String region) {
        List<Patient_WardNurse_Relation> patient_wardNurse_relations = getPatient_wardNurse_relation_repository().findByWardNurseId(user.getWorkId());
        ArrayList<Patient> ret = new ArrayList<>();

        for (Patient_WardNurse_Relation patient_wardNurse_relation: patient_wardNurse_relations){
            Patient patient = getPatientRepository().findPatientById(patient_wardNurse_relation.getPatientId());
            if (patient!=null && patient.getDisChargeCondition().equals(disChargeCondition)){
                ret.add(patient);
            }
        }
        return ret;
    }

    @Override
    public ArrayList<Patient_Waiting_Info> searchPatientByRegionAndWaitingStatus(User user, String waitingStatus, String region) {
        throw new ErrorCode(ErrorCode.INSUFFICIENT_PERMISSIONS);
    }

    @Override
    public ArrayList<Patient> searchPatientByRegionAndLifeStatus(User user, String lifeStatus, String region) {
        List<Patient_WardNurse_Relation> patient_wardNurse_relations = getPatient_wardNurse_relation_repository().findByWardNurseId(user.getWorkId());
        ArrayList<Patient> ret = new ArrayList<>();

        for (Patient_WardNurse_Relation patient_wardNurse_relation: patient_wardNurse_relations){
            Patient patient = getPatientRepository().findPatientById(patient_wardNurse_relation.getPatientId());
            if (patient!=null && patient.getLifeStatus().equals(lifeStatus)){
                ret.add(patient);
            }
        }
        return ret;
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
    public String modifyPatientLifeStatus(User user, Long patientId,String lifeStatus) {
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

    @Override
    public String registerPatientInfo(User user, String date, String detectionResult, String conditionRank, String name, String age, String gender, String lifeStatus) {
        throw new ErrorCode(ErrorCode.INSUFFICIENT_PERMISSIONS);
    }

    @Override
    public String dailyRegister(User user, Long patientId, double temperature, String symptom, String lifeStatus) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        Long nucleicAcidDetectionId = (long)0;
        String nucleicAcidDetectionResult = NucleicAcidDetectionResult.Positive;

        List<NucleicAcidDetectionReceipt> nucleicAcidDetectionReceipts = getNucleicAcidDetectionRepository().findNucleicAcidDetectionReceiptsByPatientId(patientId);
        for (NucleicAcidDetectionReceipt temp : nucleicAcidDetectionReceipts) {
            if (temp.getId() > nucleicAcidDetectionId) {
                nucleicAcidDetectionId = temp.getId();
                nucleicAcidDetectionResult = temp.getResult();
            }
        }

        DailyRegistration dailyRegistration = new DailyRegistration(dateString, patientId, user.getWorkId(), temperature, symptom, nucleicAcidDetectionResult, lifeStatus);
        getDailyRegistrationRepository().save(dailyRegistration);

        //检查出院条件
        disChargeConditionCheckForMildPatient(patientId);
        return dateString + " : " + patientId + " : " + temperature + "℃, " + symptom + ", " + nucleicAcidDetectionResult + " (ID: " + nucleicAcidDetectionId + ") " + ", " + lifeStatus + ". \n";
    }


}
