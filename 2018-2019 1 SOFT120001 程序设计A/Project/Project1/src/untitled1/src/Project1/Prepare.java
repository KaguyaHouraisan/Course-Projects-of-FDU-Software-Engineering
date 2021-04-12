package Project1;

import java.util.Random;

/**
 * 基础部分：对二维数组进行操作
 */
class Prepare {
    /**
     * 初始化静态二维数组 paint 中用以显示下一个方块的部分
     */
    static void rePrepare() {
        int i,j;
        for (i = 0;i < 4;i++) {
            for (j = 0;j < 37;j++){
                Tetris.paint[i][j] = ' ';
            }
        }
    }

    /**
     * 初始化静态二维数组 paint 中用以显示主界面的部分
     */
    static void rePaint() {
        int i,j;
        for (i = 0;i < 4;i++) {
            for (j = 0;j < 37;j++){
                Tetris.paint[i][j] = ' ';
            }
        }
        for (i = 4;i < 24;i++) {
            Tetris.paint[i][7] = '|';
            for (j = 8;j < 32;j++) {
                Tetris.paint[i][j] = ' ';
            }
            Tetris.paint[i][29] = '|';
        }
        for (i = 0;i < 37;i++) Tetris.paint[24][i] = '-';
    }

    /**
     * 调用 Check.theLowestCheck 方法与 Prepare.paintChange 方法，
     * 对静态二维数组 paint 中正在下落方块垂直投影所占据的位置赋值‘a’
     * @param x 正在下落方块的旋转中心纵坐标
     * @param y 正在下落方块的旋转中心横坐标
     * @param nowCheck 正在下落方块的类型
     * @param a 需要填充在二维数组中的字符
     */
    static void preview(int x,int y,int nowCheck,char a) {
        int m = x;
        for (;;) {
            if (Check.theLowestCheck(m,y,Tetris.con,nowCheck,Tetris.paint)) {
                Tetris.getTheLowest = false;
                break;
            }
            else m += 1;
        }
        paintChange(m,y,nowCheck,a);
    }

    /**
     * 调用 Diamond.diamonds 方法获得方块相对旋转中心的坐标，
     * 对静态二维数组 paint 中由方块各点坐标所标记的位置赋值‘a’
     * @param x 正在下落方块的旋转中心纵坐标
     * @param y 正在下落方块的旋转中心横坐标
     * @param k 正在下落方块的类型
     * @param a 需要填充在二维数组中的字符
     */
    static void paintChange(int x,int y,int k,char a) {
        int i;
        int[][] place = Diamond.diamonds(k,Tetris.con,Diamond.temp(k));
        for (i = 0;i < 4;i ++) {
            int c = place[i][0],b = place[i][1];
            Tetris.paint[x + c][y + b] = a;
        }
    }

    /**
     * 取一个 1-7 之间的随机数
     * @return 1-7 之间的随机整型数字
     */
    static int howToRandom() {
        Random random = new Random();
        return (random.nextInt(7 ) + 1);
    }

    /**
     * 调用 Prepare.howToRandom 方法与 Diamond.diamonds 方法获得下
     * 一个方块的类型与它相对旋转中心的坐标，对静态二维数组 paint 中显
     * 示下一个方块的区域内由该方块各点坐标所标记的位置赋值‘*’
     */
    static void randomPrepare() {
        int i;
        Tetris.prepareCheck = howToRandom();
        int[][] place = Diamond.diamonds(Tetris.prepareCheck,0,Diamond.temp(Tetris.prepareCheck));
        for (i = 0;i < 4;i ++) {
            int a = place[i][0],b = place[i][1];
            Tetris.paint[1 + a][9 + b] = '*';
        }
    }
}
