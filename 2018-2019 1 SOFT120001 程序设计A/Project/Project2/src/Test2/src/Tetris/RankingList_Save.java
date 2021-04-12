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
import java.io.*;
import java.util.Arrays;

/**
 * 用以绘制窗口，完成排行榜的写入
 */
public class RankingList_Save extends Application {
    /**
     * 打开一个询问是否将游戏记录存入排行榜的的弹窗
     * @param primaryStage 略
     */
    @Override
    public void start(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        primaryStage.setTitle("Login");
        Scene scene = new Scene(grid, 400, 200);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
        primaryStage.show();

        grid.setPadding(new Insets(25));
        Text scenetitle1 = new Text("Do you want to put this ");
        grid.add(scenetitle1, 0, 0, 2, 1);
        scenetitle1.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 26));

        grid.setPadding(new Insets(25));
        Text scenetitle2 = new Text("score on the ranking list?");
        grid.add(scenetitle2, 0, 1, 2, 1);
        scenetitle2.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 26));

        Button btn1 = new Button("Yes");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn1.getChildren().add(btn1);
        grid.add(hbBtn1, 1, 5);

        btn1.setOnAction(event -> {
            save("Score", Game.score);
            save("Time", Game.timeCount / 600);
            primaryStage.hide();
        });

        Button btn2 = new Button("No");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 2, 5);

        btn2.setOnAction(event -> {
            primaryStage.hide();
        });
    }

    /**
     * 将数据写入排行榜文件
     * @param name 确定写入分数排行榜还是用时排行榜
     * @param need 即需要写入的分数或用时的数据
     */
    private static void save(String name, int need) {
        boolean flag = false;
        int i = 0,j;
        int[] rankingPoint = new int[11];
        char[][] rankingUsername = new char[11][50];
        File file = new File("RankingList_" + name + ".txt");
        if (file.exists()) {
            try (FileReader reader = new FileReader("RankingList_" + name + ".txt");
                 BufferedReader br = new BufferedReader(reader)
            ) {
                if ((br.readLine()) == null) flag = true;
                else {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String str1 = line.replaceAll("[^a-z^A-Z]", "");
                        String str2 = line.replaceAll("[^0-9]", "");
                        char[] string = str1.toCharArray();
                        rankingUsername[i] = Arrays.copyOf(string, string.length);
                        rankingPoint[i] = Integer.valueOf(str2).intValue();
                        i++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!flag) {
                for (i = 0; i < 10; i++) {
                    if (rankingPoint[i] >= need && rankingPoint[i + 1] <= need) break;
                }
                for (j = 10; j > i + 1; j--) {
                    rankingPoint[j] = rankingPoint[j - 1];
                    rankingUsername[j] = Arrays.copyOf(rankingUsername[j - 1], rankingUsername[j - 1].length);
                }
                rankingPoint[i + 1] = need;
                char[] string = Game.username.toCharArray();
                rankingUsername[i + 1] = Arrays.copyOf(string, string.length);
            }
            else {
                rankingPoint[0] = need;
                char[] string = Game.username.toCharArray();
                rankingUsername[i] = Arrays.copyOf(string, string.length);
            }

            try (FileWriter writer = new FileWriter(file,false);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                for (i = 0;i < 10;i ++) {
                    out.write("\r\n" + rankingPoint[i]);
                    out.write(rankingUsername[i]);
                }
                out.flush();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}