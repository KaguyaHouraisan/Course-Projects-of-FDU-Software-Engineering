package Tetris;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 用以绘制是否退出游戏的窗口
 */
public class ExitOrNot extends Application {
    /**
     * 生成图形化的是否退出窗口
     * @param primaryStage 略
     */
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        primaryStage.setTitle("Exit or not");
        Scene scene = new Scene(grid, 350, 200);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
        primaryStage.show();

        grid.setPadding(new Insets(25));
        Text scenetitle = new Text("Are you sure to exit?");
        grid.add(scenetitle, 0, 0, 2, 1);
        scenetitle.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 26));

        Button btn1 = new Button("Yes");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn1.getChildren().add(btn1);
        grid.add(hbBtn1, 1, 4);

        btn1.setOnAction(event -> {
            Main.sound.lose();

            //在选择是的时候主界面按钮的变化
            Game.btnNewGame.setStyle("visibility: visible;");
            Game.btnHelp.setStyle("visibility: visible;");
            Game.btnStopGame.setStyle("visibility: hidden;");
            Game.btnPauseGame.setStyle("visibility: hidden;");
            Game.btnRankingList.setStyle("visibility: visible;");
            Game.btnChangeSettings.setStyle("visibility: visible;");

            //弹出是否存档的窗口
            SaveAndRead_Save save = new SaveAndRead_Save();
            save.start(new Stage());
            primaryStage.hide();
        });

        Button btn2 = new Button("No");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 2, 4);

        btn2.setOnAction(event -> {
            Game.isGameRun = true;
            Main.sound.play();
            primaryStage.hide();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}