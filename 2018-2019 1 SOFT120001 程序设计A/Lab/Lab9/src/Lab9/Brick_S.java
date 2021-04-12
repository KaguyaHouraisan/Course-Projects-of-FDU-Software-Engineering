package Lab9;

public class Brick_S extends Brick {
    @Override
    public int[][] getTheCoordinate(int con) {
        final int[][] coordinate = { {-1,2},{0,-2},{0,0},{-1,0} };
        return getTheResultOfTurn(con, coordinate);
    }
}
