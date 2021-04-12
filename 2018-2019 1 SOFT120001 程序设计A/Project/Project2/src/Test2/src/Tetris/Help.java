package Tetris;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * 用以绘制帮助窗口
 */
public class Help extends Application {
    private static int page = 0;

    /**
     * 打开帮助窗口
     * @param primaryStage 略
     */
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        primaryStage.setTitle("Help");
        Scene scene = new Scene(grid, 700, 950);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
        primaryStage.show();

        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER);
        Button btnPrevious = new Button("The Previous");
        Button btnNext = new Button("The Next");
        btnPrevious.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
        btnNext.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
        box.getChildren().addAll(btnPrevious, btnNext);

        Image[] image = {
                new Image("HelpResource/Help_1.png"),
                new Image("HelpResource/Help_2.png"),
                new Image("HelpResource/Help_3.png"),
                new Image("HelpResource/Help_4.png"),
                new Image("HelpResource/Help_5.png"),
                new Image("HelpResource/Help_6.png"),
                new Image("HelpResource/Help_7.png")
        };
        ImageView imageView = new ImageView();
        imageView.setImage(image[page]);

        grid.add(imageView, 0, 0);
        grid.add(box, 0, 1);

        btnPrevious.setOnAction(event -> {
            previousPage();
            imageView.setImage(image[page]);
        });

        btnNext.setOnAction(event -> {
            nextPage();
            imageView.setImage(image[page]);
        });
    }

    /**
     * 用以控制页数的page增加
     */
    private static void nextPage() {
        if (page < 6) page++;
    }

    /**
     * 用以控制页数的page减少
     */
    private static void previousPage() {
        if (page > 0) page--;
    }

    public static void main(String[] args) {
        launch(args);
    }
}