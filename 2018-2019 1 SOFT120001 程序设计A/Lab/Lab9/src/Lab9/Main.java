package Lab9;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        mainStep();
    }

    static char[][] paint = new char[17][27];
    private static boolean quitflag = false;
    static boolean getTheLowest = false;

    private static void mainStep() {
        Game.rePaint();
        for (;;) {
            Brick brick = getTheBrick();
            for (;;) {
                Game.output(brick);
                if (getTheLowest) {
                    lineCheck(brick);
                    getTheLowest = false;
                    break;
                }
                else {
                    System.out.print("Enter:");
                    Game.input(brick);
                }
            }
            if (quitflag) {
                System.out.print("Game over.");
                break;
            }
        }
    }

    private static Brick getTheBrick() {
        Random random = new Random();
        switch (random.nextInt(7 ) + 1) {
            case 1: return new Brick_I();
            case 2: return new Brick_J();
            case 3: return new Brick_L();
            case 4: return new Brick_O();
            case 5: return new Brick_S();
            case 6: return new Brick_T();
            default: return new Brick_Z();
        }
    }

    private static void lineCheck(Brick brick) {
        int i,j,k;
        boolean flag = false;
        if (brick.x <= 3) Main.quitflag = true;
        for (i = 16;i >= 1;i --) {
            if (paint[i][2] == '*' && paint[i][4] == '*' && paint[i][6] == '*' && paint[i][8] == '*' && paint[i][10] == '*' && paint[i][12] == '*' && paint[i][14] == '*' && paint[i][16] == '*' && paint[i][18] == '*' && paint[i][20] == '*' && paint[i][22] == '*' && paint[i][24] == '*') flag = true;
            if (flag) {
                for (k = i;k >= 1;k --) {
                    for (j = 1;j < 26;j ++) {
                        paint[k][j] = paint[k - 1][j];
                    }
                }
                flag = false;
                i = 16;
            }
        }
    }
}
