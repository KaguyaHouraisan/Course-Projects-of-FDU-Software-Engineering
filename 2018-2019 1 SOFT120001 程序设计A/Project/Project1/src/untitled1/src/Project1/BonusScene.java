package Project1;

import java.util.Scanner;

/**
 * 进阶部分：道具模式
 */
class BonusScene {
    static int numberOfBombs ;

    /**
     * 提示模式规则
     */
    static void firstStep() {
        numberOfBombs = 0;
        System.out.println("\n\n\n在此模式下，您可以消耗200分得分，购买一个道具。\n1.使用该道具可以随机消除一行积累的方块；\n2.购买和使用道具时，方块不会下落；\n3.同时只能拥有不超过五个道具。");
        System.out.print("输入s开始游戏\nInput：");
        for (;;) {
            Scanner input = new Scanner(System.in);
            char choice = input.next().charAt(0);
            if ((choice == 's') || (choice == 'S')) {
                mainStep();
                break;
            }
            System.out.print("请输入符合要求的指令：");
        }
    }

    /**
     * 循环读取输入，进行对应操作并打印界面
     * 与Tetris.mainStep类似
     */
    private static void mainStep() {
        Prepare.rePaint();
        Prepare.rePrepare();
        Prepare.randomPrepare();
        Tetris.nowCheck = Prepare.howToRandom();
        Prepare.preview(Tetris.x,Tetris.y,Tetris.nowCheck,'+');
        Prepare.paintChange(Tetris.x,Tetris.y,Tetris.nowCheck,'*');
        output(Tetris.paint);
        Prepare.preview(Tetris.x,Tetris.y,Tetris.nowCheck,' ');
        Prepare.paintChange(Tetris.x,Tetris.y,Tetris.nowCheck,' ');
        for (;;) {
            input();
            if (Tetris.quitflag) {
                System.out.println("\n\n\n");
                break;
            }
            MainStep.change(Tetris.order);
            Prepare.preview(Tetris.x,Tetris.y,Tetris.nowCheck,'+');
            Prepare.paintChange(Tetris.x,Tetris.y,Tetris.nowCheck,'*');
            output(Tetris.paint);
            Tetris.getTheLowest = Check.theLowestCheck(Tetris.x,Tetris.y,Tetris.con,Tetris.nowCheck,Tetris.paint);
            if (Tetris.getTheLowest) {
                Check.lineCheck(Tetris.x,true);
                Tetris.nowCheck = Tetris.prepareCheck;
                Prepare.rePrepare();
                Prepare.randomPrepare();
                Tetris.getTheLowest = false;
                Tetris.x = 4;
                Tetris.y = 15;
                Tetris.con = 0;
                Prepare.preview(Tetris.x,Tetris.y,Tetris.nowCheck,'+');
                Prepare.paintChange(Tetris.x,Tetris.y,Tetris.nowCheck,'*');
                output(Tetris.paint);
                Prepare.preview(Tetris.x,Tetris.y,Tetris.nowCheck,' ');
            }
            else Prepare.preview(Tetris.x,Tetris.y,Tetris.nowCheck,' ');
            Prepare.paintChange(Tetris.x,Tetris.y,Tetris.nowCheck,' ');
        }
        if (Tetris.quitflag) {
            Tetris.quitflag = false;
            MainStep.firstPrint();
        }
    }

    /**
     * 打印完整的游戏界面及当前的道具数
     * @param mainPaint 当前表示游戏界面的二维数组
     */
    private static void output(char[][] mainPaint) {
        int i,j;
        for (i = 3;i < 4;i++)
            for (j = 0;j < 37;j++ )
                Tetris.paint[i][j] = ' ';
        System.out.println("\n\n\n下一个方块：");
        for (i = 0;i < 25;i++) {
            for (j = 7;j < 30;j++) {
                System.out.print(mainPaint[i][j]);
            }
            System.out.print("\n");
        }
        System.out.println("你当前的分数为：" + Tetris.point + "\n你当前拥有的道具数为：" + numberOfBombs + "\n\nw:旋转   s:维持\na:左移   d:右移\nx:下坠   q:退出\nv:购买道具   b:使用道具");
        System.out.print("请输入你的指令：");
    }

    /**
     * 读取输入的指令
     */
    private static void input() {
        for (;;) {
            boolean flag = false;
            Scanner orderInput = new Scanner(System.in);
            char orderChoice = orderInput.next().charAt(0);
            if (orderChoice >= 'A' && orderChoice <= 'Z') orderChoice += 32;
            switch (orderChoice) {
                case ('w'): {
                    Tetris.order = 1;
                    flag = true;
                    break;
                }
                case ('s'): {
                    Tetris.order = 2;
                    flag = true;
                    break;
                }
                case ('a'): {
                    Tetris.order = 3;
                    flag = true;
                    break;
                }
                case ('d'): {
                    Tetris.order = 4;
                    flag = true;
                    break;
                }
                case ('x'): {
                    Tetris.order = 5;
                    flag = true;
                    break;
                }
                case ('q'): {
                    flag = true;
                    Tetris.quitflag = true;
                    break;
                }
                case ('v'): {
                    Tetris.order = 6;
                    flag = true;
                    break;
                }
                case ('b'): {
                    Tetris.order = 7;
                    flag = true;
                    break;
                }
                default: {
                    System.out.print("请输入符合要求的指令：");
                    break;
                }
            }
            if (flag) break;
        }
        Check.check(Tetris.x,Tetris.y,Tetris.con,Tetris.order,Tetris.nowCheck,Tetris.paint);
    }
}
