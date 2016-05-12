package de.tu.dresden.ifsr.kurs.java.roguelike.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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

    //! Threading
    private static final CountDownLatch semaphor = new CountDownLatch(1);
    private volatile static boolean active;
    private volatile static Stage stage;

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

    public void setText() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < DIM_X; i++)
            for (int j = 0; j < DIM_Y; j++)
                output.append('#');

        //gameWindow.setText(output.substring(0, MAX_CHAR_COUNT));
        System.out.println(stage.isFocused());
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

        //gameWindow.setOnKeyPressed((KeyEvent key) -> gameWindow.setText(key.getText()));
        //gameWindow.setOnKeyReleased((KeyEvent key) -> gameWindow.setText(""));

        StackPane root = new StackPane();
        root.getChildren().add(gameWindow);

        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }
}
