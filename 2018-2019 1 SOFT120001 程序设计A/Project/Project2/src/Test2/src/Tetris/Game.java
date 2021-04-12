package Tetris;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.*;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 游戏的主体逻辑
 */
public class Game extends TimerTask {
    static final int BLOCK_WIDTH = 40;
    static final int BLOCK_MARGIN = 4;
    static final int BOARD_ROWS = 20;
    static final int BOARD_COLS = 10;
    private static final int REPAINT_DELAY_MS = 300;
    static String username;
    static int timeCount = 0;
    static int score = 0;
    static boolean isSpeedUp;
    static boolean isSaveAndRead = false;
    static boolean isBlindMode = false;

    private static GraphicsContext boardCanvas = null;
    static GraphicsContext nextShapeCanvas = null;
    static GraphicsContext blindModeCanvas = null;
    private Label scoreLabel = null;
    static Label btnNewGame = null;
    static Label btnStopGame = null;
    static Label btnPauseGame = null;
    private Label timeLabel = null;
    static Label btnRankingList = null;
    static Label btnChangeSettings = null;
    static Label btnHelp = null;
    private boolean isPause = false;
    static boolean isGameRun = false;
    static boolean isGameOver = false;
    static int[][] boardArray = new int[BOARD_ROWS][BOARD_COLS];
    static Shape currentShape = null;
    static Shape nextShape = null;
    private Timer gameTimer = new Timer();
    private int repaintDelayCounter = 0;

    /**
     * 以下为分别建立起不同的按钮
     * @param gc 略
     */
    void setBoardGraphicsContext(GraphicsContext gc) {
        boardCanvas = gc;
    }

    void setNextShapeCanvasGraphicsContext(GraphicsContext gc) {
        nextShapeCanvas = gc;
    }

    void setBlindModeGraphicsContext(GraphicsContext gc) {
        blindModeCanvas = gc;
    }

    void setScoreLabel(Label control) {
        scoreLabel = control;
    }

    void setNewGameButton(Label control) {
        btnNewGame = control;
    }

    void setStopGameButton(Label control) {
        btnStopGame = control;
    }

    void setPauseGameButton(Label control) {
        btnPauseGame = control;
    }

    void setTimeLabel(Label control) {
        timeLabel = control;
    }

    void setRankingList(Label control) {
        btnRankingList = control;
    }

    void setHelp(Label control) {
        btnHelp = control;
    }

    void setChangeSettings(Label control) {
        btnChangeSettings = control;
    }

    /**
     * Init game.
     * 为不同的事件创建不同的处理方式
     * @throws Exception 略
     */
    void init() throws Exception {
        if (boardCanvas == null) throw new Exception("boardCanvas is null");
        if (nextShapeCanvas == null) throw new Exception("nextShapeCanvas is null");
        if (scoreLabel == null) throw new Exception("scoreLabel is null");
        if (btnNewGame == null) throw new Exception("btnNewGame is null");
        if (btnStopGame == null) throw new Exception("btnStopGame is null");
        if (btnPauseGame == null) throw new Exception("btnPauseGame is null");

        btnNewGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                startNewGame();
            }
        });
        btnNewGame.setStyle("visibility: visible;");
        btnHelp.setStyle("visibility: visible;");
        btnRankingList.setStyle("visibility: visible;");
        btnChangeSettings.setStyle("visibility: visible;");
        btnStopGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stopGame();
                ExitOrNot exit = new ExitOrNot();
                exit.start(new Stage());
            }
        });
        btnPauseGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pause();
            }
        });
        btnRankingList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getRankingList();
            }
        });
        btnChangeSettings.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getSettings();
            }
        });
        btnHelp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getHelp();
            }
        });

        drawBoardBackground();

        repaintDelayCounter = 0;
        gameTimer.schedule(this, 0, 1);
    }

    /**
     * 游戏结束
     */
    void destroy() {
        gameTimer.cancel();
    }

    /**
     * 游戏暂停
     */
    void pause() {
        Main.sound.pause();
        isPause = !isPause;
        Main.sound.setPauseMusic();
        btnPauseGame.setText(isPause ? "RESUME" : "PAUSE");
    }

    /**
     * 按钮：打开排行榜
     */
    private void getRankingList() {
        if (isGameRun) isPause = true;
        btnPauseGame.setText(isPause ? "RESUME" : "PAUSE");
        RankingList_Read rankingList = new RankingList_Read();
        rankingList.start(new Stage());
        Main.sound.play();
    }

    /**
     * 按钮：打开游戏设置
     */
    private void getSettings() {
        if (isGameRun) isPause = true;
        btnPauseGame.setText(isPause ? "RESUME" : "PAUSE");
        Settings settings = new Settings();
        settings.start(new Stage());
        Main.sound.play();
    }

    /**
     * 按钮：打开帮助
     */
    private void getHelp() {
        Help help = new Help();
        help.start(new Stage());
        Main.sound.play();
    }

    /**
     * 判断当前是否在暂停状态
     * @return true为在暂停状态，false为不在暂停状态
     */
    boolean isPaused() {
        return isPause;
    }

    /**
     * 对键盘事件进行反应
     * @param e 键盘事件
     * @throws CloneNotSupportedException 略
     */
    void handleKeyEvents(KeyEvent e) throws CloneNotSupportedException{
        if (currentShape == null) return;
        switch (e.getCode()){
            case UP:
                if (canRotate()) {
                    Main.sound.rotate();
                    currentShape.rotate();
                }
                break;
            case DOWN:
                if (canMoveDown()) {
                    Main.sound.grounded();
                    currentShape.moveDown();
                }
                break;
            case LEFT:
                if (canMoveLeft()) {
                    Main.sound.move();
                    currentShape.moveLeft();
                }
                break;
            case RIGHT:
                if (canMoveRight()) {
                    Main.sound.move();
                    currentShape.moveRight();
                }
                break;
        }
    }

    /**
     * 开始新游戏
     */
    private void startNewGame() {
        if (isSaveAndRead) {
            score = SaveAndRead_Read.score;
            scoreLabel.setText(String.valueOf(score));
            timeCount = SaveAndRead_Read.timeCount;
            timeLabel.setText(String.valueOf(timeCount / 600));
        }
        else {
            score = 0;
            timeCount = 0;
            scoreLabel.setText("0");
            timeLabel.setText("0");
        }
        currentShape = null;
        nextShape = null;
        isPause = false;
        isGameRun = true;
        isGameOver = false;

        Main.sound.play();
        Main.sound.setPlayMusic();

        btnNewGame.setStyle("visibility: hidden;");
        btnHelp.setStyle("visibility: hidden;");
        btnStopGame.setStyle("visibility: visible;");
        btnPauseGame.setStyle("visibility: visible;");
        btnPauseGame.setText("PAUSE");
        btnRankingList.setStyle("visibility: visible;");
        btnChangeSettings.setStyle("visibility: visible;");

        clearBoardArray();
        putNextShapeOnBoard();
        repaint();
    }

    /**
     * 结束游戏（主动）
     */
    private void stopGame() {
        isGameRun = false;
        isPause = false;
        Main.sound.play();
    }

    /**
     * 游戏最主要的循环更新由此方法实现
     */
    public void run() {
        Platform.runLater(new Runnable() {
            public void run() {
                if (isPause || !isGameRun) return;
                if (!isSpeedUp) {
                    if (++repaintDelayCounter >= REPAINT_DELAY_MS) {
                        repaintDelayCounter = 0;
                        updateGame();
                    }
                }
                else {
                    if (score <= 270) {
                        if (++repaintDelayCounter >= (REPAINT_DELAY_MS - score)) {
                            repaintDelayCounter = 0;
                            updateGame();
                        }
                    }
                    else {
                        if (++repaintDelayCounter >= REPAINT_DELAY_MS * 0.1) {
                            repaintDelayCounter = 0;
                            updateGame();
                        }
                    }
                }
                repaint();
                timeCount++;
                timeLabel.setText(String.valueOf(timeCount / 600));
            }
        });
    }

    /**
     * 更新游戏内容
     */
    private void updateGame() {
        if (canMoveDown()) currentShape.moveDown();
        else putNextShapeOnBoard();
    }

    /**
     * 重新绘制游戏界面
     */
    static void repaint() {
        drawBoardBackground();
        drawBoardBlocks();
        if (currentShape != null) currentShape.draw(boardCanvas);
        if (isGameOver) drawGameOverMessage();
        if (isBlindMode) openBlindMode();
    }

    /**
     * 检查是否可以下移
     * @return true为能够下移，false为不能下移
     */
    private boolean canMoveDown() {
        return canShapePlaced(currentShape, 0, 1);
    }

    /**
     * 检查是否可以左移
     * @return true为能够左移，false为不能左移
     */
    private boolean canMoveLeft() {
        return currentShape.getX() > 0 && canShapePlaced(currentShape, -1, 0);
    }

    /**
     * 检查是否可以右移
     * @return true为能够右移，false为不能右移
     */
    private boolean canMoveRight() {
        return (currentShape.getX() + currentShape.getWidth() < BOARD_COLS) && canShapePlaced(currentShape, 1, 0);
    }

    /**
     * 检查是否可以旋转
     * @return true为能够旋转，false为不能旋转
     * @throws CloneNotSupportedException 略
     */
    private boolean canRotate() throws CloneNotSupportedException {
        Shape rotatedShape;
        rotatedShape = currentShape.clone();
        rotatedShape.rotate();
        if (currentShape.getY() + rotatedShape.getHeight() >= BOARD_ROWS) return false;
        if (currentShape.getX() + rotatedShape.getWidth() > BOARD_COLS) return false;
        return canShapePlaced(rotatedShape, 0, 0);
    }

    /**
     * 判断方块能否处于预期的移动后的位置
     * @param shape 当前的方块
     * @param dx 在x轴上预期的移动距离
     * @param dy 在y轴上预期的移动距离
     * @return true为可以，false为不可以
     */
    private boolean canShapePlaced(Shape shape, int dx, int dy) {
        int[][] shapeArray = shape.getArray();
        for(int y = 0; y < shape.getHeight(); y++) {
            for(int x = 0; x < shape.getWidth(); x++) {
                if (shapeArray[y][x] == 0) continue;
                if (currentShape.getY() + y < 0) continue;
                if (currentShape.getY() + y + dy >= BOARD_ROWS) return false;
                if (boardArray[currentShape.getY() + y][currentShape.getX() + x] != 0) return false;
                if (dx != 0 && boardArray[currentShape.getY() + y][currentShape.getX() + x + dx] != 0) return false;
                if (dy != 0 && boardArray[currentShape.getY() + y + dy][currentShape.getX() + x] != 0) return false;
            }
        }
        return true;
    }

    /**
     * 判断游戏是否已经结束
     * @return true为游戏已结束，false为未结束
     */
    private boolean isGameOver() {
        for (int i = 0; i < BOARD_COLS; i++) {
            if (boardArray[0][i] != 0) return true;
        }
        return false;
    }

    /**
     * 把下一个方块转移到主界面上
     */
    private void putNextShapeOnBoard () {
        if (currentShape != null) {
            saveCurrentShapeOnBoard();
            clearLines();
            if (isGameOver()) {
                Main.sound.lose();
                stopGame();

                //展示或隐藏不同状态的按钮
                Game.btnNewGame.setStyle("visibility: visible;");
                Game.btnHelp.setStyle("visibility: visible;");
                Game.btnStopGame.setStyle("visibility: hidden;");
                Game.btnPauseGame.setStyle("visibility: hidden;");
                Game.btnRankingList.setStyle("visibility: visible;");
                Game.btnChangeSettings.setStyle("visibility: visible;");

                //清除正在下落的方块的显示
                if (!Game.isGameOver) {
                    Game.currentShape = null;
                    Game.repaint();
                }

                //清除下一个方块的显示
                Game.nextShapeCanvas.setFill(Color.web("#373737"));
                Game.nextShapeCanvas.fillRect(0, 0, 200, 200);
                isGameOver = true;
                return;
            }
        }
        if (isSaveAndRead) {
            isSpeedUp = SaveAndRead_Read.isSpeedUp;
            isBlindMode = SaveAndRead_Read.isBlindMode;
            currentShape = new Shape();
            nextShape = new Shape();
            currentShape.x = SaveAndRead_Read.x;
            currentShape.y = SaveAndRead_Read.y;
            currentShape.shapeType = SaveAndRead_Read.currentShapeType;
            nextShape.shapeType = SaveAndRead_Read.nextShapeType;
            currentShape.shapeArray = SaveAndRead_Read.shapeArray;
            boardArray = SaveAndRead_Read.boardArray;
            isSaveAndRead = false;
        }
        else {
            currentShape = (nextShape == null) ? new Shape() : nextShape;
            nextShape = new Shape();
        }
        nextShape.drawPreview(nextShapeCanvas);
    }

    /**
     * 保存当前在主界面上的图形
     */
    private void saveCurrentShapeOnBoard() {
        int[][] boardArrayCopy = copyBoardArray();
        int[][] shapeArray = currentShape.getArray();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (shapeArray[y][x] != 0 && currentShape.getY() + y >= 0 ) {
                    boardArrayCopy[currentShape.getY() + y][currentShape.getX() + x] = shapeArray[y][x];
                }
            }
        }
        boardArray = boardArrayCopy;
    }

    /**
     * 把下一个方块的相对坐标信息转移给存放当前方块信息的二维数组
     * @return 存放相对坐标信息的二维数组
     */
    private int[][] copyBoardArray() {
        int[][] newBoardArray = new int[BOARD_ROWS][BOARD_COLS];
        for(int row=0; row<BOARD_ROWS; row++) {
            for(int col=0; col<BOARD_COLS; col++) {
                newBoardArray[row][col] = boardArray[row][col];
            }
        }
        return newBoardArray;
    }

    /**
     * 消除可以消去的行
     */
    private void clearLines() {
        int row = BOARD_ROWS - 1;
        int count = 0, result = 1;
        while (row >= 0) {
            boolean isRowFull = true;
            for (int i = 0; i < BOARD_COLS; i++) {
                if (boardArray[row][i] == 0) {
                    isRowFull = false;
                    break;
                }
            }
            if (isRowFull) {
                for (int i = row; i >= 1; i--) {
                    boardArray[i] = boardArray[i - 1];
                }
                if (row > 0) {
                    for (int i = 0; i < BOARD_COLS; i++) {
                        boardArray[0][i] = 0;
                    }
                }
                count++;
                continue;
            }
            row--;
        }
        for (int i = 1;i < count;i ++) result *= 2;
        score = score + result * 10 * count;
        scoreLabel.setText(String.valueOf(score));
    }

    /**
     * 清除存放的上一个方块的相对坐标信息
     */
    private void clearBoardArray() {
        for(int y = 0; y < BOARD_ROWS; y++) {
            for(int x = 0; x < BOARD_COLS; x++) {
                boardArray[y][x] = 0;
            }
        }
    }

    /**
     * 绘制游戏主界面的背景
     */
    private static void drawBoardBackground() {
        boardCanvas.setFill(Color.web("#87CEFA"));
        boardCanvas.fillRect(
            0,
            0,
            BOARD_COLS * (BLOCK_WIDTH + BLOCK_MARGIN) + BLOCK_MARGIN,
            BOARD_ROWS * (BLOCK_WIDTH + BLOCK_MARGIN) + BLOCK_MARGIN);
        boardCanvas.setFill(Color.web("#000000"));
        for(int i = 0; i < BOARD_COLS; i++) {
            for(int j = 0; j < BOARD_ROWS; j++) {
                boardCanvas.fillRect(
                    i * (BLOCK_WIDTH + BLOCK_MARGIN) + BLOCK_MARGIN,
                    j * (BLOCK_WIDTH + BLOCK_MARGIN) + BLOCK_MARGIN,
                    BLOCK_WIDTH,
                    BLOCK_WIDTH);
            }
        }
    }

    /**
     * 将方块绘制到主界面上
     */
    private static void drawBoardBlocks() {
        for(int y = 0; y < BOARD_ROWS; y++){
            for(int x = 0; x < BOARD_COLS; x++){
                if (boardArray[y][x] != 0) {
                    Shape.drawBlock(boardCanvas, boardArray[y][x], x, y);
                }
            }
        }
    }

    /**
     * 提示游戏结束
     */
    private static void drawGameOverMessage() {
        boardCanvas.setFill(Color.rgb(10, 10, 10, 0.85));
        boardCanvas.fillRect(2, 2, 436, 880);
        boardCanvas.setFont(new Font(32));
        boardCanvas.setFill(Color.WHITE);
        boardCanvas.fillText("GAME OVER", 40, 460);
        RankingList_Save rankinglist = new RankingList_Save();
        rankinglist.start(new Stage());
    }

    /**
     * 开启盲打模式，用幕布覆盖界面
     */
    private static void openBlindMode() {
        blindModeCanvas.setFill(Color.rgb(10, 10, 10, 1));
        blindModeCanvas.fillRect(2, 2, 436, 880);
        if (isGameRun) blindModeCanvas.clearRect(
                (currentShape.x - 1) * (Game.BLOCK_WIDTH + Game.BLOCK_MARGIN),
                (currentShape.y - 1) * (Game.BLOCK_WIDTH + Game.BLOCK_MARGIN),
                (currentShape.getWidth() + 2) * (Game.BLOCK_WIDTH + Game.BLOCK_MARGIN) + Game.BLOCK_MARGIN,
                (currentShape.getHeight() + 2) * (Game.BLOCK_WIDTH + Game.BLOCK_MARGIN) + Game.BLOCK_MARGIN);
    }

    /**
     * 获取当前方块及下一个方块的信息以便存档
     * @return 当前方块和下一个方块
     */
    static Shape[] getTheShapes() {
        Shape[] temp = { currentShape, nextShape };
        return temp;
    }
}
