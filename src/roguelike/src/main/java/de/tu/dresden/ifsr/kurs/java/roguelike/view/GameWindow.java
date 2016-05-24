package de.tu.dresden.ifsr.kurs.java.roguelike.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.util.concurrent.CountDownLatch;


public final class GameWindow extends Application {

    //! Configurations
    public static final int DIM_X = 65;
    public static final int DIM_Y = 23;

    private static final String TITLE = "The awesome rogue-like-game";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 440;
    private static final float FONT_SIZE = 15.1f;
    private static final int MAX_CHAR_COUNT = DIM_X * DIM_Y;
    public static final int REFRESH_TIME_MS = 100;

    //! Threading
    private static final CountDownLatch semaphor = new CountDownLatch(1);
    private volatile static boolean active;
    private volatile static Stage stage;
    private volatile String output;

    private static class InstanceHolder {
        public static GameWindow INSTANCE = null;
    }

    public GameWindow() {
        InstanceHolder.INSTANCE = this;
        output = "";

        semaphor.countDown();
    }

    public static GameWindow getInstance() {
        try {
            if (InstanceHolder.INSTANCE == null) {
                new Thread() {
                    @Override
                    public void run() {
                        javafx.application.Application.launch(GameWindow.class);
                    }
                }.start();

                semaphor.await();
                active = true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return InstanceHolder.INSTANCE;
    }

    public boolean isActive() throws InterruptedException {
        Thread.sleep(REFRESH_TIME_MS);
        return active;
    }

    public void setText(String text) {
        output = text.substring(0, Math.min(text.length(), MAX_CHAR_COUNT));
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        primaryStage.setTitle(TITLE);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest((WindowEvent event) -> active = false);

        final TextArea gameWindow = new TextArea();
        gameWindow.getStylesheets()
                .add(getClass().getResource("/gamestyle.css").toExternalForm());
        gameWindow.setStyle("-fx-font-size: " + FONT_SIZE);
        gameWindow.setWrapText(true);
        gameWindow.setEditable(false);
        gameWindow.setText(output);

        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.millis(REFRESH_TIME_MS),
                (ActionEvent event) -> gameWindow.setText(output)));

        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();

        //gameWindow.setOnKeyPressed((KeyEvent key) -> gameWindow.setText(key.getText()));
        //gameWindow.setOnKeyReleased((KeyEvent key) -> gameWindow.setText(""));

        StackPane root = new StackPane();
        root.getChildren().add(gameWindow);

        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }
}
