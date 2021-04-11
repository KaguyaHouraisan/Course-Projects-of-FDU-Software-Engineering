package DataBase1.role;

import DataBase1.constant.*;
import DataBase1.domain.NucleicAcidDetectionReceipt;
import DataBase1.domain.Patient;
import DataBase1.domain.User;
import DataBase1.errorcode.ErrorCode;
import DataBase1.relation.*;
import DataBase1.repository.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AttendingDoctor extends Role{

    public AttendingDoctor(Bed_Patient_Relation_Repository bed_patient_relation_repository, BedRepository bedRepository, DailyRegistrationRepository dailyRegistrationRepository, NucleicAcidDetectionRepository nucleicAcidDetectionRepository, Patient_WardNurse_Relation_Repository patient_wardNurse_relation_repository, PatientRepository patientRepository, UserRepository userRepository, WaitingRepository waitingRepository, NotificationRepository notificationRepository) {
        super(bed_patient_relation_repository, bedRepository, dailyRegistrationRepository, nucleicAcidDetectionRepository, patient_wardNurse_relation_repository, patientRepository, userRepository, waitingRepository, notificationRepository);
    }

    @Override
    public ArrayList<Patient> searchPatientByRegionAndDisChargeConditions(User user, String disChargeCondition, String region) {
        return super.searchPatientByRegionAndDisChargeConditions(user,disChargeCondition,user.getWorkingTreatRegion());
    }

    @Override
    public ArrayList<Patient_Waiting_Info> searchPatientByRegionAndWaitingStatus(User user, String waitingStatus, String region) {
        return super.searchPatientByRegionAndWaitingStatus(user,waitingStatus,user.getWorkingTreatRegion());
    }

    @Override
    public ArrayList<Patient> searchPatientByRegionAndLifeStatus(User user, String lifeStatus, String region) {
        return super.searchPatientByRegionAndLifeStatus(user,lifeStatus,user.getWorkingTreatRegion());
    }

    @Override
    public String modifyPatientConditionRank(User user, Long patientId, String newRank) {
        Patient patient = getPatientRepository().findPatientById(patientId);
        if (patient == null) {
            throw new ErrorCode(ErrorCode.PATIENT_NOT_FOUND_FAILURE);
        }

        Bed_Patient_Relation bed_patient_relation = getBed_patient_relation_repository().findByPatientId(patientId);
        Patient_WardNurse_Relation patient_wardNurse_relation = getPatient_wardNurse_relation_repository().findByPatientId(patientId);

        patient.setConditionRank(newRank);
        getPatientRepository().save(patient);
        if (newRank.equals(bed_patient_relation.getTreatRegion())){
            return "Done";
        }
        else {
            if(allocateResourcesForPatient(patientId,newRank)){

                bed_patient_relation.setPatientId(null);
                getBed_patient_relation_repository().save(bed_patient_relation);
                getPatient_wardNurse_relation_repository().delete(patient_wardNurse_relation);

                allocateForIdleResources(user.getWorkingTreatRegion());
                return "Change Success";
            }
            else {//资源分配失败
                Waiting waiting = new Waiting(patientId,newRank,WaitingType.UnmatchedTreatmentRegion);
                getWaitingRepository().save(waiting);
                return "No resources to allocate in targetRegion";
            }
        }
    }

    @Override
    public String modifyPatientLifeStatus(User user, Long patientId,String lifeStatus) {//lifeStatus 默认 not alive
        Patient patient = getPatientRepository().findPatientById(patientId);
        if (patient == null) {
            throw new ErrorCode(ErrorCode.PATIENT_NOT_FOUND_FAILURE);
        }

        patient.setLifeStatus(LifeStatus.NotAlive);
        Bed_Patient_Relation bed_patient_relation = getBed_patient_relation_repository().findByPatientId(patientId);
        Patient_WardNurse_Relation patient_wardNurse_relation = getPatient_wardNurse_relation_repository().findByPatientId(patientId);

        bed_patient_relation.setPatientId(null);
        getBed_patient_relation_repository().save(bed_patient_relation);
        getPatient_wardNurse_relation_repository().delete(patient_wardNurse_relation);

        patient.setDisChargeCondition(DisChargeCondition.AlreadyDischarge);
        getPatientRepository().save(patient);

        allocateForIdleResources(user.getWorkingTreatRegion());

        return "Operation Done";
    }

    @Override
    public String nucleicAcidDetection(User user, Long patientId, String detectionResult) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);

        Patient patient = getPatientRepository().findPatientById(patientId);
        if (patient==null){
            throw new ErrorCode(ErrorCode.PATIENT_NOT_FOUND_FAILURE);
        }

        NucleicAcidDetectionReceipt nucleicAcidDetectionReceipt = new NucleicAcidDetectionReceipt(dateString,detectionResult,patientId,patient.getConditionRank());
        getNucleicAcidDetectionRepository().save(nucleicAcidDetectionReceipt);

        disChargeConditionCheckForMildPatient(patientId);

        return "success";
    }

    @Override
    public String checkForDisCharge(User user, Long patientId) {
        Patient patient = getPatientRepository().findPatientById(patientId);
        if (patient == null) {
            throw new ErrorCode(ErrorCode.PATIENT_NOT_FOUND_FAILURE);
        }

        if (!patient.getDisChargeCondition().equals(DisChargeCondition.AbleToDisCharge)){
            throw new ErrorCode(ErrorCode.CAN_NOT_DISCHARGE);
        }

        Bed_Patient_Relation bed_patient_relation = getBed_patient_relation_repository().findByPatientId(patientId);
        Patient_WardNurse_Relation patient_wardNurse_relation = getPatient_wardNurse_relation_repository().findByPatientId(patientId);

        bed_patient_relation.setPatientId(null);
        getBed_patient_relation_repository().save(bed_patient_relation);
        getPatient_wardNurse_relation_repository().delete(patient_wardNurse_relation);

        patient.setDisChargeCondition(DisChargeCondition.AlreadyDischarge);
        getPatientRepository().save(patient);

        allocateForIdleResources(user.getWorkingTreatRegion());
        
        return "success";
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
        throw new ErrorCode(ErrorCode.INSUFFICIENT_PERMISSIONS);
    }
}
