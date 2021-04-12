package Project1;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 进阶部分：存档模式
 */
class SaveAndRead {
    private static boolean readOrNot = false;
    static String username;

    /**
     * 提示输入用户昵称，并将其赋给静态字符串 username；提示是否开启
     * 新存档，调用方法 SaveAndRead.mainStep
     */
    static void firstStep() {
        System.out.print("请输入您的昵称（30个以内的英文字母）\nInput：");
        Scanner temp = new Scanner(System.in);
        username = temp.next();
        System.out.print("Y：读取存档  N：开启新存档\nInput：");
        for (;;) {
            Scanner input = new Scanner(System.in);
            char choice = input.next().charAt(0);
            if ((choice == 'y') || (choice == 'Y')) {
                readOrNot = true;
                mainStep();
                break;
            }
            if ((choice == 'n') || (choice == 'N')) {
                mainStep();
                break;
            }
            System.out.print("请输入符合要求的指令：");
        }
    }

    /**
     * 功能与 Tetris.mainStep 类似，增加判断：是否读档并调用
     * SaveAndRead.read 方法，是否存档并调用 SaveAndRead.save 方法
     */
    private static void mainStep() {
        Prepare.rePaint();
        Prepare.rePrepare();
        Prepare.randomPrepare();
        Tetris.nowCheck = Prepare.howToRandom();
        if (readOrNot) {
            read();
            readOrNot = false;
        }
        Prepare.preview(Tetris.x,Tetris.y,Tetris.nowCheck,'+');
        Prepare.paintChange(Tetris.x,Tetris.y,Tetris.nowCheck,'*');
        MainStep.output(Tetris.paint);
        Prepare.preview(Tetris.x,Tetris.y,Tetris.nowCheck,' ');
        Prepare.paintChange(Tetris.x,Tetris.y,Tetris.nowCheck,' ');
        for (;;) {
            MainStep.input();
            if (Tetris.quitflag) {
                System.out.println("\n\n\n");
                break;
            }
            MainStep.change(Tetris.order);
            Prepare.preview(Tetris.x,Tetris.y,Tetris.nowCheck,'+');
            Prepare.paintChange(Tetris.x,Tetris.y,Tetris.nowCheck,'*');
            MainStep.output(Tetris.paint);
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
                MainStep.output(Tetris.paint);
                Prepare.preview(Tetris.x,Tetris.y,Tetris.nowCheck,' ');
            }
            else Prepare.preview(Tetris.x,Tetris.y,Tetris.nowCheck,' ');
            Prepare.paintChange(Tetris.x,Tetris.y,Tetris.nowCheck,' ');
        }
        if (Tetris.quitflag) {
            Tetris.quitflag = false;
            RankingList.ranking();
            System.out.print("是否覆盖原存档？\nY:添加  N：放弃\nInput：");
            for (;;) {
                Scanner input = new Scanner(System.in);
                char choice = input.next().charAt(0);
                if ((choice == 'y') || (choice == 'Y')) {
                    save(Tetris.x,Tetris.y,Tetris.con,Tetris.nowCheck,Tetris.prepareCheck,Tetris.point,Tetris.paint);
                    break;
                }
                if ((choice == 'n') || (choice == 'N')) {
                    break;
                }
                System.out.print("请输入符合要求的指令：");
            }
            System.out.print("\n\n\n");
            MainStep.firstPrint();
        }
    }

    /**
     * 根据静态变量 username 新建或者打开对应存档，并将静态变量 x，y，
     * con、nowCheck、prepareCheck、point、paint 写入存档
     * @param x 正在下落方块的旋转中心纵坐标
     * @param y 正在下落方块的旋转中心横坐标
     * @param con 方块的旋转状态
     * @param nowCheck 正在下落方块的类型
     * @param prepareCheck 下一个方块的类型
     * @param point 当前已获得的分数
     * @param paint 当前表示游戏界面的二维数组
     */
    private static void save(int x,int y,int con,int nowCheck,int prepareCheck,int point,char[][] paint) {
        int i;
        try {
            File writeName = new File(username + ".txt");
            writeName.createNewFile();
            try (FileWriter writer = new FileWriter(writeName,false);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(x + "\r\n" + y + "\r\n" + con + "\r\n" + nowCheck + "\r\n" + prepareCheck + "\r\n" + point + "\r\n");
                for (i = 0; i < 25; i ++) {
                    out.write(paint[i]);
                    out.write("\r\n");
                }
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据静态变量 username 读取对应存档中的内容，赋给静态变量 x，y，
     * con、nowCheck、prepareCheck、point、paint 恢复存档前的情况
     */
    private  static void read() {
        char[][] temp = new char[31][37];
        int i = 0;
        int[] tempInt = new int[6];
        File file = new File(username + ".txt");
        if (file.exists()) {
            try (FileReader reader = new FileReader(username + ".txt");
                 BufferedReader br = new BufferedReader(reader)
            ) {
                String line;
                while ((line = br.readLine()) != null) {
                    char[] string = line.toCharArray();
                    temp[i] = Arrays.copyOf(string, string.length);
                    i++;
                }
                for (i = 0; i < 25; i++) Tetris.paint[i] = Arrays.copyOf(temp[i + 6], temp[i + 6].length);
                for (i = 0; i < 6; i++) {
                    String string = String.copyValueOf(temp[i]);
                    tempInt[i] = Integer.valueOf(string).intValue();
                }
                Tetris.x = tempInt[0];
                Tetris.y = tempInt[1];
                Tetris.con = tempInt[2];
                Tetris.nowCheck = tempInt[3];
                Tetris.prepareCheck = tempInt[4];
                Tetris.point = tempInt[5];
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("当前没有存档，进入新游戏。");
            readOrNot = false;
            mainStep();
        }
    }
    //读取存档
}
