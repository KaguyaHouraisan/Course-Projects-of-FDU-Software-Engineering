package Lab9;

public class Brick_L extends Brick {
    @Override
    public int[][] getTheCoordinate(int con) {
        final int[][] coordinate = { {0,-2},{0,0},{0,2},{-1,2} };
        return getTheResultOfTurn(con, coordinate);
    }
}
