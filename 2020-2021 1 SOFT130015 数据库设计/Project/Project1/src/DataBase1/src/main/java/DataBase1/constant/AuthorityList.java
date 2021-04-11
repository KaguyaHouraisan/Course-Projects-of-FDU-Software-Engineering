package DataBase1.constant;

import java.util.HashMap;
import java.util.Map;

public class AuthorityList {
    public static Map<Integer,String> AuthorityMap = new HashMap<>();

    public static final int[] AuthorityOfAttendingDoctor = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    public static final int[] AuthorityOfHeadNurse = {1, 2, 3, 4, 5, 6, 11, 12, 13};
    public static final int[] AuthorityOfWardNurse = {1, 3, 15};
    public static final int[] AuthorityOfEmergencyNurse = {1, 2, 3, 14};

    static {
        AuthorityMap.put(1, "Command '1' : Search patients by region and discharge conditions. \n");
        AuthorityMap.put(2, "Command '2' : Search patients by region and waiting status. \n");
        AuthorityMap.put(3, "Command '3' : Search patients by region and life status. \n");
        AuthorityMap.put(4, "Command '4' : Search patients of this region. \n");
        AuthorityMap.put(5, "Command '5' : Search head nurse of this region. \n");
        AuthorityMap.put(6, "Command '6' : Search ward nurses of this region. \n");
        AuthorityMap.put(7, "Command '7' : Modify condition rank of patient. \n");
        AuthorityMap.put(8, "Command '8' : Modify life status of patient. \n");
        AuthorityMap.put(9, "Command '9' : Offer nucleic acid detection. \n");
        AuthorityMap.put(10, "Command '10' : Check for discharge. \n");
        AuthorityMap.put(11, "Command '11' : Add ward nurse. \n");
        AuthorityMap.put(12, "Command '12' : Remove nurse. \n");
        AuthorityMap.put(13, "Command '13' : Search patients and beds of this region. \n");
        AuthorityMap.put(14, "Command '14' : Register patient information. \n");
        AuthorityMap.put(15, "Command '15' : Daily register. \n");
    }
}
