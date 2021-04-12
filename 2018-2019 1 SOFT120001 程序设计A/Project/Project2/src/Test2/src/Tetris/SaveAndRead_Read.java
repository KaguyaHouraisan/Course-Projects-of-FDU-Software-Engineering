package Tetris;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * 用以完成存档的读取
 */
class SaveAndRead_Read {
    static int x, y, currentShapeType, nextShapeType, score, timeCount;
    static boolean isSpeedUp, isBlindMode;
    static int[][] shapeArray = new int[4][4];
    static int[][] boardArray = new int[Game.BOARD_ROWS][Game.BOARD_COLS];

    static void readLastFile() {
        char[][] temp = new char[33][10];
        Game.currentShape = new Shape();
        Game.nextShape = new Shape();
        int i = 0, j;
        int[] tempInt = new int[8];
        File file = new File(Game.username + ".txt");
        if (file.exists()) {
            Game.isSaveAndRead = true;
            try (FileReader reader = new FileReader(Game.username + ".txt");
                 BufferedReader br = new BufferedReader(reader)
            ) {
                String line;
                while ((line = br.readLine()) != null) {
                    char[] string = line.toCharArray();
                    temp[i] = Arrays.copyOf(string, string.length);
                    i++;
                }
                for (i = 0; i < 8; i++) {
                    String string = String.copyValueOf(temp[i]);
                    tempInt[i] = Integer.valueOf(string).intValue();
                }
                x = tempInt[0];
                y = tempInt[1];
                currentShapeType = tempInt[2];
                nextShapeType = tempInt[3];
                score = tempInt[4];
                timeCount = tempInt[5] * 600;
                isSpeedUp = tempInt[6] == 1;
                isBlindMode = tempInt[7] == 1;
                for (i = 0; i < 4; i++) {
                    for (j = 0; j < 4; j++) {
                        if (temp[i + 8][j] == ' ') shapeArray[i][j] = 0;
                        else shapeArray[i][j] = 1;
                    }
                }
                for (i = 0; i < Game.BOARD_ROWS; i++) {
                    for (j = 0; j < Game.BOARD_COLS; j++) {
                        if (temp[i + 12][j] == ' ') boardArray[i][j] = 0;
                        else boardArray[i][j] = 1;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
