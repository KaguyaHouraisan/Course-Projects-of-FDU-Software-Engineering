package Test;
import java.util.Scanner;

//老师好！考虑到第一个指令为旋转的情况，我额外加了一个功能，如果在初始位置上反复旋转，
//当方块有超过上界的部分时会在上方额外显示一行，此时是16行可移动区域，向下移动或者旋转到没有超出部分时会消失，谢谢老师。

public class lab3 {
    public static void main(String[] Args) {
        firstStep();
        nextStep();
    }

    private static void firstStep() {
        int i;
        System.out.println("Start...");
        System.out.println("|            * *          |");
        for (i = 0;i < 14;i++) System.out.println("|                         |");
        System.out.print(" ");
        for (i = 0;i < 25;i++) System.out.print("-");
        System.out.print("\n\nEnter:");
    }

    private static boolean quitflag = false;
    private static boolean flag = false;
    private static int warning = 0;
    private static int x = 12,y = 1,con = 1,order;

    private static void nextStep() {
        for(;;) {
            for (;;) {
                mainCheck();
                if (flag) break;
            }
            checkPrint();
            switch (warning) {
                case 0: {
                    mainPrint();
                    break;
                }
                case 1: {
                    for (;;) {
                        if (order == 3) {
                            howToPaint(x, y);
                            mainCheck();
                        }
                        else {
                            mainPrint();
                            warning = 0;
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    for (;;) {
                        if (order == 4) {
                            howToPaint(x, y);
                            mainCheck();
                        } else {
                            mainPrint();
                            warning = 0;
                            break;
                        }
                    }
                    break;
                }
            }
            if (quitflag) {
                gameOver();
                break;
            }
        }
    }

    private static void checkPrint() {
        if ((((con == 2) || (con == 0)) && (y == 13)) || (((con == 1) || (con == 3)) && (y == 14))) quitflag = true;
        else if (((con != 2) && (x <= 3)) || ((con == 2) && (x <= 1))) {
            warning = 1;
        }
        else if (((con != 0) && (x >= 21)) || ((con ==0) && (x >= 23))) {
            warning = 2;
        }
    }

    private static void mainPrint() {
        switch (order){
            case 1: {
                if (((con == 2) && (x <= 1)) || ((con == 0) && (x >= 23))) {
                    System.out.println("此处不能旋转。");
                    howToPaint(x,y);
                    break;
                }
                else {
                    con = (con + 1) % 4;
                    if (con == 3) y = y + 1;
                    if (con == 0) y = y - 1;
                    howToPaint(x, y);
                    break;
                }
            }
            case 2: {
                y = y + 1;
                howToPaint(x,y);
                break;
            }
            case 3: {
                x = x - 2;
                howToPaint(x,y);
                break;
            }
            case 4: {
                x = x + 2;
                howToPaint(x,y);
                break;
            }
        }
    }

    private static void howToPaint(int placeX,int placeY) {
        int i;
        for (i = 0;i < placeY - 2;i++) System.out.println("|                         |");
        switch (con) {
            case 1: {
                typeOnePrint(placeX);
                break;
            }
            case 2: {
                System.out.print("|");
                for (i = 0;i < placeX + 1;i++) System.out.print(" ");
                System.out.print(" *");
                for (i = placeX + 3;i < 25;i++) System.out.print(" ");
                System.out.print("|\n|");
                for (i = 0;i < placeX;i++) System.out.print(" ");
                System.out.print("* *");
                for (i = placeX + 3;i < 25;i++) System.out.print(" ");
                System.out.print("|\n|");
                for (i = 0;i < placeX;i++) System.out.print(" ");
                System.out.print("* ");
                for (i = placeX + 2;i < 25;i++) System.out.print(" ");
                System.out.print("|\n");
                break;
            }
            case 3: {
                typeOnePrint(placeX);
                break;
            }
            case 0: {
                System.out.print("|");
                for (i = 0; i < placeX - 1; i++) System.out.print(" ");
                System.out.print(" *");
                for (i = placeX + 1; i < 25; i++) System.out.print(" ");
                System.out.print("|\n|");
                for (i = 0; i < placeX - 2; i++) System.out.print(" ");
                System.out.print("* *");
                for (i = placeX + 1; i < 25; i++) System.out.print(" ");
                System.out.print("|\n|");
                for (i = 0; i < placeX - 2; i++) System.out.print(" ");
                System.out.print("* ");
                for (i = placeX; i < 25; i++) System.out.print(" ");
                System.out.print("|\n");
                break;
            }
        }
        if (quitflag) {
            if((con == 1) || (con == 3)) System.out.print("|\n");
            System.out.print(" ");
            for (i = 0;i < 25;i++) System.out.print("-");
        }
        else {
            if((con == 1) || (con == 3)) System.out.println("|\n|                         |");
            for (i = placeY + 1;i < 15;i++) System.out.println("|                         |");
            System.out.print(" ");
            for (i = 0;i < 25;i++) System.out.print("-");
            System.out.print("\n\nEnter:");
        }
    }

    private static void mainCheck() {
        Scanner orderInput = new Scanner(System.in);
        char orderChoice = orderInput.next().charAt(0);
        switch (orderChoice) {
            case ('w'): {
                order = 1;
                flag = true;
                break;
            }
            case ('s'): {
                order = 2;
                flag = true;
                break;
            }
            case ('a'): {
                order = 3;
                flag = true;
                break;
            }
            case ('d'): {
                order = 4;
                flag = true;
                break;
            }
            default: {
                anotherInput();
                break;
            }
        }
    }

    private static void typeOnePrint(int placeX) {
        int i;
        System.out.print("|");
        for (i = 0;i < placeX - 2;i++) System.out.print(" ");
        System.out.print("* *");
        for (i = placeX + 1;i < 25;i++) System.out.print(" ");
        System.out.print("|\n|");
        for (i = 0;i < placeX;i++) System.out.print(" ");
        System.out.print("* *");
        for (i = placeX + 3;i < 25;i++) System.out.print(" ");
    }

    private static void gameOver() {
        System.out.println("\n\nEnd...");
    }

    private static void anotherInput() {
        System.out.println("请输入符合要求的指令：");
    }
}
