package DataBase1;

import DataBase1.domain.*;
import DataBase1.errorcode.ErrorCode;
import DataBase1.relation.*;
import DataBase1.repository.*;
import DataBase1.constant.*;
import java.util.List;
import java.util.Scanner;
import DataBase1.role.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class Terminal {
    private User currentUser;
    private Bed_Patient_Relation_Repository bed_patient_relation_repository;
    private BedRepository bedRepository;
    private DailyRegistrationRepository dailyRegistrationRepository;
    private NucleicAcidDetectionRepository nucleicAcidDetectionRepository;
    private Patient_WardNurse_Relation_Repository patient_wardNurse_relation_repository;
    private PatientRepository patientRepository;
    private UserRepository userRepository;
    private WaitingRepository waitingRepository;
    private NotificationRepository notificationRepository;
    private Role attendingDoctor;
    private Role headNurse;
    private Role wardNurse;
    private Role emergencyNurse;
    private final Role[] roles = new Role[5];

    @Autowired
    public Terminal(Bed_Patient_Relation_Repository bed_patient_relation_repository, BedRepository bedRepository,
                    DailyRegistrationRepository dailyRegistrationRepository, NucleicAcidDetectionRepository nucleicAcidDetectionRepository,
                    Patient_WardNurse_Relation_Repository patient_wardNurse_relation_repository, PatientRepository patientRepository,
                    UserRepository userRepository, WaitingRepository waitingRepository, NotificationRepository notificationRepository) {
        currentUser = null;
        this.bed_patient_relation_repository = bed_patient_relation_repository;
        this.bedRepository = bedRepository;
        this.dailyRegistrationRepository = dailyRegistrationRepository;
        this.nucleicAcidDetectionRepository = nucleicAcidDetectionRepository;
        this.patient_wardNurse_relation_repository = patient_wardNurse_relation_repository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.waitingRepository = waitingRepository;
        this.notificationRepository = notificationRepository;

        attendingDoctor = new AttendingDoctor(this.bed_patient_relation_repository, this.bedRepository, this.dailyRegistrationRepository, this.nucleicAcidDetectionRepository, this.patient_wardNurse_relation_repository, this.patientRepository, this.userRepository, this.waitingRepository, this.notificationRepository);
        headNurse = new HeadNurse(this.bed_patient_relation_repository, this.bedRepository, this.dailyRegistrationRepository, this.nucleicAcidDetectionRepository, this.patient_wardNurse_relation_repository, this.patientRepository, this.userRepository, this.waitingRepository, this.notificationRepository);
        wardNurse = new WardNurse(this.bed_patient_relation_repository, this.bedRepository, this.dailyRegistrationRepository, this.nucleicAcidDetectionRepository, this.patient_wardNurse_relation_repository, this.patientRepository, this.userRepository, this.waitingRepository, this.notificationRepository);
        emergencyNurse = new EmergencyNurse(this.bed_patient_relation_repository, this.bedRepository, this.dailyRegistrationRepository, this.nucleicAcidDetectionRepository, this.patient_wardNurse_relation_repository, this.patientRepository, this.userRepository, this.waitingRepository, this.notificationRepository);
        roles[0] = null;
        roles[1] = attendingDoctor;
        roles[2] = headNurse;
        roles[3] = wardNurse;
        roles[4] = emergencyNurse;
    }

    public Terminal(){}


    public void runTerminal() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String command = null;

        while (true) {
            try {
                if (currentUser == null) {
                    System.out.println("Welcome to hospital information management system, type in '1' to register, or '2' to login. ");
                    command = scanner.nextLine();
                    switch (command) {
                        case "1" :
                            register();
                            break;
                        case "2" :
                            login();
                            break;
                        default:
                            break;
                    }
                } else {
                    System.out.println("Welcome, " + currentUser.getUserName() + "(" + currentUser.getWorkId() + ", " +
                            currentUser.getRoleId() + ", " + currentUser.getWorkingTreatRegion() + ")" + ", type in '0' to quit. ");
                    showNotification();
                    showAuthorityList();
                    command = scanner.nextLine();
                    switch (command) {
                        case "1" :
                            searchPatientByRegionAndDisChargeConditions();
                            break;
                        case "2" :
                            searchPatientByRegionAndWaitingStatus();
                            break;
                        case "3" :
                            searchPatientByRegionAndLifeStatus();
                            break;
                        case "4" :
                            searchAllPatientThisRegion();
                            break;
                        case "5" :
                            searchHeadNurse();
                            break;
                        case "6" :
                            searchWardNurse();
                            break;
                        case "7" :
                            modifyPatientConditionRank();
                            break;
                        case "8" :
                            modifyPatientLifeStatus();
                            break;
                        case "9" :
                            nucleicAcidDetection();
                            break;
                        case "10" :
                            checkForDisCharge();
                            break;
                        case "11" :
                            addWardNurse();
                            break;
                        case "12" :
                            removeNurse();
                            break;
                        case "13" :
                            searchBedPatientInfo();
                            break;
                        case "14" :
                            registerPatientInfo();
                            break;
                        case "15" :
                            dailyRegister();
                            break;
                        case "0" :
                            quit();
                            break;
                        default:
                            break;
                    }
                }
            } catch (ErrorCode errorCode) {
                System.out.println(errorCode.getErrorText(errorCode.getErrorCode()));
            }
        }
    }

    private void register() {
        Scanner scanner = new Scanner(System.in);
        long workID;
        String username;
        String password;
        long roleId;
        String workingTreatRegion;
        User user;

        System.out.println("Please type in your workID (pure number): ");
        workID = Long.parseLong(scanner.nextLine());
        if (userRepository.findUserByWorkId(workID) != null) {
            throw new ErrorCode(ErrorCode.WORK_ID_HAS_EXISTED);
        }

        System.out.println("Please type in your username: ");
        username = scanner.nextLine();

        System.out.println("Please type in your password: ");
        password = DigestUtils.md5DigestAsHex(scanner.nextLine().getBytes());

        System.out.println("Please type in your roleId: ");
        System.out.println("Type in '1' as attending doctor, type in '2' as head nurse, ");
        System.out.println("type in '3' as ward nurse, type in '4' as emergency nurse. ");
        switch (scanner.nextLine()) {
            case "1" :
                roleId = RoleId.AttendingDoctor;
                break;
            case "2" :
                roleId = RoleId.HeadNurse;
                break;
            case "3" :
                roleId = RoleId.WardNurse;
                break;
            case "4" :
                roleId = RoleId.EmergencyNurse;
                break;
            default:
                throw new ErrorCode(ErrorCode.WRONG_ROLE_ID);
        }

        System.out.println("Please type in your workingTreatRegionId: ");
        System.out.println("Type in '1' as mild treatment region, type in '2' as intensive treatment region, ");
        System.out.println("type in '3' as critical treatment region, type in '4' as null treatment region. ");
        switch (scanner.nextLine()) {
            case "1" :
                workingTreatRegion = TreatRegion.MildStatement;
                break;
            case "2" :
                workingTreatRegion = TreatRegion.IntensiveStatement;
                break;
            case "3" :
                workingTreatRegion = TreatRegion.CriticalStatement;
                break;
            case "4" :
                workingTreatRegion = TreatRegion.NullStatement;
                break;
            default:
                throw new ErrorCode(ErrorCode.WRONG_WORKING_TREAT_REGION);
        }

        user = new User(workID, username, password, roleId, workingTreatRegion);
        userRepository.save(user);
        System.out.println("Register successfully. ");
    }

    private void login() {
        Scanner scanner = new Scanner(System.in);
        String workID;
        String password;
        User user;

        System.out.println("Please type in your workID: ");
        workID = scanner.nextLine();
        System.out.println("Please type in your password: ");
        password = DigestUtils.md5DigestAsHex(scanner.nextLine().getBytes());

        user = userRepository.findUserByWorkId(Long.parseLong(workID));
        if (user == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else if (!user.getPassword().equals(password)) {
            throw new ErrorCode(ErrorCode.WRONG_PASSWORD_FAILURE);
        } else {
            currentUser = user;
            System.out.println("Login successfully. ");
        }
    }

    private void quit() {
        currentUser = null;
        System.out.println("You have successfully exited. ");
    }

    private void showNotification() {
        if (currentUser == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else {
            if (currentUser.getRoleId() == RoleId.AttendingDoctor || currentUser.getRoleId() == RoleId.HeadNurse) {
                List<Notification> notifications = notificationRepository.findByWorkId(currentUser.getWorkId());
                for (Notification temp : notifications) {
                    System.out.println(temp.getContent());
                }
                if (notifications.size() == 0) {
                    System.out.println("No Notification. ");
                }
            }
        }
    }

    private void showAuthorityList() {
        if (currentUser == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else {
            if (currentUser.getRoleId() == RoleId.AttendingDoctor) {
                for (int i = 0; i < AuthorityList.AuthorityOfAttendingDoctor.length; i++) {
                    System.out.print(AuthorityList.AuthorityMap.get(AuthorityList.AuthorityOfAttendingDoctor[i]));
                }
            } else if (currentUser.getRoleId() == RoleId.HeadNurse) {
                for (int i = 0; i < AuthorityList.AuthorityOfHeadNurse.length; i++) {
                    System.out.print(AuthorityList.AuthorityMap.get(AuthorityList.AuthorityOfHeadNurse[i]));
                }
            } else if (currentUser.getRoleId() == RoleId.WardNurse) {
                for (int i = 0; i < AuthorityList.AuthorityOfWardNurse.length; i++) {
                    System.out.print(AuthorityList.AuthorityMap.get(AuthorityList.AuthorityOfWardNurse[i]));
                }
            } else if (currentUser.getRoleId() == RoleId.EmergencyNurse) {
                for (int i = 0; i < AuthorityList.AuthorityOfEmergencyNurse.length; i++) {
                    System.out.print(AuthorityList.AuthorityMap.get(AuthorityList.AuthorityOfEmergencyNurse[i]));
                }
            } else {
                throw new ErrorCode(ErrorCode.WRONG_ROLE_ID);
            }
        }
    }

    private void searchPatientByRegionAndDisChargeConditions() {
        if (currentUser == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else {
            Scanner scanner = new Scanner(System.in);
            String disChargeCondition;
            String region;

            if (currentUser.getRoleId() == RoleId.EmergencyNurse) {
                System.out.println("Please input the region of patients: ");
                System.out.println("Type in '1' as mild region, type in '2' as intensive region, type in '3' as critical region. ");
                switch (scanner.nextLine()) {
                    case "1" :
                        region = TreatRegion.MildStatement;
                        break;
                    case "2" :
                        region = TreatRegion.IntensiveStatement;
                        break;
                    case "3" :
                        region = TreatRegion.CriticalStatement;
                        break;
                    default:
                        throw new ErrorCode(ErrorCode.WRONG_WORKING_TREAT_REGION);
                }
            } else {
                region = currentUser.getWorkingTreatRegion();
            }

            System.out.println("Please input the discharge condition of patients: ");
            System.out.println("Type in '1' as unable to discharge, type in '2' as able to discharge. ");
            switch (scanner.nextLine()) {
                case "1":
                    disChargeCondition = DisChargeCondition.UnAbleToDisCharge;
                    break;
                case "2":
                    disChargeCondition = DisChargeCondition.AbleToDisCharge;
                    break;
                default:
                    throw new ErrorCode(ErrorCode.WRONG_DISCHARGE_CONDITION);
            }

            List<Patient> patients = roles[currentUser.getRoleId().intValue()].searchPatientByRegionAndDisChargeConditions(currentUser, disChargeCondition, region);
            System.out.print("\n");
            for (Patient temp : patients) {
                System.out.println("ID: " + temp.getId() + "  Name: " + temp.getName() + "  Age: " + temp.getAge() +
                        "  Gender: " + temp.getGender() + "  Condition Rank: " + temp.getConditionRank());
            }
            System.out.print("\n");
        }
    }

    private void searchPatientByRegionAndWaitingStatus() {
        if (currentUser == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else {
            Scanner scanner = new Scanner(System.in);
            String waitingStatus;
            String region;

            if (currentUser.getRoleId() == RoleId.EmergencyNurse) {
                System.out.println("Please input the region of patients: ");
                System.out.println("Type in '1' as mild region, type in '2' as intensive region, type in '3' as critical region. ");
                switch (scanner.nextLine()) {
                    case "1" :
                        region = TreatRegion.MildStatement;
                        break;
                    case "2" :
                        region = TreatRegion.IntensiveStatement;
                        break;
                    case "3" :
                        region = TreatRegion.CriticalStatement;
                        break;
                    default:
                        throw new ErrorCode(ErrorCode.WRONG_WORKING_TREAT_REGION);
                }
            } else {
                region = currentUser.getWorkingTreatRegion();
            }

            System.out.println("Please input the waiting status of patients: ");
            System.out.println("Type in '1' as right region, type in '2' as waiting to move. ");
            switch (scanner.nextLine()) {
                case "1":
                    waitingStatus = WaitingStatus.RightRegion;
                    break;
                case "2":
                    waitingStatus = WaitingStatus.WaitingToMove;
                    break;
                default:
                    throw new ErrorCode(ErrorCode.WRONG_WAITING_STATUS);
            }

            Patient tempPatient;
            List<Patient_Waiting_Info> patient_waiting_infos = roles[currentUser.getRoleId().intValue()].searchPatientByRegionAndWaitingStatus(currentUser, waitingStatus, region);
            System.out.print("\n");
            if (waitingStatus.equals(WaitingStatus.RightRegion)) {
                for (Patient_Waiting_Info temp : patient_waiting_infos) {
                    tempPatient = temp.getPatient();
                    System.out.println("ID: " + tempPatient.getId() + "  Name: " + tempPatient.getName() + "  Age: " + tempPatient.getAge() +
                            "  Gender: " + tempPatient.getGender() + "  Condition Rank: " + tempPatient.getConditionRank());
                }
            } else {
                for (Patient_Waiting_Info temp : patient_waiting_infos) {
                    tempPatient = temp.getPatient();
                    System.out.println("Target Region: " + temp.getTargetRegion() + "  ID: " + tempPatient.getId() + "  Name: " + tempPatient.getName() + "  Age: " + tempPatient.getAge() +
                            "  Gender: " + tempPatient.getGender() + "  Condition Rank: " + tempPatient.getConditionRank());
                }
            }
            System.out.print("\n");
        }
    }

    private void searchPatientByRegionAndLifeStatus() {
        if (currentUser == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else {
            Scanner scanner = new Scanner(System.in);
            String lifeStatus;
            String region;

            if (currentUser.getRoleId() == RoleId.EmergencyNurse) {
                System.out.println("Please input the region of patients: ");
                System.out.println("Type in '1' as mild region, type in '2' as intensive region, type in '3' as critical region. ");
                switch (scanner.nextLine()) {
                    case "1" :
                        region = TreatRegion.MildStatement;
                        break;
                    case "2" :
                        region = TreatRegion.IntensiveStatement;
                        break;
                    case "3" :
                        region = TreatRegion.CriticalStatement;
                        break;
                    default:
                        throw new ErrorCode(ErrorCode.WRONG_WORKING_TREAT_REGION);
                }
            } else {
                region = currentUser.getWorkingTreatRegion();
            }

            System.out.println("Please input the life status of patients: ");
            System.out.println("Type in '1' as alive, type in '2' as not alive. ");
            switch (scanner.nextLine()) {
                case "1":
                    lifeStatus = LifeStatus.Alive;
                    break;
                case "2":
                    lifeStatus = LifeStatus.NotAlive;
                    break;
                default:
                    throw new ErrorCode(ErrorCode.WRONG_DISCHARGE_CONDITION);
            }

            List<Patient> patients = roles[currentUser.getRoleId().intValue()].searchPatientByRegionAndLifeStatus(currentUser, lifeStatus, region);
            System.out.print("\n");
            for (Patient temp : patients) {
                System.out.println("ID: " + temp.getId() + "  Name: " + temp.getName() + "  Age: " + temp.getAge() +
                        "  Gender: " + temp.getGender() + "  Condition Rank: " + temp.getConditionRank());
            }
            System.out.print("\n");
        }
    }

    private void searchAllPatientThisRegion() {
        if (currentUser == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else {
            List<Patient> patients = roles[currentUser.getRoleId().intValue()].searchAllPatientThisRegion(currentUser.getWorkingTreatRegion());
            System.out.print("\n");
            for (Patient temp : patients) {
                System.out.println("ID: " + temp.getId() + "  Name: " + temp.getName() + "  Age: " + temp.getAge() +
                        "  Gender: " + temp.getGender() + "  Condition Rank: " + temp.getConditionRank());
            }
            System.out.print("\n");
        }
    }

    private void searchHeadNurse() {
        if (currentUser == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else {
            List<User> headNurses = roles[currentUser.getRoleId().intValue()].searchHeadNurse(currentUser);
            System.out.print("\n");
            for (User headNurse : headNurses) {
                System.out.println("Head Nurse Work ID: " + headNurse.getWorkId() + "  Name: " + headNurse.getUserName());
            }
            System.out.print("\n");
        }
    }

    private void searchWardNurse() {
        if (currentUser == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else {
            List<User> wardNurses = roles[currentUser.getRoleId().intValue()].searchWardNurse(currentUser);
            System.out.print("\n");
            for (User wardNurse : wardNurses) {
                System.out.println("Ward Nurse Work ID: " + wardNurse.getWorkId() + "  Name: " + wardNurse.getUserName());
            }
            System.out.print("\n");
        }
    }

    private void modifyPatientConditionRank() {
        if (currentUser == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nPlease input the patient ID: ");
            long patientID = Long.parseLong(scanner.nextLine());

            String conditionRank;
            System.out.println("Please input the condition rank of the patient: ");
            System.out.println("Type in '1' as mild statement, type in '2' as intensive statement, type in '3' as critical statement. ");
            switch (scanner.nextLine()) {
                case "1":
                    conditionRank = TreatRegion.MildStatement;
                    break;
                case "2":
                    conditionRank = TreatRegion.IntensiveStatement;
                    break;
                case "3":
                    conditionRank = TreatRegion.CriticalStatement;
                    break;
                default:
                    throw new ErrorCode(ErrorCode.WRONG_CONDITION_RANK);
            }

            String result = roles[currentUser.getRoleId().intValue()].modifyPatientConditionRank(currentUser, patientID, conditionRank);
            System.out.println("\n" + result + "\n");
        }
    }

    private void modifyPatientLifeStatus() {
        if (currentUser == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nPlease input the patient ID: ");
            long patientID = Long.parseLong(scanner.nextLine());

            String result = roles[currentUser.getRoleId().intValue()].modifyPatientLifeStatus(currentUser, patientID, LifeStatus.NotAlive);
            System.out.println("\n" + result + "\n");
        }
    }

    private void nucleicAcidDetection() {
        if (currentUser == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nPlease input the patient ID: ");
            long patientID = Long.parseLong(scanner.nextLine());

            String detectionResult;
            System.out.println("Please input the detection result of patient: ");
            System.out.println("Type in '1' as negative, type in '2' as positive. ");
            switch (scanner.nextLine()) {
                case "1":
                    detectionResult = NucleicAcidDetectionResult.Negative;
                    break;
                case "2":
                    detectionResult = NucleicAcidDetectionResult.Positive;
                    break;
                default:
                    throw new ErrorCode(ErrorCode.WRONG_DETECTION_RESULT);
            }

            String result = roles[currentUser.getRoleId().intValue()].nucleicAcidDetection(currentUser, patientID, detectionResult);
            System.out.println("\n" + result + "\n");
        }
    }

    private void checkForDisCharge() {
        if (currentUser == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nPlease input the patient ID: ");
            long patientID = Long.parseLong(scanner.nextLine());

            String result = roles[currentUser.getRoleId().intValue()].checkForDisCharge(currentUser, patientID);
            System.out.println("\n" + result + "\n");
        }
    }

    private void addWardNurse() {
        if (currentUser == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nPlease input the work ID of the ward nurse: ");
            long wardNurseID = Long.parseLong(scanner.nextLine());
            String result = roles[currentUser.getRoleId().intValue()].addWardNurse(currentUser, wardNurseID);
            System.out.println(result);
        }
    }

    private void removeNurse() {
        if (currentUser == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nPlease input the work ID of the nurse: ");
            long nurseID = Long.parseLong(scanner.nextLine());

            String result = roles[currentUser.getRoleId().intValue()].removeNurse(currentUser, nurseID);
            System.out.println(result);
        }
    }

    private void searchBedPatientInfo() {
        if (currentUser == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else {
            List<Bed_Patient_Info> bed_patient_information = roles[currentUser.getRoleId().intValue()].searchBedPatientInfo(currentUser);
            Patient tempPatient;
            Bed tempBed;

            System.out.print("\n");
            for (Bed_Patient_Info temp : bed_patient_information) {
                tempBed = temp.getBed();
                tempPatient = temp.getPatient();
                if (tempPatient == null) {
                    System.out.println("Region: " + tempBed.getTreatRegion() + "  Ward ID: " + tempBed.getWardId() + "  Bed ID: "
                            + tempBed.getId() + "  No Patient currently. ");
                } else {
                    System.out.println("Region: " + tempBed.getTreatRegion() + "  Ward ID: " + tempBed.getWardId() + "  Bed ID: "
                            + tempBed.getId() + "  Patient ID: " + tempPatient.getId() + "  Name: " + tempPatient.getName() + "  Age: " + tempPatient.getAge() +
                            "  Gender: " + tempPatient.getGender() + "  Condition Rank: " + tempPatient.getConditionRank());
                }
            }
            System.out.print("\n");
        }
    }

    private void registerPatientInfo() {
        if (currentUser == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else {
            Scanner scanner = new Scanner(System.in);
            String patientName;
            String patientAge;
            String patientGender;
            String patientLifeStatus;
            String patientConditionRank;
            String nucleicAcidDetectionReceiptDate;

            System.out.println("Please input the name of the patient: ");
            patientName = scanner.nextLine();
            if (patientName == null) {
                throw new ErrorCode(ErrorCode.NULL_PATIENT_NAME);
            }

            System.out.println("Please input the age of the patient: ");
            patientAge = scanner.nextLine();

            System.out.println("Please input the gender of the patient: ");
            System.out.println("Type in '1' as male, type in '2' as female. ");
            switch (scanner.nextLine()) {
                case "1" :
                    patientGender = Gender.Male;
                    break;
                case "2" :
                    patientGender = Gender.Female;
                    break;
                default:
                    throw new ErrorCode(ErrorCode.WRONG_PATIENT_GENDER);
            }

            patientLifeStatus = LifeStatus.Alive;

            System.out.println("Please input the condition rank of the patient: ");
            System.out.println("Type in '1' as mild, type in '2' as intensive, type in '3' as critical. ");
            switch (scanner.nextLine()) {
                case "1" :
                    patientConditionRank = TreatRegion.MildStatement;
                    break;
                case "2" :
                    patientConditionRank = TreatRegion.IntensiveStatement;
                    break;
                case "3" :
                    patientConditionRank = TreatRegion.CriticalStatement;
                    break;
                default:
                    throw new ErrorCode(ErrorCode.WRONG_CONDITION_RANK);
            }

            System.out.println("Please input the date of the nucleic acid detection receipt: ");
            nucleicAcidDetectionReceiptDate = scanner.nextLine();

            String result = roles[currentUser.getRoleId().intValue()].registerPatientInfo(currentUser, nucleicAcidDetectionReceiptDate,
                    NucleicAcidDetectionResult.Positive, patientConditionRank, patientName, patientAge, patientGender, patientLifeStatus);
            System.out.println(result);
        }
    }

    private void dailyRegister() {
        if (currentUser == null) {
            throw new ErrorCode(ErrorCode.USER_NOT_FOUND_FAILURE);
        } else {
            Scanner scanner = new Scanner(System.in);
            long patientId;
            double temperature;
            String symptom;
            String lifeStatus;

            System.out.println("Please input the ID of the patient: ");
            patientId = Long.parseLong(scanner.nextLine());
            Patient patient = patientRepository.findPatientById(patientId);
            if (patient == null) {
                throw new ErrorCode(ErrorCode.PATIENT_NOT_FOUND_FAILURE);
            }

            System.out.println("Please input the temperature of the patient: ");
            temperature = Double.parseDouble(scanner.nextLine());

            System.out.println("Please input the symptom of the patient: ");
            symptom = scanner.nextLine();

            System.out.println("Please input the life status of the patient: ");
            System.out.println("Type in '1' as alive, type in '2' as not alive. ");
            switch (scanner.nextLine()) {
                case "1" :
                    lifeStatus = LifeStatus.Alive;
                    break;
                case "2" :
                    lifeStatus = LifeStatus.NotAlive;
                    break;
                default:
                    throw new ErrorCode(ErrorCode.WRONG_PATIENT_GENDER);
            }

            String result = roles[currentUser.getRoleId().intValue()].dailyRegister(currentUser, patientId, temperature, symptom, lifeStatus);
            System.out.println(result);
        }
    }

    public void init() {
        bed_patient_relation_repository.deleteAll();
        bedRepository.deleteAll();
        dailyRegistrationRepository.deleteAll();
        nucleicAcidDetectionRepository.deleteAll();
        patient_wardNurse_relation_repository.deleteAll();
        patientRepository.deleteAll();
        userRepository.deleteAll();
        waitingRepository.deleteAll();
        notificationRepository.deleteAll();

        for (int wardID = 1; wardID <= SystemConstant.WardNumberOfMildTreatmentArea; wardID++) {
            for (int j = 1; j <= SystemConstant.BedNumberOfMildWard; j++) {
                Bed temp = new Bed(wardID, TreatRegion.MildStatement);
                bedRepository.save(temp);
                Bed_Patient_Relation bed_patient_relation = new Bed_Patient_Relation(null, temp.getId(), TreatRegion.MildStatement);
                bed_patient_relation_repository.save(bed_patient_relation);
            }
        }
        for (int wardID = 1; wardID <= SystemConstant.WardNumberOfIntensiveTreatmentArea; wardID++) {
            for (int j = 1; j <= SystemConstant.BedNumberOfIntensiveWard; j++) {
                Bed temp = new Bed(wardID, TreatRegion.IntensiveStatement);
                bedRepository.save(temp);
                Bed_Patient_Relation bed_patient_relation = new Bed_Patient_Relation(null, temp.getId(), TreatRegion.IntensiveStatement);
                bed_patient_relation_repository.save(bed_patient_relation);
            }
        }
        for (int wardID = 1; wardID <= SystemConstant.WardNumberOfCriticalTreatmentArea; wardID++) {
            for (int j = 1; j <= SystemConstant.BedNumberOfCriticalWard; j++) {
                Bed temp = new Bed(wardID, TreatRegion.CriticalStatement);
                bedRepository.save(temp);
                Bed_Patient_Relation bed_patient_relation = new Bed_Patient_Relation(null, temp.getId(), TreatRegion.CriticalStatement);
                bed_patient_relation_repository.save(bed_patient_relation);
            }
        }

        User attendingDoctorUser1 = new User((long)1, "attendingDoctorTest1", DigestUtils.md5DigestAsHex("123456".getBytes()), RoleId.AttendingDoctor, TreatRegion.MildStatement);
        userRepository.save(attendingDoctorUser1);
        User attendingDoctorUser2 = new User((long)2, "attendingDoctorTest2", DigestUtils.md5DigestAsHex("123456".getBytes()), RoleId.AttendingDoctor, TreatRegion.IntensiveStatement);
        userRepository.save(attendingDoctorUser2);
        User attendingDoctorUser3 = new User((long)3, "attendingDoctorTest3", DigestUtils.md5DigestAsHex("123456".getBytes()), RoleId.AttendingDoctor, TreatRegion.CriticalStatement);
        userRepository.save(attendingDoctorUser3);

        User headNurseUser1 = new User((long)4, "headNurseTest1", DigestUtils.md5DigestAsHex("123456".getBytes()), RoleId.HeadNurse, TreatRegion.MildStatement);
        userRepository.save(headNurseUser1);
        User headNurseUser2 = new User((long)5, "headNurseTest2", DigestUtils.md5DigestAsHex("123456".getBytes()), RoleId.HeadNurse, TreatRegion.IntensiveStatement);
        userRepository.save(headNurseUser2);
        User headNurseUser3 = new User((long)6, "headNurseTest3", DigestUtils.md5DigestAsHex("123456".getBytes()), RoleId.HeadNurse, TreatRegion.CriticalStatement);
        userRepository.save(headNurseUser3);

        User wardNurseUser1 = new User((long)7, "wardNurseTest1", DigestUtils.md5DigestAsHex("123456".getBytes()), RoleId.WardNurse, TreatRegion.MildStatement);
        userRepository.save(wardNurseUser1);
        User wardNurseUser2 = new User((long)8, "wardNurseTest2", DigestUtils.md5DigestAsHex("123456".getBytes()), RoleId.WardNurse, TreatRegion.MildStatement);
        userRepository.save(wardNurseUser2);
        User wardNurseUser3 = new User((long)9, "wardNurseTest3", DigestUtils.md5DigestAsHex("123456".getBytes()), RoleId.WardNurse, TreatRegion.MildStatement);
        userRepository.save(wardNurseUser3);
        User wardNurseUser4 = new User((long)10, "wardNurseTest4", DigestUtils.md5DigestAsHex("123456".getBytes()), RoleId.WardNurse, TreatRegion.IntensiveStatement);
        userRepository.save(wardNurseUser4);
        User wardNurseUser5 = new User((long)11, "wardNurseTest5", DigestUtils.md5DigestAsHex("123456".getBytes()), RoleId.WardNurse, TreatRegion.IntensiveStatement);
        userRepository.save(wardNurseUser5);
        User wardNurseUser6 = new User((long)12, "wardNurseTest6", DigestUtils.md5DigestAsHex("123456".getBytes()), RoleId.WardNurse, TreatRegion.CriticalStatement);
        userRepository.save(wardNurseUser6);
        User wardNurseUser7 = new User((long)13, "wardNurseTest7", DigestUtils.md5DigestAsHex("123456".getBytes()), RoleId.WardNurse, TreatRegion.CriticalStatement);
        userRepository.save(wardNurseUser7);

        User emergencyNurseUser = new User((long)14, "emergencyNurseTest", DigestUtils.md5DigestAsHex("123456".getBytes()), RoleId.EmergencyNurse, TreatRegion.NullStatement);
        userRepository.save(emergencyNurseUser);

        roles[(int) RoleId.EmergencyNurse].registerPatientInfo(emergencyNurseUser, "2020-12-30", NucleicAcidDetectionResult.Positive, TreatRegion.MildStatement, "Test1", "18", Gender.Male, LifeStatus.Alive);
        roles[(int) RoleId.EmergencyNurse].registerPatientInfo(emergencyNurseUser, "2020-12-30", NucleicAcidDetectionResult.Positive, TreatRegion.MildStatement, "Test2", "18", Gender.Female, LifeStatus.Alive);
        roles[(int) RoleId.EmergencyNurse].registerPatientInfo(emergencyNurseUser, "2020-12-30", NucleicAcidDetectionResult.Positive, TreatRegion.MildStatement, "Test3", "18", Gender.Female, LifeStatus.Alive);
        roles[(int) RoleId.EmergencyNurse].registerPatientInfo(emergencyNurseUser, "2020-12-30", NucleicAcidDetectionResult.Positive, TreatRegion.MildStatement, "Test4", "18", Gender.Male, LifeStatus.Alive);
        roles[(int) RoleId.EmergencyNurse].registerPatientInfo(emergencyNurseUser, "2020-12-30", NucleicAcidDetectionResult.Positive, TreatRegion.MildStatement, "Test5", "18", Gender.Female, LifeStatus.Alive);
        roles[(int) RoleId.EmergencyNurse].registerPatientInfo(emergencyNurseUser, "2020-12-30", NucleicAcidDetectionResult.Positive, TreatRegion.MildStatement, "Test6", "18", Gender.Male, LifeStatus.Alive);
        roles[(int) RoleId.EmergencyNurse].registerPatientInfo(emergencyNurseUser, "2020-12-30", NucleicAcidDetectionResult.Positive, TreatRegion.MildStatement, "Test7", "18", Gender.Female, LifeStatus.Alive);
        roles[(int) RoleId.EmergencyNurse].registerPatientInfo(emergencyNurseUser, "2020-12-30", NucleicAcidDetectionResult.Positive, TreatRegion.MildStatement, "Test8", "18", Gender.Female, LifeStatus.Alive);
        roles[(int) RoleId.EmergencyNurse].registerPatientInfo(emergencyNurseUser, "2020-12-30", NucleicAcidDetectionResult.Positive, TreatRegion.IntensiveStatement, "Test9", "18", Gender.Male, LifeStatus.Alive);
        roles[(int) RoleId.EmergencyNurse].registerPatientInfo(emergencyNurseUser, "2020-12-30", NucleicAcidDetectionResult.Positive, TreatRegion.IntensiveStatement, "Test10", "18", Gender.Female, LifeStatus.Alive);
        roles[(int) RoleId.EmergencyNurse].registerPatientInfo(emergencyNurseUser, "2020-12-30", NucleicAcidDetectionResult.Positive, TreatRegion.IntensiveStatement, "Test11", "18", Gender.Male, LifeStatus.Alive);
        roles[(int) RoleId.EmergencyNurse].registerPatientInfo(emergencyNurseUser, "2020-12-30", NucleicAcidDetectionResult.Positive, TreatRegion.IntensiveStatement, "Test12", "18", Gender.Female, LifeStatus.Alive);
        roles[(int) RoleId.EmergencyNurse].registerPatientInfo(emergencyNurseUser, "2020-12-30", NucleicAcidDetectionResult.Positive, TreatRegion.CriticalStatement, "Test13", "18", Gender.Male, LifeStatus.Alive);
        roles[(int) RoleId.EmergencyNurse].registerPatientInfo(emergencyNurseUser, "2020-12-30", NucleicAcidDetectionResult.Positive, TreatRegion.CriticalStatement, "Test14", "18", Gender.Female, LifeStatus.Alive);

        List<Patient> patients = roles[(int) RoleId.WardNurse].searchPatientByRegionAndDisChargeConditions(wardNurseUser1,DisChargeCondition.UnAbleToDisCharge,wardNurseUser1.getWorkingTreatRegion());
        Patient patient = patients.get(0);
        System.out.println(patient.getId());

        try {
            roles[(int) RoleId.WardNurse].dailyRegister(wardNurseUser1,patient.getId(),37,"Normal",LifeStatus.Alive);
        } catch (ErrorCode errorCode) {
            System.out.println(errorCode.getErrorText(errorCode.getErrorCode()));
        }
        try {
            roles[(int) RoleId.WardNurse].dailyRegister(wardNurseUser1,patient.getId(),36.7,"Normal1",LifeStatus.Alive);
        } catch (ErrorCode errorCode) {
            System.out.println(errorCode.getErrorText(errorCode.getErrorCode()));
        }
        try {
            roles[(int) RoleId.AttendingDoctor].nucleicAcidDetection(attendingDoctorUser1,patient.getId(),NucleicAcidDetectionResult.Negative);
        } catch (ErrorCode errorCode) {
            System.out.println(errorCode.getErrorText(errorCode.getErrorCode()));
        }
    }
}
