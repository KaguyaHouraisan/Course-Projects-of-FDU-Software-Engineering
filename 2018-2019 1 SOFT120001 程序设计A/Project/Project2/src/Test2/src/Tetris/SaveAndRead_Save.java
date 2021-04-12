package Tetris;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 用以完成存档的存放
 */
public class SaveAndRead_Save extends Application {
    /**
     * 打开一个询问是否将游戏存档的的弹窗
     * @param primaryStage 略
     */
    @Override
    public void start(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        primaryStage.setTitle("Save");
        Scene scene = new Scene(grid, 400, 200);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
        primaryStage.show();

        grid.setPadding(new Insets(25));
        Text scenetitle1 = new Text("Do you want to        ");
        grid.add(scenetitle1, 0, 0, 2, 1);
        scenetitle1.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 26));

        grid.setPadding(new Insets(25));
        Text scenetitle2 = new Text("              save this file?");
        grid.add(scenetitle2, 0, 1, 2, 1);
        scenetitle2.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 26));

        Button btn1 = new Button("Yes");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn1.getChildren().add(btn1);
        grid.add(hbBtn1, 1, 5);

        btn1.setOnAction(event -> {
            Shape[] temp = Game.getTheShapes();
            save(temp[0], temp[1]);

            //将当前正在下落的方块从主界面移除
            if (!Game.isGameOver) {
                Game.currentShape = null;
                if (Game.isBlindMode) {
                    Game.isBlindMode= false;
                    Game.repaint();
                    Game.isBlindMode= true;
                }
                else Game.repaint();
            }

            //将下一个方块从主界面移除
            Game.nextShapeCanvas.setFill(Color.web("#373737"));
            Game.nextShapeCanvas.fillRect(0, 0, 200, 200);

            primaryStage.hide();
        });

        Button btn2 = new Button("No");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 2, 5);

        btn2.setOnAction(event -> {
            RankingList_Save rankinglist = new RankingList_Save();
            rankinglist.start(new Stage());

            //将当前正在下落的方块从主界面移除
            if (!Game.isGameOver) {
                Game.currentShape = null;
                if (Game.isBlindMode) {
                    Game.isBlindMode= false;
                    Game.repaint();
                    Game.isBlindMode= true;
                }
                else Game.repaint();
            }
            //将下一个方块从主界面移除
            Game.nextShapeCanvas.setFill(Color.web("#373737"));
            Game.nextShapeCanvas.fillRect(0, 0, 200, 200);
            primaryStage.hide();
        });
    }

    /**
     * 将游戏存入相应存档
     * @param now 当前正在下落的方块
     * @param next 下一个方块
     */
    private static void save(Shape now, Shape next) {
        try {
            File writeName = new File(Game.username + ".txt");
            writeName.createNewFile();
            try (FileWriter writer = new FileWriter(writeName,false);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                int temp = Game.isSpeedUp ? 1 : 0;
                int temp2 = Game.isBlindMode ? 1 : 0;
                out.write(now.x + "\r\n" + now.y + "\r\n" + now.shapeType + "\r\n" + next.shapeType + "\r\n" + Game.score + "\r\n" + Game.timeCount / 600 + "\r\n" + temp + "\r\n" + temp2 + "\r\n");
                for (int i = 0; i < 4; i ++) {
                    for (int j = 0; j < 4; j ++) {
                        if (now.shapeArray[i][j] == 0) out.write(' ');
                        else out.write('*');
                    }
                    out.write("\r\n");
                }
                for (int i = 0; i < Game.BOARD_ROWS; i ++) {
                    for (int j = 0; j < Game.BOARD_COLS; j ++) {
                        if (Game.boardArray[i][j] == 0) out.write(' ');
                        else out.write('*');
                    }
                    out.write("\r\n");
                }
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}