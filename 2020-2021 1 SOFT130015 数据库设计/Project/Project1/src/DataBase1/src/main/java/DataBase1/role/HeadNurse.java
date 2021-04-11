package DataBase1.role;

import DataBase1.constant.RoleId;
import DataBase1.constant.TreatRegion;
import DataBase1.domain.Patient;
import DataBase1.domain.User;
import DataBase1.errorcode.ErrorCode;
import DataBase1.relation.Bed_Patient_Info;
import DataBase1.relation.Bed_Patient_Relation;
import DataBase1.relation.Patient_Waiting_Info;
import DataBase1.repository.*;
import java.util.ArrayList;
import java.util.List;

public class HeadNurse extends Role {
    public HeadNurse(Bed_Patient_Relation_Repository bed_patient_relation_repository, BedRepository bedRepository, DailyRegistrationRepository dailyRegistrationRepository, NucleicAcidDetectionRepository nucleicAcidDetectionRepository, Patient_WardNurse_Relation_Repository patient_wardNurse_relation_repository, PatientRepository patientRepository, UserRepository userRepository, WaitingRepository waitingRepository, NotificationRepository notificationRepository) {
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

        String workingTreatRegion = user.getWorkingTreatRegion();
        User wardNurse = getUserRepository().findUserByWorkId(nurseId);

        if (wardNurse == null || !wardNurse.getRoleId().equals(RoleId.WardNurse)){
            throw new ErrorCode(ErrorCode.INVALID_WARDNURSE);
        }

        if (getPatient_wardNurse_relation_repository().findByWardNurseId(nurseId).size() != 0) {
            throw new ErrorCode(ErrorCode.CAN_NOT_REMOVE_WARDNURSE_RELATED_TO_PATIENT);
        }

        wardNurse.setWorkingTreatRegion(workingTreatRegion);
        getUserRepository().save(wardNurse);
        allocateForIdleResources(workingTreatRegion);

        return "Success";
    }

    //如果当前护士关联有病人则不能被remove掉
    @Override
    public String removeNurse(User user, Long nurseId) {
        User wardNurse = getUserRepository().findUserByWorkId(nurseId);
        if (wardNurse.getRoleId() != RoleId.WardNurse) {
            throw new ErrorCode(ErrorCode.NOT_WARDNURSE_TO_REMOVE);
        }

        if (getPatient_wardNurse_relation_repository().findByWardNurseId(nurseId).size() != 0) {
            throw new ErrorCode(ErrorCode.CAN_NOT_REMOVE_WARDNURSE_RELATED_TO_PATIENT);
        }

        wardNurse.setWorkingTreatRegion(TreatRegion.NullStatement);
        getUserRepository().save(wardNurse);
        return "Success";
    }

    @Override
    public ArrayList<Bed_Patient_Info> searchBedPatientInfo(User user) {

        List<Bed_Patient_Relation>  bed_patient_relations = getBed_patient_relation_repository().findByTreatRegion(user.getWorkingTreatRegion());

        ArrayList<Bed_Patient_Info> ret = new ArrayList<>();

        for (Bed_Patient_Relation relation: bed_patient_relations){
            if (relation.getPatientId()==null){
                ret.add(new Bed_Patient_Info(getBedRepository().findById((long)(relation.getBedId())),null));
            }
            else {
                ret.add(new Bed_Patient_Info(getBedRepository().findById((long)(relation.getBedId())),getPatientRepository().findPatientById(relation.getPatientId())));
            }
        }

        return ret;
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
