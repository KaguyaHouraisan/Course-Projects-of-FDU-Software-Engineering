package Lab7;

class Lab7ZBlock {
    static int[][] diamonds(int con) {
        switch (con) {
            case 0: {
                int[][] place = { {-1,-2},{-1,0},{0,0},{0,2} };
                return place;
            }
            case 1: {
                int[][] place = { {-1,2},{0,0},{0,2},{1,0} };
                return place;
            }
            case 2: {
                int[][] place = { {0,-2},{0,0},{1,0},{1,2} };
                return place;
            }
            default: {
                int[][] place = { {-1,0},{0,-2},{0,0},{1,-2} };
                return place;
            }
        }
    }
}
