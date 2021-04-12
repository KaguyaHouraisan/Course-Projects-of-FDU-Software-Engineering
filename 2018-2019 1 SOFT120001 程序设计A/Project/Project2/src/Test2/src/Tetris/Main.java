package Tetris;

import sound.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.stage.WindowEvent;

/**
 * 用以绘制游戏主界面
 */
public class Main extends Application {
    private Game game = null;
    private Stage stage = new Stage();
    static SoundSystem sound;
    static boolean soundWorks = false;

    void showWindow() throws Exception {
        start(stage);
    }

    /**
     * 打开游戏主界面
     * @param stage 略
     * @throws Exception 略
     */
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage.setTitle("Tetris");
        Scene scene = new Scene(root, 712, 872);
        scene.getStylesheets().add(getClass().getResource("Main.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        //实例化一个声音控制器
        sound = new SoundSystem();
        soundWorks = sound.isWorking();

        //实例化一个按钮控制器
        game = new Game();
        game.setBoardGraphicsContext(((Canvas)scene.lookup("#gameCanvas")).getGraphicsContext2D());
        game.setNextShapeCanvasGraphicsContext(((Canvas)scene.lookup("#nextShapeCanvas")).getGraphicsContext2D());
        game.setBlindModeGraphicsContext(((Canvas)scene.lookup("#blindModeCanvas")).getGraphicsContext2D());
        game.setScoreLabel((Label)scene.lookup("#lblScore"));
        game.setNewGameButton((Label)scene.lookup("#btnNewGame"));
        game.setStopGameButton((Label)scene.lookup("#btnStopGame"));
        game.setPauseGameButton((Label)scene.lookup("#btnPauseGame"));
        game.setTimeLabel((Label)scene.lookup("#lblTime"));
        game.setRankingList((Label)scene.lookup("#btnRankingList"));
        game.setChangeSettings((Label)scene.lookup("#btnChangeSettings"));
        game.setHelp((Label)scene.lookup("#btnHelp"));
        game.init();

        //事件监听
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                if (game != null) game.destroy();
            }
        });

        //事件监听
        stage.iconifiedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (game != null && !game.isPaused()) game.pause();
            }
        });

        //事件监听
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent arg0) {
                if (game != null) {
                    try {
                        game.handleKeyEvents(arg0);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
