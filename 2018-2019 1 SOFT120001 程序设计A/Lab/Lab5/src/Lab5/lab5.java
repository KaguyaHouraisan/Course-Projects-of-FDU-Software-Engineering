package Test;

import java.util.Scanner;

public class lab5 {
    public static void main(String[] args) {
        mainStep();
    }

    private static char[][] paint = new char[17][27];
    private static boolean quitflag = false;
    private static boolean warning = false;
    private static int x = 1,y = 12,con = 0;

    private static void rePaint() {
        int i,j;
        for (i = 0; i < 27; i ++) paint[0][i] = ' ';
        for (i = 1; i < 16; i ++) {
            paint[i][0] = '|';
            paint[i][26] = '|';
            for (j = 1; j < 26; j ++) paint[i][j] = ' ';
        }
        paint[16][0] = ' ';
        paint[16][26] = ' ';
        for (i = 1; i < 26; i ++) paint[16][i] = '-';
        System.out.println("Start...");
    }

    private static void mainStep() {
        rePaint();
        output();
        for (;;) {
            input();
            output();
            if (quitflag) break;
        }
    }

    private static void output() {
        int i;
        int[][] place = diamonds();
        for (i = 0; i < 4; i ++) {
            int a = place[i][0],b = place[i][1];
            paint[x + a][y + b] = '*';
        }
        System.out.print("\n\n\n");
        for (i = 1; i < 17; i ++) System.out.println(paint[i]);
        if (!quitflag) System.out.print("Enter:");
        else System.out.print("Game over.");
        for (i = 0; i < 4; i ++) {
            int a = place[i][0],b = place[i][1];
            paint[x + a][y + b] = ' ';
        }
    }

    private static void input() {
        for (;;) {
            boolean flag = true;
            Scanner orderInput = new Scanner(System.in);
            char orderChoice = orderInput.next().charAt(0);
            if (orderChoice >= 'A' && orderChoice <= 'Z') orderChoice += 32;
            switch (orderChoice) {
                case ('w'): {
                    check(orderChoice);
                    if (!warning) con = (con + 1) % 4;
                    else warning = false;
                    break;
                }
                case ('s'): {
                    check(orderChoice);
                    x = x + 1;
                    break;
                }
                case ('a'): {
                    check(orderChoice);
                    if (!warning) y = y - 2;
                    else warning = false;
                    break;
                }
                case ('d'): {
                    check(orderChoice);
                    if (!warning) y = y + 2;
                    else warning = false;
                    break;
                }
                default: {
                    System.out.print("请输入符合要求的指令：");
                    flag = false;
                    break;
                }
            }
            if (flag) break;
        }
    }

    private static int[][] diamonds() {
        switch (con) {
            case 0: {
                int[][] place = { {-1,-2},{-1,0},{0,0},{0,2} };
                return place;
            }
            case 1: {
                int[][] place = { {-1,2},{0,0},{0,2},{1,0} };
                return place;
            }
            case 2: {
                int[][] place = { {0,-2},{0,0},{1,0},{1,2} };
                return place;
            }
            default: {
                int[][] place = { {-1,0},{0,-2},{0,0},{1,-2} };
                return place;
            }
        }
    }

    private static void check(char orderChoice) {
        int i;
        int[][] place = diamonds();
        switch (orderChoice) {
            case ('w'): {
                if ((con == 1 && y == 2) || (con == 3 && y == 24)) warning = true;
                if (con == 0 && x == 14) quitflag = true;
                break;
            }
            case ('s'): {
                if (con == 0) {
                    if (x == 14) quitflag = true;
                }
                else {
                    if (x == 13) quitflag = true;
                }
                break;
            }
            case ('a'): {
                for (i = 0; i < 4; i ++) {
                    int a = place[i][0],b = place[i][1];
                    if (paint[x + a][y + b - 2] == '|') {
                        warning = true;
                        break;
                    }
                }
                break;
            }
            case ('d'): {
                for (i = 0; i < 4; i ++) {
                    int a = place[i][0],b = place[i][1];
                    if (paint[x + a][y + b + 2] == '|') {
                        warning = true;
                        break;
                    }
                }
                break;
            }
        }
    }
}
