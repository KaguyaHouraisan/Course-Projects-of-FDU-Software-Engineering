package DataBase1.errorcode;

import java.util.HashMap;
import java.util.Map;

public class ErrorCode extends RuntimeException {
    public static final int USER_NOT_FOUND_FAILURE = 100;
    public static final int WRONG_PASSWORD_FAILURE = 101;
    public static final int WRONG_WORKING_TREAT_REGION = 102;
    public static final int WRONG_ROLE_ID = 103;
    public static final int WORK_ID_HAS_EXISTED = 104;
    public static final int NULL_PATIENT_NAME = 200;
    public static final int WRONG_PATIENT_GENDER = 201;
    public static final int WRONG_CONDITION_RANK = 202;
    public static final int PATIENT_NOT_FOUND_FAILURE = 203;
    public static final int WRONG_DISCHARGE_CONDITION = 204;
    public static final int WRONG_WAITING_STATUS = 205;
    public static final int WRONG_DETECTION_RESULT = 206;
    public static final int INSUFFICIENT_PERMISSIONS = 502;
    public static final int INVALID_WARDNURSE = 503;
    public static final int PERMISSION_UNAVAILABLE = 302;
    public static final int NOT_WARDNURSE_TO_REMOVE = 701;
    public static final int CAN_NOT_REMOVE_WARDNURSE_RELATED_TO_PATIENT = 702;
    public static final int CAN_NOT_DISCHARGE = 703;

    private int errorCode;

    private static Map<Integer,String> ErrorCodeMap = new HashMap<>();

    static {
        ErrorCodeMap.put(USER_NOT_FOUND_FAILURE, "This user does not exist. \n");
        ErrorCodeMap.put(WRONG_PASSWORD_FAILURE, "Wrong password. \n");
        ErrorCodeMap.put(WRONG_WORKING_TREAT_REGION, "Wrong working treatment region. \n");
        ErrorCodeMap.put(WRONG_ROLE_ID, "Wrong role ID. \n");
        ErrorCodeMap.put(WORK_ID_HAS_EXISTED, "This workID has already existed. \n");
        ErrorCodeMap.put(NULL_PATIENT_NAME, "Patient name could not be null. \n");
        ErrorCodeMap.put(WRONG_PATIENT_GENDER, "Wrong patient gender. \n");
        ErrorCodeMap.put(WRONG_CONDITION_RANK, "Wrong condition rank. \n");
        ErrorCodeMap.put(PATIENT_NOT_FOUND_FAILURE, "This patient does not exist. \n");
        ErrorCodeMap.put(WRONG_DISCHARGE_CONDITION, "Wrong discharge condition. \n");
        ErrorCodeMap.put(WRONG_WAITING_STATUS, "Wrong waiting status. \n");
        ErrorCodeMap.put(WRONG_DETECTION_RESULT, "Wrong detection result. \n");
        ErrorCodeMap.put(INSUFFICIENT_PERMISSIONS, "Insufficient permissions.  \n");
        ErrorCodeMap.put(INVALID_WARDNURSE, "Invalid WardNurse.  \n");
        ErrorCodeMap.put(PERMISSION_UNAVAILABLE, "Permission Unavailable.  \n");
        ErrorCodeMap.put(NOT_WARDNURSE_TO_REMOVE, "Only free ward nurse can be removed.  \n");
        ErrorCodeMap.put(CAN_NOT_REMOVE_WARDNURSE_RELATED_TO_PATIENT, "Can not remove a ward nurse related to a patient.  \n");
        ErrorCodeMap.put(CAN_NOT_DISCHARGE, "Can not discharge.  \n");
    }

    public static String getErrorText(int errorCode){
        return ErrorCodeMap.getOrDefault(errorCode,"invalid");
    }

    public ErrorCode(int errorCode){
        super(String.format("errorCode '%d'  \"%s\"",errorCode,getErrorText(errorCode) ));
        this.errorCode = errorCode;
    }

    public int getErrorCode(){
        return errorCode;
    }
}
