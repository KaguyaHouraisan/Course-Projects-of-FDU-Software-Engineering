package Tetris;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 用以绘制窗口，完成排行榜的读取
 */
public class RankingList_Read extends Application {
    /**
     * 打开显示排行榜的图形化界面
     * @param primaryStage 略
     */
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        primaryStage.setTitle("RankingList");
        Scene scene = new Scene(grid, 350, 800);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
        primaryStage.show();

        String[] tempOfScore = rankingList_Read("Score");
        String[] tempOfTime = rankingList_Read("Time");

        Text[] temp = new Text[22];

        temp[0] = new Text("Ranking List of Score: ");
        grid.add(temp[0], 0, 0, 2, 1);
        temp[0].setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
        temp[11] = new Text("Ranking List of Time: ");
        grid.add(temp[11], 0, 13, 2, 1);
        temp[11].setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
        for (int i = 1; i < 11; i++) {
            grid.setPadding(new Insets(25));
            temp[i] = new Text(tempOfScore[i - 1]);
            grid.add(temp[i], 0, i, 2, 1);
            temp[i].setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
        }
        for (int i = 12; i < 22; i++) {
            grid.setPadding(new Insets(25));
            temp[i] = new Text(tempOfTime[i - 12]);
            grid.add(temp[i], 0, i + 2, 2, 1);
            temp[i].setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
        }
    }

    /**
     * 读取存放排行榜数据的文件
     * @param name 确定读取的是分数还是用时
     * @return 存放有排行榜数据的字符串数组
     */
    private static String[] rankingList_Read(String name) {
        String[] temp = new String[10];
        try {
            File file = new File("RankingList_" + name + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileReader reader = new FileReader("RankingList_" + name + ".txt");
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            if (! (br.readLine() == null)) {
                int i = 1;
                while ((line = br.readLine()) != null) {
                    String str1 = line.replaceAll("[^a-z^A-Z]", "");
                    String str2 = line.replaceAll("[^0-9]", "");
                    int k = Integer.valueOf(str2).intValue();
                    if (k != 0) {
                        temp[i - 1] = "NO." + i + ": " + str1 + "    " + name + ": " + str2;
                        i++;
                    }
                    else break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
