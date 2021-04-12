package Project1;

/**
 * 基础部分：包含各种检测判断方法
 */
class Check {
    /**
     * 检查方块在下一步的操作中是否会碰壁或与其他方块碰撞
     * @param x 正在下落方块的旋转中心纵坐标
     * @param y 正在下落方块的旋转中心横坐标
     * @param con 方块的旋转状态
     * @param order 下一步操作的类型
     * @param nowCheck 正在下落方块的类型
     * @param paint 当前表示游戏界面的二维数组
     */
    static void check(int x,int y,int con,int order,int nowCheck,char[][] paint) {
        int p = 0;
        if (order == 3) p = -2;
        else if (order == 4) p = 2;
        int[][] place = Diamond.diamonds(nowCheck,con,Diamond.temp(nowCheck));
        boolean check = false;
        int i;
        for (i = 3; i >= 0; i --) {
            int a = place[i][0], b = place[i][1];
            if (paint[x + a + 1][y + b + p] == '|' || paint[x + a + 1][y + b + p] == '*' || paint[x + a + 1][y + b + p] == '-') {
                check = true;
                break;
            }
        }
        Tetris.warning = check;
    }

    /**
     * 检查是否已经填满了空行以及是否游戏失败
     * @param x 正在下落方块的旋转中心纵坐标
     * @param type 当前游戏所处的模式
     */
    static void lineCheck(int x,boolean type) {
        int i,j,k,p = 0,result = 1;
        boolean flag = false;
        if (x <= 8 && type) Tetris.quitflag = true;
        for (i = 24;i >= 4;i --) {
            if (Tetris.paint[i][25] == '*' && Tetris.paint[i][27] == '*' && Tetris.paint[i][9] == '*' && Tetris.paint[i][11] == '*' && Tetris.paint[i][13] == '*' && Tetris.paint[i][15] == '*' && Tetris.paint[i][17] == '*' && Tetris.paint[i][19] == '*' && Tetris.paint[i][21] == '*' && Tetris.paint[i][23] == '*') flag = true;
            if (flag) {
                p ++;
                for (k = i;k >=5;k --) {
                    for (j = 8;j < 30;j ++) {
                        Tetris.paint[k][j] = Tetris.paint[k - 1][j];
                    }
                }
                flag = false;
                i = 24;
            }
        }
        for (i = 1;i < p;i ++) result *= 2;
        Tetris.point = Tetris.point + result * 10 * p;
    }

    /**
     * 判断方块是否已到达容器底部
     * @param x 正在下落方块的旋转中心纵坐标
     * @param y 正在下落方块的旋转中心横坐标
     * @param con 方块的旋转状态
     * @param nowCheck 正在下落方块的类型
     * @param paint 当前表示游戏界面的二维数组
     * @return 当前下落方块是否已到达底部
     */
    static boolean theLowestCheck(int x,int y,int con,int nowCheck,char[][] paint) {
        int[][] place = Diamond.diamonds(nowCheck,con,Diamond.temp(nowCheck));
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

    /**
     * 判断此处能否进行旋转
     * @param x 正在下落方块的旋转中心纵坐标
     * @param y 正在下落方块的旋转中心横坐标
     * @param con 方块的旋转状态
     * @param nowCheck 正在下落方块的类型
     * @param paint 当前表示游戏界面的二维数组
     */
    static void turnCheck(int x,int y,int con,int nowCheck,char[][] paint) {
        int[][] place = Diamond.diamonds(nowCheck,(con + 1) % 4,Diamond.temp(nowCheck));
        boolean check = false;
        int i;
        for (i = 3; i >= 0; i --) {
            int a = place[i][0], b = place[i][1];
            if (paint[x + a + 1][y + b] == '|' || paint[x + a + 1][y + b] == '*' || paint[x + a + 1][y + b] == '-') {
                check = true;
                break;
            }
        }
        Tetris.warning = check;
    }
}
