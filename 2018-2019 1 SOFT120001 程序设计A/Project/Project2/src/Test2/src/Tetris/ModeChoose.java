package Tetris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 用以绘制模式选择窗口
 */
public class ModeChoose extends Application {
    private Stage stage = new Stage();

    void showWindow() throws Exception {
        start(stage);
    }

    /**
     * 打开模式选择窗口
     * @param primaryStage 略
     * @throws Exception 略
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ModeChoose.fxml"));
        primaryStage.setTitle("ModeChoose");
        Scene scene = new Scene(root, 280, 420);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
