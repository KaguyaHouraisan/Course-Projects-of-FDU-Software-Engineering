package Project1;

import java.util.Scanner;

/**
 * 基础部分：执行输入输出
 */
class MainStep {
    /**
     * 打印开始界面，判断是否进入游戏及采取哪种游戏模式，对不同指令分别调用不同方法
     */
    static void firstPrint() {
        Tetris.x = 4;
        Tetris.y = 17;
        Tetris.con = 0;
        Tetris.point = 0;
        System.out.println(" ---------------------------------------------------\n|                       Tetris                      |\n| - - - - - - - - - - - - - - - - - - - - - - - - - |\n|                        input                      |\n|                    s: Start Game                  |\n|                    q: Quit Game                   |\n|                    v: Save/Read                   |\n|                    b: Ranking List                |\n|                    n: Bonus Scene                 |\n|                    m: Random Pattern              |\n ---------------------------------------------------\nPS：只有存档模式的得分将计入排行榜！");
        System.out.print("Please input your command:");
        for (;;) {
            Scanner input = new Scanner(System.in);
            char choice = input.next().charAt(0);
            if ((choice == 's') || (choice == 'S')) {
                Tetris.mainStep();
                break;
            }
            if ((choice == 'q') || (choice == 'Q')) {
                System.out.println("Game over.");
                break;
            }
            if ((choice == 'v') || (choice == 'V')) {
                SaveAndRead.firstStep();
                break;
            }
            if ((choice == 'b') || (choice == 'B')) {
                RankingList.output();
                break;
            }
            if ((choice == 'n') || (choice == 'N')) {
                BonusScene.firstStep();
                break;
            }
            if ((choice == 'm') || (choice == 'M')) {
                RandomBlocks.firstStep();
                break;
            }
            System.out.print("请输入符合要求的指令：");
        }
    }

    /**
     * 读取输入的指令，对静态整型变量 order 赋相应的值
     */
    static void input() {
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
                default: {
                    System.out.print("请输入符合要求的指令：");
                    break;
                }
            }
            if (flag) break;
        }
        Check.check(Tetris.x,Tetris.y,Tetris.con,Tetris.order,Tetris.nowCheck,Tetris.paint);
    }

    /**
     * 根据 order 的值，调用对应 Check 类中的方法检测该操作能否进行，
     * 根据检测结果对静态变量 x，y，con 及其他变量进行操作
     * @param order 下一步进行的操作
     */
    static void change(int order) {
        switch (order) {
            case 1: {
                Check.turnCheck(Tetris.x,Tetris.y,order,Tetris.nowCheck,Tetris.paint);
                if (Tetris.warning) {
                    Tetris.x = Tetris.x + 1;
                    Tetris.warning = false;
                }
                else {
                    Tetris.con = (Tetris.con + 1) % 4;
                    Tetris.x = Tetris.x + 1;
                    Tetris.getTheLowest = Check.theLowestCheck(Tetris.x, Tetris.y, Tetris.con, Tetris.nowCheck, Tetris.paint);
                }
                break;
            }
            case 2: {
                Tetris.x = Tetris.x + 1;
                Tetris.getTheLowest = Check.theLowestCheck(Tetris.x,Tetris.y,Tetris.con,Tetris.nowCheck,Tetris.paint);
                break;
            }
            case 3: {
                Check.check(Tetris.x,Tetris.y,Tetris.con,Tetris.order,Tetris.nowCheck,Tetris.paint);
                if (Tetris.warning) {
                    Tetris.x = Tetris.x + 1;
                    Tetris.warning = false;
                }
                else {
                    Tetris.y = Tetris.y - 2;
                    Tetris.x = Tetris.x + 1;
                    Tetris.getTheLowest = Check.theLowestCheck(Tetris.x, Tetris.y, Tetris.con, Tetris.nowCheck, Tetris.paint);
                }
                break;
            }
            case 4: {
                Check.check(Tetris.x,Tetris.y,Tetris.con,Tetris.order,Tetris.nowCheck,Tetris.paint);
                if (Tetris.warning) {
                    Tetris.x = Tetris.x + 1;
                    Tetris.warning = false;
                }
                else {
                    Tetris.y = Tetris.y + 2;
                    Tetris.x = Tetris.x + 1;
                    Tetris.getTheLowest = Check.theLowestCheck(Tetris.x, Tetris.y, Tetris.con, Tetris.nowCheck, Tetris.paint);
                }
                break;
            }
            case 5: {
                for (;;) {
                    Tetris.getTheLowest = Check.theLowestCheck(Tetris.x,Tetris.y,Tetris.con,Tetris.nowCheck,Tetris.paint);
                    if (Tetris.getTheLowest) break;
                    else Tetris.x += 1;
                }
                break;
            }
            case 6: {
                if (Tetris.point >= 200 && BonusScene.numberOfBombs < 5) {
                    Tetris.point = Tetris.point - 200;
                    BonusScene.numberOfBombs++;
                }
                if (Tetris.point < 200) {
                    System.out.println("\n\n\n您的分数不足！");
                }
                if (BonusScene.numberOfBombs >= 5) {
                    System.out.println("\n\n\n您的道具数已达到上限！");
                }
                break;
            }
            case 7: {
                if (BonusScene.numberOfBombs > 0) {
                    for (; ; ) {
                        int a = (int)(10 + Math.random() * (24 - 10 + 1));
                        boolean flag = false;
                        for (int i = 9; i <= 27; i += 2) {
                            if (Tetris.paint[a][i] == '*') {
                                flag = true;
                                break;
                            }
                        }
                        if (flag) {
                            for (int i = 9; i <= 27; i += 2) Tetris.paint[a][i] = '*';
                            break;
                        }
                    }
                    BonusScene.numberOfBombs --;
                }
                else {
                    System.out.println("\n\n\n您没有足够的道具！");
                }
                Check.lineCheck(Tetris.x,false);
                break;
            }
        }
    }

    /**
     * 打印完整的游戏界面
     * @param mainPaint 当前表示游戏界面的二维数组
     */
    static void output(char[][] mainPaint) {
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
        System.out.println("你当前的分数为：" + Tetris.point +"\n\n\nw:旋转   s:维持\na:左移   d:右移\nx:下坠   q:退出");
        System.out.print("请输入你的指令：");
    }
}
