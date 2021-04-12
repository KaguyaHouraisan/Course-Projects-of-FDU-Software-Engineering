package Lab7;

import java.util.Scanner;

class Lab7Game {
    static void rePaint() {
        int i,j;
        for (i = 0; i < 27; i ++) Lab7Main.paint[0][i] = ' ';
        for (i = 1; i < 16; i ++) {
            Lab7Main.paint[i][0] = '|';
            Lab7Main.paint[i][26] = '|';
            for (j = 1; j < 26; j ++) Lab7Main.paint[i][j] = ' ';
        }
        Lab7Main.paint[16][0] = ' ';
        Lab7Main.paint[16][26] = ' ';
        for (i = 1; i < 26; i ++) Lab7Main.paint[16][i] = '-';
        System.out.println("Start...");
    }

    static void output() {
        int i;
        int[][] place = Lab7ZBlock.diamonds(Lab7Main.con);
        for (i = 0; i < 4; i++) {
            int a = place[i][0], b = place[i][1];
            Lab7Main.paint[Lab7Main.x + a][Lab7Main.y + b] = '*';
        }
        System.out.print("\n\n\n");
        for (i = 1; i < 17; i++) System.out.println(Lab7Main.paint[i]);
        if (!Lab7Main.quitflag) System.out.print("Enter:");
        else System.out.print("Game over.");
        if (!Lab7Main.getTheLowest) {
            for (i = 0; i < 4; i++) {
                int a = place[i][0], b = place[i][1];
                Lab7Main.paint[Lab7Main.x + a][Lab7Main.y + b] = ' ';
            }
        }
        else {
            lineCheck();
            Lab7Main.getTheLowest = false;
            Lab7Main.x = 1;
            Lab7Main.y = 12;
            Lab7Main.con = 0;
            output();
        }
    }

    static void input() {
        for (;;) {
            boolean flag = true;
            Scanner orderInput = new Scanner(System.in);
            char orderChoice = orderInput.next().charAt(0);
            if (orderChoice >= 'A' && orderChoice <= 'Z') orderChoice += 32;
            switch (orderChoice) {
                case ('w'): {
                    check(orderChoice);
                    if (!Lab7Main.warning) Lab7Main.con = (Lab7Main.con + 1) % 4;
                    else Lab7Main.warning = false;
                    break;
                }
                case ('s'): {
                    check(orderChoice);
                    Lab7Main.x = Lab7Main.x + 1;
                    break;
                }
                case ('a'): {
                    check(orderChoice);
                    if (!Lab7Main.warning) Lab7Main.y = Lab7Main.y - 2;
                    else Lab7Main.warning = false;
                    break;
                }
                case ('d'): {
                    check(orderChoice);
                    if (!Lab7Main.warning) Lab7Main.y = Lab7Main.y + 2;
                    else Lab7Main.warning = false;
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

    private static void check(char orderChoice) {
        switch (orderChoice) {
            case ('w'): {
                turnCheck(Lab7Main.x,Lab7Main.y,(Lab7Main.con + 1) % 4,Lab7Main.paint);
                if (!Lab7Main.warning) {
                    if (theLowestCheck(Lab7Main.x, Lab7Main.y, (Lab7Main.con + 1) % 4, Lab7Main.paint))
                        Lab7Main.getTheLowest = true;
                }
                break;
            }
            case ('s'): {
                if (theLowestCheck(Lab7Main.x + 1,Lab7Main.y,Lab7Main.con,Lab7Main.paint)) Lab7Main.getTheLowest = true;
                break;
            }
            case ('a'): {
                mainCheck(Lab7Main.x,Lab7Main.y,Lab7Main.con,3,Lab7Main.paint);
                break;
            }
            case ('d'): {
                mainCheck(Lab7Main.x,Lab7Main.y,Lab7Main.con,4,Lab7Main.paint);
                break;
            }
        }
    }
    
    private static void lineCheck() {
        int i,j,k;
        boolean flag = false;
        if (Lab7Main.x <= 3) Lab7Main.quitflag = true;
        for (i = 16;i >= 1;i --) {
            if (Lab7Main.paint[i][2] == '*' && Lab7Main.paint[i][4] == '*' && Lab7Main.paint[i][6] == '*' && Lab7Main.paint[i][8] == '*' && Lab7Main.paint[i][10] == '*' && Lab7Main.paint[i][12] == '*' && Lab7Main.paint[i][14] == '*' && Lab7Main.paint[i][16] == '*' && Lab7Main.paint[i][18] == '*' && Lab7Main.paint[i][20] == '*' && Lab7Main.paint[i][22] == '*' && Lab7Main.paint[i][24] == '*') flag = true;
            if (flag) {
                for (k = i;k >= 1;k --) {
                    for (j = 1;j < 26;j ++) {
                        Lab7Main.paint[k][j] = Lab7Main.paint[k - 1][j];
                    }
                }
                flag = false;
                i = 16;
            }
        }
    }

    private static boolean theLowestCheck(int x,int y,int con,char[][] paint) {
        int[][] place = Lab7ZBlock.diamonds(con);
        boolean check = false;
        int i,j;
        for (i = 0; i < place.length; i ++) {
            for (j = 0; j < place.length; j ++) {
                if (j != i && place[i][1] == place[j][1] && place[i][0] < place[j][0]) {
                    place[i][0] = place[j][0];
                }
            }
        }
        for (i = 0; i < place.length; i ++) {
            int a = place[i][0],b = place[i][1];
            if (paint[x + a + 1][y + b] == '*' || paint[x + a + 1][y + b] == '-') {
                check = true;
                break;
            }
        }
        return check;
    }

    private static void turnCheck(int x,int y,int con,char[][] paint) {
        int[][] place = Lab7ZBlock.diamonds(con);
        int i;
        for (i = 3; i >= 0; i --) {
            int a = place[i][0], b = place[i][1];
            if (paint[x + a + 1][y + b] == '|' || paint[x + a + 1][y + b] == '*' || paint[x + a + 1][y + b] == '-') {
                Lab7Main.warning = true;
                break;
            }
        }
    }

    private static void mainCheck(int x,int y,int con,int order,char[][] paint) {
        int p = 0;
        if (order == 3) p = -2;
        else if (order == 4) p = 2;
        int[][] place = Lab7ZBlock.diamonds(con);
        int i;
        for (i = 3; i >= 0; i --) {
            int a = place[i][0], b = place[i][1];
            if (paint[x + a + 1][y + b + p] == '|' || paint[x + a + 1][y + b + p] == '*' || paint[x + a + 1][y + b + p] == '-') {
                Lab7Main.warning = true;
                break;
            }
        }
    }
}
