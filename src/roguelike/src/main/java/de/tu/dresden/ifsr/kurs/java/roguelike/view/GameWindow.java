package de.tu.dresden.ifsr.kurs.java.roguelike.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.concurrent.CountDownLatch;


public final class GameWindow extends Application {

    //! Configurations
    private static final String TITLE = "The awesome rogue-like-game";
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    //! Threading
    private static final CountDownLatch semaphor = new CountDownLatch(1);
    private volatile static boolean active;

    private static class InstanceHolder {
        public static GameWindow INSTANCE = null;
    }

    public GameWindow() {
        InstanceHolder.INSTANCE = this;
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

    public boolean isActive() {
        return active;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(TITLE);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest((WindowEvent event) -> active = false);

        final TextArea gameWindow = new TextArea();
        gameWindow.getStylesheets()
                .add(getClass().getResource("/gamestyle.css").toExternalForm());
        gameWindow.setWrapText(true);
        gameWindow.setEditable(false);

        gameWindow.setOnKeyPressed((KeyEvent key) -> gameWindow.setText(key.getText()));
        gameWindow.setOnKeyReleased((KeyEvent key) -> gameWindow.setText(""));

        StackPane root = new StackPane();
        root.getChildren().add(gameWindow);

        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }
}
