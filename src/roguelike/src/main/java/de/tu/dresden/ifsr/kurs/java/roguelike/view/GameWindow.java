package de.tu.dresden.ifsr.kurs.java.roguelike.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.concurrent.CountDownLatch;


///!!! Das ganze in eine innere klasse auslagern und nach innen absichern!!!

public final class GameWindow extends Application {

    //! Configurations
    private static final String TITLE = "The awesome rogue-like-game";
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    //! Threading and singleton
    private static final CountDownLatch latch = new CountDownLatch(1);
    private static GameWindow startUpTest = null;
    private static TextArea gameWindow;
    private volatile static boolean active;

    public static GameWindow getInsatnce() {
        if (!active) {
            new Thread() {
                @Override
                public void run() {
                    javafx.application.Application.launch(GameWindow.class);
                }
            }.start();

            try {
                latch.await();
                active = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return startUpTest;
    }

    private static void setStartUpTest(GameWindow startUpTest0) {
        startUpTest = startUpTest0;
        latch.countDown();
    }

    public GameWindow() throws InstantiationException {
        if (active) {
            throw new InstantiationException(
                    "A GameWindow is already existing.");
        }

        setStartUpTest(this);
    }

    public boolean isActive() {
        return active;
    }

    private void showText(String text) {
        gameWindow.setText(text);
    }

    private void clearText() {
        gameWindow.setText("");
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(TITLE);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest((WindowEvent event) -> active = false);

        gameWindow = new TextArea();
        gameWindow.getStylesheets()
                .add(getClass().getResource("/gamestyle.css").toExternalForm());
        gameWindow.setWrapText(true);
        gameWindow.setEditable(false);

        gameWindow.setOnKeyPressed((KeyEvent key) -> showText(key.getText()));
        gameWindow.setOnKeyReleased((KeyEvent key) -> clearText());

        StackPane root = new StackPane();
        root.getChildren().add(gameWindow);

        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }
}
