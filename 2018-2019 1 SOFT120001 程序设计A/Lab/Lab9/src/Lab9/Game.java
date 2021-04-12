package Lab9;

import java.util.Scanner;

class Game {
    static void rePaint() {
        int i,j;
        for (i = 0; i < 27; i ++) Main.paint[0][i] = ' ';
        for (i = 1; i < 16; i ++) {
            Main.paint[i][0] = '|';
            Main.paint[i][26] = '|';
            for (j = 1; j < 26; j ++) Main.paint[i][j] = ' ';
        }
        Main.paint[16][0] = ' ';
        Main.paint[16][26] = ' ';
        for (i = 1; i < 26; i ++) Main.paint[16][i] = '-';
        System.out.println("Start...");
    }

    static void output(Brick brick) {
        int i;
        int[][] place = brick.getTheCoordinate(brick.con);
        for (i = 0; i < 4; i++) {
            int a = place[i][0], b = place[i][1];
            Main.paint[brick.x + a][brick.y + b] = '*';
        }
        System.out.print("\n\n\n");
        for (i = 1; i < 17; i++) System.out.println(Main.paint[i]);
        if (!Main.getTheLowest) {
            for (i = 0; i < 4; i++) {
                int a = place[i][0], b = place[i][1];
                Main.paint[brick.x + a][brick.y + b] = ' ';
            }
        }
    }

    static void input(Brick brick) {
        for (;;) {
            boolean flag = true;
            Scanner orderInput = new Scanner(System.in);
            char orderChoice = orderInput.next().charAt(0);
            if (orderChoice >= 'A' && orderChoice <= 'Z') orderChoice += 32;
            switch (orderChoice) {
                case ('w'): {
                    brick.turn();
                    break;
                }
                case ('s'): {
                    brick.down();
                    break;
                }
                case ('a'): {
                    brick.left();
                    break;
                }
                case ('d'): {
                    brick.right();
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
}
