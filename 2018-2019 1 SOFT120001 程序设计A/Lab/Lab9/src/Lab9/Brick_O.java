package Lab9;

public class Brick_O extends Brick {
    @Override
    public int[][] getTheCoordinate(int con) {
        final int[][] coordinate = { {0,0},{0,2},{-1,0},{-1,2} };
        return getTheResultOfTurn(con, coordinate);
    }

    @Override
    public int[][] getTheResultOfTurn(int con, int[][] place) {
        return place;
    }
}
