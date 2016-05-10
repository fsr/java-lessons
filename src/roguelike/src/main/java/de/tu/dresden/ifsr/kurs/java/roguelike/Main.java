package de.tu.dresden.ifsr.kurs.java.roguelike;

import de.tu.dresden.ifsr.kurs.java.roguelike.model.ModelStarter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String TITLE = "The awesome rogue-like-game";
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    public static void main(String[] args) {
        ModelStarter.modelTest();
        //launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(TITLE);
        primaryStage.setResizable(false);

        final TextArea gameWindow = new TextArea();
        gameWindow.setWrapText(true);
        gameWindow.setDisable(true);
        gameWindow.setStyle("-fx-opacity: 1.0;" +
                "-fx-font-family: monospace" +
                "-fx-text-fill: white;" +
                "-fx-background-color: black;");

        int width = gameWindow.getPrefColumnCount();
        int height = gameWindow.getPrefRowCount();
        System.out.println(width);
        System.out.println(height);

        StringBuffer outputBuffer = new StringBuffer(width * height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0)
                    outputBuffer.append("-");
                else
                    outputBuffer.append("#");
            }
        }
        gameWindow.setText(outputBuffer.toString());

        StackPane root = new StackPane();
        root.getChildren().add(gameWindow);

        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }
}
