package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Created by DoubleHUO on 3/10/17.
 */

public class Info extends Application implements Runnable {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private final Group root = new Group();
    private void addTitle() {

        Title title = new Title("Author Information");
        title.setTranslateX(BOARD_WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(BOARD_HEIGHT / 8);

        root.getChildren().add(title);
    }
    private void addInstruction() {

        Title instruction = new Title("Acknowledgement");
        instruction.setTranslateX(BOARD_WIDTH / 2 - instruction.getTitleWidth() / 2);
        instruction.setTranslateY(4 * BOARD_HEIGHT / 8);

        root.getChildren().add(instruction);
    }
    void addAuthor(){

    }

    private void addBackground(){
        ImageView imageView = new ImageView(new Image(getClass().getResource("res/pinkblue.jpg").toExternalForm()));
        //ImageView imageView = new ImageView(new Image(getClass().getResource("res/pinkblue.jpg").toExternalForm()));
        //ImageView imageView = new ImageView(new Image(getClass().getResource("res/pinkpurple.jpg").toExternalForm()));
        imageView.setFitWidth(BOARD_WIDTH);
        imageView.setFitHeight(BOARD_HEIGHT);

        root.getChildren().add(imageView);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Information");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        addBackground();
        addTitle();
        addInstruction();
        primaryStage.setScene(scene);
        primaryStage.show();
}

    @Override
    public void run() {
        try {
            new Info().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
