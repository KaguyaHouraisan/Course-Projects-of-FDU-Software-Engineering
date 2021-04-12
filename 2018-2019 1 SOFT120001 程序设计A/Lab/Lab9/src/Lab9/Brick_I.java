package Lab9;

public class Brick_I extends Brick {
    @Override
    public int[][] getTheCoordinate(int con) {
        final int[][] coordinate = { {0,-2},{0,0},{0,2},{0,4} };
        return getTheResultOfTurn(con, coordinate);
    }

    @Override
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
                    temp[j][1] = m * (-2) + 2;
                }
            }
            return temp;
        }
    }
}
