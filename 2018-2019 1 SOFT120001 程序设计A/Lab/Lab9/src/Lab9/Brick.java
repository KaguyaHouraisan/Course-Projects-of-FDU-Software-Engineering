package Lab9;

abstract class Brick {
    public int con,x,y;

    Brick() {
        con = 0;
        x = 1;
        y = 12;
    }

    public int[][] getTheCoordinate(int con) {
        final int[][] coordinate = { {0,0},{0,0},{0,0},{0,0} };
        return getTheResultOfTurn(con, coordinate);
    }

    void down() {
        if (!downCheck(x, Main.paint))  x++;
        else Main.getTheLowest = true;
    }

    void left() {
        if (!leftOrRightCheck(-2, Main.paint))  y -= 2;
    }

    void right() {
        if (!leftOrRightCheck(2, Main.paint))  y += 2;
    }

    void turn() {
        if (!turnCheck(Main.paint))  con = (con + 1) % 4;
    }

    public int[][] getTheResultOfTurn(int con, int[][] place) {
        if (con == 0) return place;
        else {
            int[][] temp = new int[4][2];
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 2; j++)
                    temp[i][j] = place[i][j];
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

    private boolean leftOrRightCheck(int p, char[][] paint) {
        int[][] coordinate = getTheCoordinate(con);
        int i;
        for (i = 3; i >= 0; i --) {
            int a = coordinate[i][0], b = coordinate[i][1];
            if (paint[x + a + 1][y + b + p] == '|' || paint[x + a + 1][y + b + p] == '*' || paint[x + a + 1][y + b + p] == '-') {
                return true;
            }
        }
        return false;
    }

    private boolean turnCheck(char[][] paint) {
        int[][] coordinate = getTheCoordinate((con + 1) % 4);
        int i;
        for (i = 3; i >= 0; i --) {
            int a = coordinate[i][0], b = coordinate[i][1];
            if (paint[x + a + 1][y + b] == '|' || paint[x + a + 1][y + b] == '*' || paint[x + a + 1][y + b] == '-') {
                return true;
            }
        }
        return false;
    }

    boolean downCheck(int x, char[][] paint) {
        int[][] coordinate = getTheCoordinate(con);
        int i,j;
        for (i = 0; i < coordinate.length; i ++) {
            for (j = 0; j < coordinate.length; j ++) {
                if (j != i && coordinate[i][1] == coordinate[j][1] && coordinate[i][0] < coordinate[j][0]) {
                    coordinate[i][0] = coordinate[j][0];
                }
            }
        }
        for (i = 0; i < coordinate.length; i ++) {
            int a = coordinate[i][0],b = coordinate[i][1];
            if (paint[x + a + 1][y + b] == '*' || paint[x + a + 1][y + b] == '-') {
                return true;
            }
        }
        return false;
    }
}
