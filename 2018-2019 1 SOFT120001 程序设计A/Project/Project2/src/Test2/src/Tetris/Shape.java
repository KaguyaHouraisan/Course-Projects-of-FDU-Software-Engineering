package Tetris;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;

/**
 * 方块的不同类型
 */
public class Shape implements Cloneable {
    int x, y, shapeType;
    int[][] shapeArray = new int[4][4];
    private Color shapeColor;

    /**
     * 每种方块的不同颜色
     */
    private static final Color[] shapeColors = new Color[] { Color.RED, Color.GREEN, Color.AZURE, Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.ORANGE };

    /**
     * 获得不同方块各自的颜色
     * @param shapeType 方块的种类
     * @return 方块对应的颜色
     */
    private static Color getColor(int shapeType) {
        return Shape.shapeColors[shapeType-1];
    }

    /**
     * 得到实例化后的shape对象的类型
     * @return shape对象的类型
     */
    private int getType() {
        return shapeType;
    }

    /**
     * 构造方法
     */
    Shape() {
        shapeType = new Random().nextInt(7) + 1;
        switch (shapeType) {
            case 1:
                shapeArray = new int[][]{ {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0} };
                break;
            case 2:
                shapeArray = new int[][]{ {1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0} };
                break;
            case 3:
                shapeArray = new int[][]{ {0, 1, 1, 0}, {1, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0} };
                break;
            case 4:
                shapeArray = new int[][]{ {0, 1, 0, 0}, {1, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0} };
                break;
            case 5:
                shapeArray = new int[][]{ {1, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0} };
                break;
            case 6:
                shapeArray = new int[][]{ {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 1, 0, 0}, {0, 0, 0, 0} };
                break;
            case 7:
                shapeArray = new int[][]{ {0, 1, 0, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 0, 0},};
                break;
        }
        shapeColor = shapeColors[shapeType-1];
        x = (Game.BOARD_COLS - getWidth()) / 2;
        y = - getHeight() + 1;
    }

    /**
     * 得到方块的x坐标
     * @return 方块的x坐标
     */
    int getX() {
        return x;
    }

    /**
     * 得到方块的y坐标
     * @return 方块的y坐标
     */
    int getY() {
        return y;
    }

    /**
     * 获取方块的四个点的相对坐标
     * @return 由四个点的相对坐标组成的二维数组
     */
    int[][] getArray() {
        return shapeArray;
    }

    /**
     * 将方块绘制到主界面上
     * @param gc 略
     */
    void draw(GraphicsContext gc) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if (shapeArray[i][j] != 0) {
                    Shape.drawBlock(gc, this.getType(),x + j, y + i);
                }
            }
        }
    }

    /**
     * 将下一个方块绘制到主界面上
     * @param gc 略
     */
    void drawPreview(GraphicsContext gc) {
        gc.setFill(Color.web("#373737"));
        gc.fillRect(0, 0, 200, 200);
        gc.setFill(shapeColor);
        for(int y=0; y<4; y++) {
            for(int x=0; x<4; x++) {
                if (shapeArray[y][x] != 0) {
                    int dx = (6 - getWidth()) * (Game.BLOCK_WIDTH + Game.BLOCK_MARGIN) / 2;
                    int dy = (4 - getHeight()) * (Game.BLOCK_WIDTH + Game.BLOCK_MARGIN) / 2;
                    gc.fillRect(
                        dx + x * (Game.BLOCK_WIDTH + Game.BLOCK_MARGIN) + Game.BLOCK_MARGIN,
                        dy + y * (Game.BLOCK_WIDTH + Game.BLOCK_MARGIN) + Game.BLOCK_MARGIN,
                        Game.BLOCK_WIDTH,
                        Game.BLOCK_WIDTH);
                }
            }
        }
    }

    /**
     * 将方块在主界面上的准确位置
     * @param gc 略
     * @param shapeType 方块的类型
     * @param x 方块的x坐标
     * @param y 方块的y坐标
     */
    static void drawBlock(GraphicsContext gc,int shapeType, int x, int y) {
        gc.setFill(Shape.getColor(shapeType));
        gc.fillRect(
            x * (Game.BLOCK_WIDTH + Game.BLOCK_MARGIN) + Game.BLOCK_MARGIN,
            y * (Game.BLOCK_WIDTH + Game.BLOCK_MARGIN) + Game.BLOCK_MARGIN,
            Game.BLOCK_WIDTH,
            Game.BLOCK_WIDTH);
    }

    /**
     * 对方块进行加速下移操作
     */
    void moveDown() {
        y++;
    }

    /**
     * 对方块进行左移操作
     */
    void moveLeft() {
        x--;
    }

    /**
     * 对方块进行右移操作
     */
    void moveRight() {
        x++;
    }

    /**
     * 对方块进行旋转操作
     */
    void rotate() {
        if (shapeType == 5) return;
        int[][] newShapeArray = new int[][]{ {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0} };
        for(int y = 0; y < getHeight(); y++)
            for(int x = 0; x < getWidth(); x++)
                newShapeArray[x][getHeight() - y - 1] = shapeArray[y][x];
        shapeArray = newShapeArray;
    }

    /**
     * 得到方块目前的宽度
     * @return int
     */
    int getWidth() {
        int width = 0, tmpWidth;
        for(int y = 0; y < 4; y++){
            tmpWidth = 0;
            for(int x = 3; x >= 0; x--){
                if (shapeArray[y][x] != 0) {
                    tmpWidth = x;
                    break;
                }
            }
            if (tmpWidth > width) {
                width = tmpWidth;
            }
        }
        return width + 1;
    }

    /**
     * 得到方块目前的高度
     * @return int
     */
    int getHeight() {
        int height = 0, tmpHeight;
        for(int x = 0; x < 4; x++){
            tmpHeight = 0;
            for(int y = 3; y >= 0; y--){
                if (shapeArray[y][x] != 0) {
                    tmpHeight = y;
                    break;
                }
            }
            if (tmpHeight > height) height = tmpHeight;
        }
        return height + 1;
    }

    /**
     * 复制一个shape对象
     * @return 复制的结果
     * @throws CloneNotSupportedException 略
     */
    public Shape clone() throws CloneNotSupportedException {
        return (Shape)super.clone();
    }
}
