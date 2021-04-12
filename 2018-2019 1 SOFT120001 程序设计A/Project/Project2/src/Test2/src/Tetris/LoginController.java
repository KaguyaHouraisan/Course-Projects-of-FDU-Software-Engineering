package Tetris;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * 登录窗口的控制器
 */
public class LoginController implements Initializable {
    public LoginController() {
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    public TextField textField;

    /**
     * 在确保输入的用户名符合规范后进入模式选择窗口
     * @throws Exception 略
     */
    public void openTheGame() throws Exception {
        String str = textField.getText().replaceAll("[^0-9]", "");
        for (;str.equals("") && !textField.getText().equals("");) {
            ModeChoose choose = new ModeChoose();
            Game.username = textField.getText();
            choose.showWindow();
            textField.getScene().getWindow().hide();
            break;
        }
    }
}

