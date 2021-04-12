package Project1;

/**
 * 基础部分：给出各个类型方块相对旋转中心坐标
 */
class Diamond {
    /**
     * 根据接受的参数，计算对应的方块在相应旋转状态下，各点相对于旋转中心的坐标
     * @param type 方块的类型
     * @param con 方块的旋转状态
     * @param temp 方块没有旋转时的相对坐标
     * @return 方块在旋转后，各点相对于旋转中心的坐标
     */
    static int[][] diamonds(int type,int con,int[][] temp) {
        switch (con) {
            case 0: {
                return temp;
            }
            default: {
                if (type == 1) {
                    for (int i = 0; i < con; i ++) {
                        for (int j = 0; j < 4; j ++) {
                            int m = temp[j][0],n = temp[j][1];
                            temp[j][0] = n / 2;
                            temp[j][1] = m * (-2) + 2;
                        }
                    }
                    return temp;
                }
                else if (type == 4) return temp;
                else {
                    for (int i = 0; i < con; i ++) {
                        for (int j = 0; j < 4; j ++) {
                            int m = temp[j][0],n = temp[j][1];
                            temp[j][0] = n / 2;
                            temp[j][1] = m * (-2);
                        }
                    }
                    return temp;
                }
            }
        }
    }

    /**
     * 输出对应的方块在没有旋转的情况下，各点相对于旋转中心的坐标
     * @param type 方块的类型
     * @return 方块在没有旋转的情况下，各点相对于旋转中心的坐标
     */
    static int[][] temp(int type){
        if (type == 1) {
            int[][] place = { {0,-2},{0,0},{0,2},{0,4} };
            return place;
        }
        else if (type == 2) {
            int[][] place = { {0,-2},{0,0},{0,2},{-1,-2} };
            return place;
        }
        else if (type == 3) {
            int[][] place = { {0,-2},{0,0},{0,2},{-1,2} };
            return place;
        }
        else if (type == 4) {
            int[][] place = { {0,0},{0,2},{-1,0},{-1,2} };
            return place;
        }
        else if (type == 5) {
            int[][] place = { {-1,2},{0,-2},{0,0},{-1,0} };
            return place;
        }
        else if (type == 6) {
            int[][] place = { {0,-2},{0,0},{0,2},{-1,0} };
            return place;
        }
        else {
            int[][] place = { {-1,-2},{0,0},{0,2},{-1,0} };
            return place;
        }
    }
}
