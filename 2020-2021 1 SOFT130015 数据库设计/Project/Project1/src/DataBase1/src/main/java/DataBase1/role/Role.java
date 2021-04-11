package DataBase1.role;

import DataBase1.constant.*;
import DataBase1.domain.*;
import DataBase1.errorcode.ErrorCode;
import DataBase1.relation.*;
import DataBase1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Role {
    private Bed_Patient_Relation_Repository bed_patient_relation_repository;
    private BedRepository bedRepository;
    private DailyRegistrationRepository dailyRegistrationRepository;
    private NucleicAcidDetectionRepository nucleicAcidDetectionRepository;
    private Patient_WardNurse_Relation_Repository patient_wardNurse_relation_repository;
    private PatientRepository patientRepository;
    private UserRepository userRepository;
    private WaitingRepository waitingRepository;
    private NotificationRepository notificationRepository;

    @Autowired
    public Role(Bed_Patient_Relation_Repository bed_patient_relation_repository, BedRepository bedRepository, DailyRegistrationRepository dailyRegistrationRepository, NucleicAcidDetectionRepository nucleicAcidDetectionRepository, Patient_WardNurse_Relation_Repository patient_wardNurse_relation_repository, PatientRepository patientRepository, UserRepository userRepository, WaitingRepository waitingRepository, NotificationRepository notificationRepository) {
        this.bed_patient_relation_repository = bed_patient_relation_repository;
        this.bedRepository = bedRepository;
        this.dailyRegistrationRepository = dailyRegistrationRepository;
        this.nucleicAcidDetectionRepository = nucleicAcidDetectionRepository;
        this.patient_wardNurse_relation_repository = patient_wardNurse_relation_repository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.waitingRepository = waitingRepository;
        this.notificationRepository = notificationRepository;
    }

    public ArrayList<Patient> searchPatientByRegionAndDisChargeConditions(User user, String disChargeCondition, String region){
        ArrayList<Patient> allPatient = searchPatients(region);

        ArrayList<Patient> patients = new ArrayList<>();

        for (Patient patient: allPatient){
            if(patient.getDisChargeCondition().equals(disChargeCondition)){
                patients.add(patient);
            }
        }

        return patients;
    }//查看各区域病人信息并支持不同条件的筛选,根据是否满足出院条件

    public ArrayList<Patient_Waiting_Info> searchPatientByRegionAndWaitingStatus(User user, String waitingStatus, String region){

        ArrayList<Patient> allPatient = searchPatients(region);

        ArrayList<Patient_Waiting_Info> patientsWaitingToMove = new ArrayList<>();
        ArrayList<Patient_Waiting_Info> patientsInRightRegion = new ArrayList<>();

        for (Patient patient: allPatient){
            Waiting waiting = waitingRepository.findByPatientId(patient.getId());
            if(waiting==null){
                patientsInRightRegion.add(new Patient_Waiting_Info(patient,""));
                continue;
            }
            patientsWaitingToMove.add(new Patient_Waiting_Info(patient,waiting.getTargetRegion()));
        }

        if (waitingStatus.equals(WaitingStatus.WaitingToMove)){
            return patientsWaitingToMove;
        }
        else {
            return patientsInRightRegion;
        }
    }//查看各区域病人信息并支持不同条件的筛选,根据是否待转入其他治疗区域

    public ArrayList<Patient> searchPatientByRegionAndLifeStatus(User user, String lifeStatus,String region){

        Patient patient;

        List<Bed_Patient_Relation> searchResult = bed_patient_relation_repository.findByTreatRegion(region);

        ArrayList<Patient> patientRet = new ArrayList<>();
        for (Bed_Patient_Relation relation : searchResult){
            if (relation.getPatientId() != null) {
                patient = patientRepository.findPatientById(relation.getPatientId());
                if (patient.getLifeStatus().equals(lifeStatus)){
                    patientRet.add(patient);
                }
            }
        }
        return patientRet;
     }//查看各区域病人信息并支持不同条件的筛选,根据病人生命状态

    public ArrayList<Patient> searchAllPatientThisRegion(String region){
        return searchPatients(region);
    }//护士长，主治医师

    public ArrayList<User> searchHeadNurse(User user){

        String workingTreatRegion = user.getWorkingTreatRegion();
        List<User> headNurse = userRepository.findByRoleIdAndWorkingTreatRegion(RoleId.HeadNurse,workingTreatRegion);

        return (ArrayList<User>) headNurse;
    }

    public ArrayList<User> searchWardNurse(User user){

        String workingTreatRegion = user.getWorkingTreatRegion();
        List<User> wardNurse = userRepository.findByRoleIdAndWorkingTreatRegion(RoleId.WardNurse,workingTreatRegion);

        return (ArrayList<User>) wardNurse;
    }

    public String modifyPatientConditionRank(User user, Long patientId, String newRank){return null;}//修改病人的病情评级

    public String modifyPatientLifeStatus(User user, Long patientId, String lifeStatus){return null;}//修改病人的生命状态

    public String nucleicAcidDetection(User user, Long patientId,String detectionResult){return null;}//核酸检测

    public String checkForDisCharge(User user, Long patientId){return null;}//判断是否可以出院

    public String addWardNurse(User user,Long nurseId){return null;}//护士长增加本区域护士

    public String removeNurse(User user,Long nurseId){return null;}//护士长移除本区域护士

    public ArrayList<Bed_Patient_Info> searchBedPatientInfo(User user){return null;}//护士长查看本治疗区域的病床信息以及病床的病人信息

    public String registerPatientInfo(User user,
                               String date,
                               String detectionResult,
                               String conditionRank,
                               String name,
                               String age,
                               String gender,
                               String lifeStatus){return null;}//急诊护士登记病人信息


    public String dailyRegister(User user,
                         Long patientId,
                         double temperature,
                         String symptom,
                         String lifeStatus
                         ){return null;}//病房护士负责患者的治疗及每天的信息登记


    private ArrayList<Patient> searchPatients(String region) {

        List<Bed_Patient_Relation> searchResult = bed_patient_relation_repository.findByTreatRegion(region);

        ArrayList<Patient> patientRet = new ArrayList<>();
        for (Bed_Patient_Relation relation : searchResult) {
            if (relation.getPatientId() != null) {
                patientRet.add(patientRepository.findPatientById(relation.getPatientId()));
            }
        }

        return patientRet;
    }

    //arraylist.sort(dateCompare)是把日期从大到小排列
    Comparator dateCompare = new Comparator<DateObject>() {
        @Override
        public int compare(DateObject o1, DateObject o2) {
            // TODO Auto-generated method stub
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = null;
            Date date2 = null;
            try {
                date1 = formatter.parse(o1.getDate());
                date2 = formatter.parse(o2.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(date1.before(date2))
                return 1;
            else return -1;
        }
    };

    public void disChargeConditionCheckForMildPatient(Long patientId){
        Patient patient = getPatientRepository().findPatientById(patientId);
        if (patient==null){
            throw new ErrorCode(ErrorCode.PATIENT_NOT_FOUND_FAILURE);
        }

        ArrayList<DailyRegistration> dailyRegistrations = (ArrayList<DailyRegistration>) getDailyRegistrationRepository().findByPatientId(patientId);
        ArrayList<NucleicAcidDetectionReceipt> nucleicAcidDetectionReceipts = (ArrayList<NucleicAcidDetectionReceipt>) getNucleicAcidDetectionRepository().findNucleicAcidDetectionReceiptsByPatientId(patientId);

        if (dailyRegistrations.size()<3 || nucleicAcidDetectionReceipts.size()<2 || !(patient.getConditionRank().equals(TreatRegion.MildStatement))){
            throw new ErrorCode(ErrorCode.CAN_NOT_DISCHARGE);
        }

        dailyRegistrations.sort(dateCompare);
        nucleicAcidDetectionReceipts.sort(dateCompare);

        boolean temperatureCheck = true;
        boolean nucleicAcidDetectionCheck = true;

        for (int i = 0;i<3;i++){
            if (dailyRegistrations.get(i).getTemperature()>=37.3){
                temperatureCheck = false;
                break;
            }
        }

        for (int i = 0;i<2;i++){
            if (nucleicAcidDetectionReceipts.get(i).getResult().equals(NucleicAcidDetectionResult.Positive)){
                nucleicAcidDetectionCheck = false;
                break;
            }
        }

        if (temperatureCheck && nucleicAcidDetectionCheck){
            patient.setDisChargeCondition(DisChargeCondition.AbleToDisCharge);
            getPatientRepository().save(patient);
            notificationForAttendingDoctor(patientId, patient.getConditionRank());  //一定是轻症
        }

    }


    public Bed_Patient_Relation_Repository getBed_patient_relation_repository() {
        return bed_patient_relation_repository;
    }

    public BedRepository getBedRepository() {
        return bedRepository;
    }

    public DailyRegistrationRepository getDailyRegistrationRepository() {
        return dailyRegistrationRepository;
    }

    public NucleicAcidDetectionRepository getNucleicAcidDetectionRepository() {
        return nucleicAcidDetectionRepository;
    }

    public Patient_WardNurse_Relation_Repository getPatient_wardNurse_relation_repository() {
        return patient_wardNurse_relation_repository;
    }

    public PatientRepository getPatientRepository() {
        return patientRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public WaitingRepository getWaitingRepository() {
        return waitingRepository;
    }

    public NotificationRepository getNotificationRepository() {
        return notificationRepository;
    }

    public boolean allocateResourcesForPatient(long patientId, String treatRegionTo) {
        Patient patient = getPatientRepository().findPatientById(patientId);
        if (patient == null) {
            throw new ErrorCode(ErrorCode.PATIENT_NOT_FOUND_FAILURE);
        }

        Long bedAllocatedId = null;
        List<Bed_Patient_Relation>  bed_patient_relations = getBed_patient_relation_repository().findByTreatRegion(treatRegionTo);
        for (Bed_Patient_Relation temp : bed_patient_relations) {
            if (temp.getPatientId() == null) {
                bedAllocatedId = temp.getBedId();
                break;
            }
        }
        if (bedAllocatedId == null) {
            return false;  //没有可以分配的病床
        }

        Long wardNurseId = null;
        List<User> wardNurses = getUserRepository().findByRoleIdAndWorkingTreatRegion(RoleId.WardNurse, treatRegionTo);
        for (User temp : wardNurses) {
            List<Patient_WardNurse_Relation> patient_wardNurse_relations = getPatient_wardNurse_relation_repository().findByWardNurseId(temp.getWorkId());
            if (patient_wardNurse_relations.size() < getMaxPatientNumberOfAWardNurseAbleToLookAfter(treatRegionTo)) {
                wardNurseId = temp.getWorkId();
                break;
            }
        }
        if (wardNurseId == null) {
            return false;  //没有可以分配的病房护士
        }

        Bed_Patient_Relation bedAllocated = getBed_patient_relation_repository().findByBedId(bedAllocatedId);
        bedAllocated.setPatientId(patientId);
        getBed_patient_relation_repository().save(bedAllocated);

        Patient_WardNurse_Relation patient_wardNurse_relation = new Patient_WardNurse_Relation(patientId, wardNurseId);
        getPatient_wardNurse_relation_repository().save(patient_wardNurse_relation);

        notificationForHeadNurse(patientId, treatRegionTo);
        return true;
    }

    public void allocateForIdleResources(String workingTreatRegion) {

        List<Waiting> waitingsInIsolationZone = getWaitingRepository().findByTargetRegionAndWaitingType(workingTreatRegion,WaitingType.IsolationZone);

        Long patientId;
        Waiting tempWaiting;
        if (waitingsInIsolationZone.size()>0){
            Long minId = waitingsInIsolationZone.get(0).getId();
            for (Waiting waiting: waitingsInIsolationZone){
                if (waiting.getId()<minId){
                    minId = waiting.getId();
                }
            }
            tempWaiting = getWaitingRepository().findById((long)minId);
            patientId = tempWaiting.getPatientId();
            if (allocateResourcesForPatient(patientId,workingTreatRegion)){
                getWaitingRepository().delete(tempWaiting);
            }
            return;
        }

        List<Waiting> waitingsInUnmatchedTreatmentRegion = getWaitingRepository().findByTargetRegionAndWaitingType(workingTreatRegion,WaitingType.UnmatchedTreatmentRegion);
        if (waitingsInUnmatchedTreatmentRegion.size()>0){
            Long minId = waitingsInUnmatchedTreatmentRegion.get(0).getId();
            for (Waiting waiting: waitingsInUnmatchedTreatmentRegion){
                if (waiting.getId()<minId){
                    minId = waiting.getId();
                }
            }
            tempWaiting = getWaitingRepository().findById((long)minId);
            patientId = tempWaiting.getPatientId();

            Bed_Patient_Relation bed_patient_relation = getBed_patient_relation_repository().findByPatientId(patientId);
            Patient_WardNurse_Relation patient_wardNurse_relation = getPatient_wardNurse_relation_repository().findByPatientId(patientId);

            String originalRegion = getBed_patient_relation_repository().findByPatientId(patientId).getTreatRegion();

            if (allocateResourcesForPatient(patientId,workingTreatRegion)){
                getWaitingRepository().delete(tempWaiting);
                bed_patient_relation.setPatientId(null);
                getBed_patient_relation_repository().save(bed_patient_relation);
                getPatient_wardNurse_relation_repository().delete(patient_wardNurse_relation);
            }
            allocateForIdleResources(originalRegion);
            return;
        }

    }

    private int getMaxPatientNumberOfAWardNurseAbleToLookAfter(String treatRegion) {
        if (treatRegion.equals(TreatRegion.MildStatement)) {
            return SystemConstant.MaxPatientNumberOfAWardNurseAbleToLookAfterInMildArea;
        } else if (treatRegion.equals(TreatRegion.IntensiveStatement)) {
            return SystemConstant.MaxPatientNumberOfAWardNurseAbleToLookAfterInIntensiveArea;
        } else if (treatRegion.equals(TreatRegion.CriticalStatement)) {
            return SystemConstant.MaxPatientNumberOfAWardNurseAbleToLookAfterInCriticalArea;
        } else {
            return 0;
        }
    }

    public void notificationForAttendingDoctor(Long patientId, String treatmentRegion){
        User attendingDoctor = getUserRepository().findByRoleIdAndWorkingTreatRegion(RoleId.AttendingDoctor,treatmentRegion).get(0);
        Patient patient = getPatientRepository().findPatientById(patientId);
        if (patient == null) {
            throw new ErrorCode(ErrorCode.PATIENT_NOT_FOUND_FAILURE);
        }

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);

        String notificationString = "Patient " + patient.getName() + " can discharge now --- " + dateString;
        Notification notification = new Notification(patientId,attendingDoctor.getWorkId(),notificationString);
        getNotificationRepository().save(notification);
    }

    public void notificationForHeadNurse(Long patientId, String treatmentRegion) {
        User headNurse = getUserRepository().findByRoleIdAndWorkingTreatRegion(RoleId.HeadNurse, treatmentRegion).get(0);
        Patient patient = getPatientRepository().findPatientById(patientId);
        if (patient == null) {
            throw new ErrorCode(ErrorCode.PATIENT_NOT_FOUND_FAILURE);
        }

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);

        String notificationString = "Patient " + patient.getName() + " has been transferred to your region --- " + dateString;
        Notification notification = new Notification(patientId, headNurse.getWorkId(), notificationString);
        getNotificationRepository().save(notification);
    }
}
