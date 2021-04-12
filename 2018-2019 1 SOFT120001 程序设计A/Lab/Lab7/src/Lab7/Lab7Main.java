package Lab7;

public class Lab7Main {
    public static void main(String[] args) {
        mainStep();
    }

    static char[][] paint = new char[17][27];
    static boolean quitflag = false;
    static boolean warning = false;
    static int x = 1,y = 12,con = 0;
    static boolean getTheLowest = false;

    private static void mainStep() {
        Lab7Game.rePaint();
        Lab7Game.output();
        for (;;) {
            Lab7Game.input();
            Lab7Game.output();
            if (quitflag) break;
        }
    }
}
