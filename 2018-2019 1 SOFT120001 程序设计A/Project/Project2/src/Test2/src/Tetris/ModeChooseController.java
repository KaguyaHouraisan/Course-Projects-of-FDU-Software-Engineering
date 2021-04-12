package Tetris;

import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 模式选择窗口的控制器
 */
public class ModeChooseController implements Initializable {
    public ModeChooseController() {
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    public Text welcome4;

    /**
     * 进入常规模式
     * @throws Exception 略
     */
    public void openCommon() throws Exception {
        Main main = new Main();
        main.showWindow();
        Game.isSpeedUp = false;
        welcome4.getScene().getWindow().hide();
    }

    /**
     * 进入增速模式
     * @throws Exception 略
     */
    public void openFast() throws Exception {
        Main main = new Main();
        main.showWindow();
        Game.isSpeedUp = true;
        welcome4.getScene().getWindow().hide();
    }

    /**
     * 读取上一个存档
     * @throws Exception 略
     */
    public void openRead() throws Exception {
        Main main = new Main();
        SaveAndRead_Read.readLastFile();
        main.showWindow();
        welcome4.getScene().getWindow().hide();
    }

    /**
     * 进入盲打模式
     * @throws Exception 略
     */
    public void openBlind() throws Exception {
        Main main = new Main();
        main.showWindow();
        Game.isSpeedUp = false;
        Game.isBlindMode = true;
        welcome4.getScene().getWindow().hide();
    }

    /**
     * 打开帮助
     */
    public void openHelp() {
        Help help = new Help();
        help.start(new Stage());
    }
}
