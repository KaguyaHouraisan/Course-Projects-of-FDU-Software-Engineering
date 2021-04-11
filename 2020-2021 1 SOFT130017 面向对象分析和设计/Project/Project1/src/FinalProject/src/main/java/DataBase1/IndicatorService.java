package DataBase1;

import DataBase1.constant.SystemConstant;
import DataBase1.errorcode.ErrorCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IndicatorService {

    private static int indicate = 0;
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private static Date beginDate;

    static {
        try {
            beginDate = formatter.parse("2021-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String getTime(){
        return formatter.format(beginDate);
    }

    public static void update(){
        indicate++;
        beginDate.setTime(beginDate.getTime()+1000*60*60*24);
    }

    public static int minusDate(String dateBefore, String dateAfter){
        SimpleDateFormat format = IndicatorService.formatter;

        Date date1;
        Date date2;

        try {
            date1 = format.parse(dateBefore);
            date2 = format.parse(dateAfter);
        } catch (ParseException e) {
            throw new ErrorCode(ErrorCode.INVALID_DATE_STRING);
        }

        return (int)((date2.getTime()-date1.getTime())/(SystemConstant.ONE_DAY));
    }

    public static Boolean ifDateSequenceTrue(String dateBefore, String dateAfter){
        SimpleDateFormat format = IndicatorService.formatter;

        Date date1;
        Date date2;

        try {
            date1 = format.parse(dateBefore);
            date2 = format.parse(dateAfter);
        } catch (ParseException e) {
            throw new ErrorCode(ErrorCode.INVALID_DATE_STRING);
        }

        return date1.before(date2);
    }



}
