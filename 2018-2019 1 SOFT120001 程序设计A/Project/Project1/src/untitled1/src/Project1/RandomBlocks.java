package Project1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * 进阶部分：随机方块
 */
class RandomBlocks {
    private static int[][][] prepareBlocks = new int[7][15][2];

    /**
     * 提示游戏模式规则，展示随机生成的 7 个方块
     */
    static void firstStep() {
        System.out.println("随机生成的方块如下：");
        randomFunc();
        for (int i = 0; i < 7; i++) {
            char[][] temp = { {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},{' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},{' ',' ',' ',' ',' ',' ',' ',' ',' ',' '} };
            for (int j = 0; j < prepareBlocks[i].length; j++) {
                int a = prepareBlocks[i][j][0],b = prepareBlocks[i][j][1];
                temp[a + 1][b + 5] = '*';
            }
            for (int j = 0; j < 3; j++) {
                System.out.println(temp[j]);
            }
            System.out.print("\n");
        }
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
     * 功能与 Tetris.mainStep 类似
     */
    private static void mainStep() {
        Tetris.x = 5;
        Prepare.rePaint();
        Prepare.rePrepare();
        randomPrepare();
        Tetris.nowCheck = Prepare.howToRandom() - 1;
        preview(Tetris.x,Tetris.y,Tetris.con,Tetris.nowCheck,'+');
        paintChange(Tetris.x,Tetris.y,Tetris.con,Tetris.nowCheck,'*');
        output(Tetris.paint);
        preview(Tetris.x,Tetris.y,Tetris.con,Tetris.nowCheck,' ');
        paintChange(Tetris.x,Tetris.y,Tetris.con,Tetris.nowCheck,' ');
        for (;;) {
            MainStep.input();
            if (Tetris.quitflag) {
                System.out.println("\n\n\n");
                break;
            }
            change(Tetris.order);
            preview(Tetris.x,Tetris.y,Tetris.con,Tetris.nowCheck,'+');
            paintChange(Tetris.x,Tetris.y,Tetris.con,Tetris.nowCheck,'*');
            output(Tetris.paint);
            Tetris.getTheLowest = theLowestCheck(Tetris.x,Tetris.y,Tetris.con,Tetris.nowCheck,Tetris.paint);
            if (Tetris.getTheLowest) {
                Check.lineCheck(Tetris.x,true);
                Tetris.nowCheck = Tetris.prepareCheck;
                Prepare.rePrepare();
                randomPrepare();
                Tetris.getTheLowest = false;
                Tetris.x = 5;
                Tetris.y = 15;
                Tetris.con = 0;
                preview(Tetris.x,Tetris.y,Tetris.con,Tetris.nowCheck,'+');
                paintChange(Tetris.x,Tetris.y,Tetris.con,Tetris.nowCheck,'*');
                output(Tetris.paint);
            }
            preview(Tetris.x,Tetris.y,Tetris.con,Tetris.nowCheck,' ');
            paintChange(Tetris.x,Tetris.y,Tetris.con,Tetris.nowCheck,' ');
        }
        if (Tetris.quitflag) {
            Tetris.quitflag = false;
            MainStep.firstPrint();
        }
    }

    /**
     * 调用 RandomBlocks.diamonds 方法获得下一个方块的类型与它相对旋
     * 转中心的坐标，对静态二维数组 paint 中显示下一个方块的区域内由该
     * 方块各点坐标所标记的位置赋值‘*’
     */
    private static void randomPrepare() {
        int i;
        Tetris.prepareCheck = Prepare.howToRandom() - 1;
        int[][] place = diamonds(0,prepareBlocks[Tetris.prepareCheck]);
        for (i = 0;i < prepareBlocks[Tetris.prepareCheck].length;i ++) {
            int a = place[i][0],b = place[i][1];
            Tetris.paint[1 + a][18 + b] = '*';
        }
    }

    /**
     * 调用 differentDiamonds 方法获取 7 个随机方块在未旋转状态下的各点
     * 相对于旋转中心的坐标，并将它们存放到三维静态数组 prepareBlocks 中
     */
    private static void randomFunc() {
        for (int i = 0; i < 7; i++) {
            int[][] temp = differentDiamonds();
            for (int j = 0; j < temp.length; j++) {
                prepareBlocks[i][j] = Arrays.copyOf(temp[j],temp[j].length);
            }
        }
    }

    /**
     * 调用 RandomBlocks.diamonds 方法获得方块相对旋转中心的坐标，
     * 对静态二维数组 paint 中由方块各点坐标所标记的位置赋值‘a’
     * @param x 正在下落方块的旋转中心纵坐标
     * @param y 正在下落方块的旋转中心横坐标
     * @param con 方块的旋转状态
     * @param k 正在下落方块的类型
     * @param a 需要填充在二维数组中的字符
     */
    private static void paintChange(int x,int y,int con,int k,char a) {
        int i;
        int[][] place = diamonds(con,prepareBlocks[k]);
        for (i = 0;i < prepareBlocks[k].length;i ++) {
            int c = place[i][0],b = place[i][1];
            Tetris.paint[x + c][y + b] = a;
        }
    }

    /**
     * 随机产生一个最大不超过 3*5 的方块各点相对于旋转中心的坐标
     * @return 用以存放相对坐标的二维数组
     */
    private static int[][] differentDiamonds() {
        int[][] prepare = new int[15][2];
        int x = 0,y = 0;
        prepare[0][0] = 0;
        prepare[0][1] = 0;
        for (int i = 1; i < 15; i ++) {
            Random random = new Random();
            int n = random.nextInt(4) + 1;
            if (n == 1) {
                if (x == -1) {
                    x = 0;
                    y = 0;
                }
                else {
                    x--;
                }
            }
            if (n == 2) {
                if (y == 4) {
                    x = 0;
                    y = 0;
                }
                else {
                    y += 2;
                }
            }
            if (n == 3) {
                if (x == 1) {
                    x = 0;
                    y = 0;
                }
                else {
                    x++;
                }
            }
            if (n == 4) {
                if (y == -4) {
                    x = 0;
                    y = 0;
                }
                else {
                    y -= 2;
                }
            }
            prepare[i][0] = x;
            prepare[i][1] = y;
        }
        return prepare;
    }

    /**
     * 调用 RandomBlocks.theLowestCheck 方法与
     * RandomBlocks.paintChange 方法，对静态二维数组 paint 中正在下落
     * 方块垂直投影所占据的位置赋值‘a’
     * @param x 正在下落方块的旋转中心纵坐标
     * @param y 正在下落方块的旋转中心横坐标
     * @param con 方块的旋转状态
     * @param nowCheck 正在下落方块的类型
     * @param a 需要填充在二维数组中的字符
     */
    private static void preview(int x,int y,int con,int nowCheck,char a) {
        int m = x;
        for (;;) {
            if (theLowestCheck(m,y,con,nowCheck,Tetris.paint)) {
                Tetris.getTheLowest = false;
                break;
            }
            else m += 1;
        }
        paintChange(m,y,con,nowCheck,a);
    }

    /**
     * 打印完整的游戏界面
     * @param mainPaint 当前表示游戏界面的二维数组
     */
    private static void output(char[][] mainPaint) {
        int i,j;
        for (j = 0;j < 37;j++ )
            Tetris.paint[3][j] = ' ';
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

    /**
     * 判断方块是否已到达容器底部
     * @param x 正在下落方块的旋转中心纵坐标
     * @param y 正在下落方块的旋转中心横坐标
     * @param con 方块的旋转状态
     * @param nowCheck 正在下落方块的类型
     * @param paint 当前表示游戏界面的二维数组
     * @return 方块是否已到达容器底部
     */
    private static boolean theLowestCheck(int x,int y,int con,int nowCheck,char[][] paint) {
        boolean check = false;
        int i,j;
        int[][] place = diamonds(con,prepareBlocks[nowCheck]);
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

    /**
     * 根据 order 的值，调用对应 Check 类中的方法检测该操作能否进行，
     * 根据检测结果对静态变量 x，y，con 及其他变量进行操作
     * @param order 下一步进行的操作
     */
    private static void change(int order) {
        switch (order) {
            case 1: {
                turnCheck(Tetris.x,Tetris.y,Tetris.con,Tetris.nowCheck,Tetris.paint);
                if (Tetris.warning) {
                    Tetris.x = Tetris.x + 1;
                    Tetris.warning = false;
                }
                else {
                    Tetris.con = (Tetris.con + 1) % 4;
                    Tetris.x = Tetris.x + 1;
                    Tetris.getTheLowest = theLowestCheck(Tetris.x, Tetris.y, Tetris.con, Tetris.nowCheck, Tetris.paint);
                }
                break;
            }
            case 2: {
                Tetris.x = Tetris.x + 1;
                Tetris.getTheLowest = theLowestCheck(Tetris.x,Tetris.y, Tetris.con, Tetris.nowCheck,Tetris.paint);
                break;
            }
            case 3: {
                check(Tetris.x,Tetris.y,Tetris.con,Tetris.order,Tetris.nowCheck,Tetris.paint);
                if (Tetris.warning) {
                    Tetris.x = Tetris.x + 1;
                    Tetris.warning = false;
                }
                else {
                    Tetris.y = Tetris.y - 2;
                    Tetris.x = Tetris.x + 1;
                    Tetris.getTheLowest = theLowestCheck(Tetris.x, Tetris.y, Tetris.con, Tetris.nowCheck, Tetris.paint);
                }
                break;
            }
            case 4: {
                check(Tetris.x,Tetris.y,Tetris.con,Tetris.order,Tetris.nowCheck,Tetris.paint);
                if (Tetris.warning) {
                    Tetris.x = Tetris.x + 1;
                    Tetris.warning = false;
                }
                else {
                    Tetris.y = Tetris.y + 2;
                    Tetris.x = Tetris.x + 1;
                    Tetris.getTheLowest = theLowestCheck(Tetris.x, Tetris.y, Tetris.con, Tetris.nowCheck, Tetris.paint);
                }
                break;
            }
            case 5: {
                for (;;) {
                    Tetris.getTheLowest = theLowestCheck(Tetris.x,Tetris.y, Tetris.con, Tetris.nowCheck,Tetris.paint);
                    if (Tetris.getTheLowest) break;
                    else Tetris.x += 1;
                }
                break;
            }
        }
    }

    /**
     * 检查方块在下一步的平移操作中是否会碰壁或与其他方块碰撞
     * @param x 正在下落方块的旋转中心纵坐标
     * @param y 正在下落方块的旋转中心横坐标
     * @param con 方块的旋转状态
     * @param order 下一步操作的类型
     * @param nowCheck 正在下落方块的类型
     * @param paint 当前表示游戏界面的二维数组
     */
    private static void check(int x,int y,int con,int order,int nowCheck,char[][] paint) {
        int p = 0,i;
        if (order == 3) p = -2;
        else if (order == 4) p = 2;
        int[][] place = diamonds(con,prepareBlocks[nowCheck]);
        boolean check = false;
        for (i = prepareBlocks[nowCheck].length - 1; i >= 0; i --) {
            int a = place[i][0], b = place[i][1];
            if (paint[x + a + 1][y + b + p] == '|' || paint[x + a + 1][y + b + p] == '*' || paint[x + a + 1][y + b + p] == '-') {
                check = true;
                break;
            }
        }
        Tetris.warning = check;
    }

    /**
     * 根据接受的参数，计算对应的方块在相应旋转状态下，各点相对于旋转中心的坐标
     * @param con 方块的旋转状态
     * @param temp 方块没有旋转时的相对坐标
     * @return 用以存放相对坐标的二维数组
     */
    private static int[][] diamonds(int con,int[][] temp) {
        int[][] result = new int[temp.length][temp[0].length];
        for (int i = 0; i < temp.length; i++) {
            result[i] = Arrays.copyOf(temp[i],temp[i].length);
        }
        switch (con) {
            case 0: {
                return result;
            }
            default: {
                for (int i = 0; i < con; i ++) {
                    for (int j = 0; j < result.length; j ++) {
                        int m = result[j][0],n = result[j][1];
                        result[j][0] = n / 2;
                        result[j][1] = m * (-2);
                    }
                }
                return result;
            }
        }
    }

    /**
     * 检查方块在下一步的旋转操作中是否会碰壁或与其他方块碰撞
     * @param x 正在下落方块的旋转中心纵坐标
     * @param y 正在下落方块的旋转中心横坐标
     * @param con 方块的旋转状态
     * @param nowCheck 正在下落方块的类型
     * @param paint 当前表示游戏界面的二维数组
     */
    private static void turnCheck(int x,int y,int con,int nowCheck,char[][] paint) {
        int[][] place = diamonds((con + 1) % 4,prepareBlocks[nowCheck]);
        boolean check = false;
        for (int i = 0; i < prepareBlocks[nowCheck].length; i ++) {
            int a = place[i][0], b = place[i][1];
            if (paint[x + a + 1][y + b] == '|' || paint[x + a + 1][y + b] == '*' || paint[x + a + 1][y + b] == '-') {
                check = true;
                break;
            }
        }
        Tetris.warning = check;
    }
}
