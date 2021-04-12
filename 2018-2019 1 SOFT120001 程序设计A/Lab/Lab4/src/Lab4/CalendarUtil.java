package Test;
import java.util.Scanner;

public class CalendarUtil {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input the year(>=2000)");
        int year = input.nextInt();
        System.out.println("Please input the month");
        int month = input.nextInt();
        printCalendar(year, month);
    }

    private static void printCalendar(int year, int month) {
        int firstDayOfWeek = getFirstDayOfWeek(year, month);
        int daysOfMonth = getDaysOfMonth(year, month);
        int[] table = new int[42];
        int i;
        for (i = 0; i < 42; i ++) table[i] = 0;
        for (i = 0; i < daysOfMonth; i ++) table[firstDayOfWeek + i] = i + 1;
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");
        for (i = 0; i < 42; i ++ ) {
            if (table[i] == 0) System.out.print("    ");
            else if (table[i] > 0 && table[i] < 10) System.out.print(table[i] + "   ");
            else System.out.print(table[i] + "  ");
            if (i % 7 == 6) System.out.print("\n");
        }
    }

    private static int getDaysOfMonth(int year, int month) {
        boolean leapYear = isLeapYear(year);
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month ==12) return 31;
        else if (month == 4 || month == 6 || month == 9 || month ==11) return 30;
        else {
            if (leapYear) return 29;
            else return 28;
        }
    }

    private static int getFirstDayOfWeek(int year, int month) {
        if (year == 2000 && month ==1) return 6;
        else {
            if (month == 1) {
                year = year - 1;
                month = 12;
                return (getFirstDayOfWeek(year,month) + getDaysOfMonth(year,month)) % 7;
            }
            else {
                month = month - 1;
                return (getFirstDayOfWeek(year,month) + getDaysOfMonth(year,month)) % 7;
            }
        }
    }

    private static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }
}
