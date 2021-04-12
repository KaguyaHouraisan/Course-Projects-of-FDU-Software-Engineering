package Tetris;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * 用以绘制游戏设置窗口
 */
public class Settings extends Application {
    /**
     * 打开显示游戏设置的弹窗
     * @param primaryStage 略
     */
    @Override
    public void start(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        primaryStage.setTitle("Settings");
        Scene scene = new Scene(grid, 350, 350);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
        primaryStage.show();

        VBox controls = new VBox(250);

        VBox volume = new VBox(10);

        //整体音量调节
        Label mvol = new Label("Global Volume");
        Slider mvolumeSlider = new Slider();
        mvolumeSlider.setMax(100);
        mvolumeSlider.setValue(100);
        mvolumeSlider.setMaxWidth(250);
        mvolumeSlider.setMinWidth(250);
        mvolumeSlider.setOnMouseReleased(e-> {
            Main.sound.setGlobalVolume(mvolumeSlider.getValue());
        });
        VBox mlabeled = new VBox();
        mlabeled.setAlignment(Pos.CENTER);
        mlabeled.getChildren().addAll(mvol, mvolumeSlider);

        //音效调节
        Label vol = new Label("Sound Volume");
        Slider volumeSlider = new Slider();
        volumeSlider.setMax(100);
        volumeSlider.setValue(100);
        volumeSlider.setMaxWidth(250);
        volumeSlider.setMinWidth(250);
        volumeSlider.setOnMouseReleased(e-> {
            Main.sound.setVolume(volumeSlider.getValue());
        });
        VBox labeled = new VBox();
        labeled.setAlignment(Pos.CENTER);
        labeled.getChildren().addAll(vol, volumeSlider);

        //背景音乐调节
        Label svol = new Label("Music Volume");
        Slider svolumeSlider = new Slider();
        svolumeSlider.setMax(100);
        svolumeSlider.setValue(100);
        svolumeSlider.setMaxWidth(250);
        svolumeSlider.setMinWidth(250);
        svolumeSlider.setOnMouseReleased(e->{
            Main.sound.setMusicVol(svolumeSlider.getValue());
        });
        VBox slabeled = new VBox();
        slabeled.setAlignment(Pos.CENTER);
        slabeled.getChildren().addAll(svol, svolumeSlider);

        CheckBox mute = new CheckBox("Mute");
        CheckBox sounde = new CheckBox("Sounds");
        CheckBox musice = new CheckBox("Music");

        //依次为各按钮触发的事件
        musice.setOnAction(e -> {
            if (Main.soundWorks) {
                if (!musice.isSelected() && !sounde.isSelected()) {
                    mute.setSelected(true);
                } else {
                    mute.setSelected(false);
                }
                svolumeSlider.setDisable(!musice.isSelected());
                mvolumeSlider.setDisable(!sounde.isSelected()&&!musice.isSelected());
                Main.sound.setMusice(musice.isSelected());
            }
        });
        sounde.setOnAction(e -> {
            if (Main.soundWorks) {
                if (!musice.isSelected() && !sounde.isSelected()) {
                    mute.setSelected(true);
                } else {
                    mute.setSelected(false);
                }
                volumeSlider.setDisable(!sounde.isSelected());
                mvolumeSlider.setDisable(!sounde.isSelected()&&!musice.isSelected());
                Main.sound.setSounde(sounde.isSelected());
            }
        });
        mute.setOnAction(e -> {
            if (Main.soundWorks) {
                musice.setSelected(!mute.isSelected());
                volumeSlider.setDisable(mute.isSelected());
                Main.sound.setMusice(!mute.isSelected());
                sounde.setSelected(!mute.isSelected());
                svolumeSlider.setDisable(mute.isSelected());
                Main.sound.setSounde(!mute.isSelected());
                mvolumeSlider.setDisable(mute.isSelected());
            }
        });

        if (!Main.soundWorks) {
            volumeSlider.setDisable(true);
            svolumeSlider.setDisable(true);
            mvolumeSlider.setDisable(true);
            mute.setDisable(true);
            sounde.setDisable(true);
            musice.setDisable(true);
        }
        HBox checks = new HBox(10);
        checks.setAlignment(Pos.CENTER);
        checks.getChildren().addAll(mute, sounde, musice);

        VBox reStart = new VBox(50);
        reStart.setAlignment(Pos.CENTER);
        Button reOpen = new Button("Change Username");
        reStart.getChildren().addAll(reOpen);

        reOpen.setOnAction(event -> {
            Stage stage = new Stage();
            reStart(stage);
            primaryStage.hide();
        });

        VBox reMode = new VBox(50);
        reMode.setAlignment(Pos.CENTER);
        Button reOpenMode = new Button("   Change Mode   ");
        reMode.getChildren().addAll(reOpenMode);

        reOpenMode.setOnAction(event -> {
            Stage stage2 = new Stage();
            reMode(stage2);
            primaryStage.hide();
        });

        volume.getChildren().addAll(mlabeled,labeled, slabeled, checks, reStart, reMode);
        volume.setAlignment(Pos.CENTER);

        controls.getChildren().addAll(volume);
        controls.setAlignment(Pos.CENTER);

        grid.getChildren().addAll(controls);
    }

    /**
     * 打开切换用户名的窗口
     * @param stage 略
     */
    private void reStart(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        stage.setTitle("Re Start");
        Scene nextScene = new Scene(grid, 400, 200);
        stage.setScene(nextScene);
        nextScene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
        stage.show();

        Label userName = new Label("Username:");
        userName.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
        grid.add(userName, 0, 0);

        TextField textField = new TextField();
        grid.add(textField, 1, 0);

        Button btn = new Button("Start");
        btn.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        btn.setOnAction(event -> {
            String str = textField.getText().replaceAll("[^0-9]", "");
            for (;str.equals("") && !textField.getText().equals("");) {
                Game.username = textField.getText();
                stage.hide();
                break;
            }
        });
    }

    /**
     * 打开切换游戏模式的窗口
     * @param stage 略
     */
    private void reMode(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        stage.setTitle("Re Choose Mode");
        Scene nextScene = new Scene(grid, 400, 200);
        stage.setScene(nextScene);
        nextScene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
        stage.show();

        Button btn1 = new Button("              Normal Mode            ");
        Button btn2 = new Button("            Speed-up Mode           ");
        Button btn3 = new Button("                Blind Mode             ");
        btn1.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
        btn2.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
        btn3.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
        VBox Btn = new VBox(10);
        Btn.setAlignment(Pos.CENTER);
        Btn.getChildren().add(btn1);
        Btn.getChildren().add(btn2);
        Btn.getChildren().add(btn3);
        grid.add(Btn, 0, 0);

        btn1.setOnAction(event -> {
            Game.isSpeedUp = false;
            Game.isBlindMode = false;
            Game.blindModeCanvas.clearRect(2, 2, 436, 880);
            stage.hide();
        });

        btn2.setOnAction(event -> {
            Game.isSpeedUp = true;
            stage.hide();
        });

        btn3.setOnAction(event -> {
            Game.isBlindMode = true;
            stage.hide();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
