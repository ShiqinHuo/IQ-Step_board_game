package comp1110.ass2.gui;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

/**
 * Created by DoubleHUO on 3/10/17.
 * This class is based on
 * https://github.com/AlmasB/FXTutorials/tree/master/src/com/almasb/civ6menu
 */
// used ideas given by the YouTuber Almas Baimagambetov and the link:
// https://www.youtube.com/watch?v=N2EmtYGLh4U&index=1&list=PL4h6ypqTi3RQWPZfR6t73rxZK_TFkyURe
// The source code is from:
// https://github.com/AlmasB/FXTutorials/tree/master/src/com/almasb/civ6menu
public class Info extends Application implements Runnable {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private Pane root = new Pane();
    private final Text ackText = new Text("Thanks to open source platforms which we greatly benefit from.\n \nEspecially Almas Baimagambetov's videos in Youtube.\n \nThanks to the cooperation of 3 group mates.\n \nThanks to tutor Jo-lan Hu's help.");

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

    private void addAuthor(double x, double y) {
        authorBox.setTranslateX(x);
        authorBox.setTranslateY(y);
        authorData.forEach(data -> {
            MenuItem item = new MenuItem(data.getKey());
            //item.setOnAction(data.getValue());
            item.setTranslateX(-300);
            Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);
            authorBox.getChildren().addAll(item);
        });
        root.getChildren().add(authorBox);
    }
    private List<Pair<String,Runnable>> authorData = Arrays.asList(
            new Pair<String,Runnable>("Xiangyi Luo u6162693",()->{}),
            new Pair<String,Runnable>("Wenjun Yang u6251843",()->{}),
            new Pair<String,Runnable>("Shiqin Huo  u5949730",()->{})
    );//https://docs.oracle.com/javase/8/javafx/api/javafx/application/Platform.html

    private Line line;
    private Parent createContent() {
        addBackground();
        addTitle();
        addAck();
        double lineX = BOARD_WIDTH / 2 - 100;//WIDTH / 2 - 100;
        double lineY = BOARD_HEIGHT / 8 + 50;//HEIGHT / 3 + 50;
        addLine(lineX, lineY);
        addAuthor(lineX + 5, lineY);
        startAnimation();
        return root;
    }
    private void addLine(double x, double y) {
        line = new Line(x, y, x, y + 110);
        line.setStrokeWidth(3);
        line.setStroke(Color.color(1, 1, 1, 0.75));
        line.setEffect(new DropShadow(5, Color.BLACK));
        line.setScaleY(0);
        root.getChildren().add(line);
    }

    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(0.7), line);
        st.setToY(1);
        st.setOnFinished(e -> {

            for (int i = 0; i < authorBox.getChildren().size(); i++) {
                Node n = authorBox.getChildren().get(i);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }

    private void addAck(){
        ackText.setFill(Color.DEEPPINK);
        ackText.setFont(Font.loadFont(MenuApp.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 14));
        ackText.setLayoutX(250);
        ackText.setLayoutY(430);
        root.getChildren().add(ackText);
    }

    private VBox authorBox = new VBox(-5);

    private void addBackground(){
        //ImageView imageView = new ImageView(new Image(getClass().getResource("res/pinkblue.jpg").toExternalForm()));
        //ImageView imageView = new ImageView(new Image(getClass().getResource("res/Colourful.jpg").toExternalForm()));
        ImageView imageView = new ImageView(new Image(getClass().getResource("res/pinkpurple.jpg").toExternalForm()));
        imageView.setFitWidth(BOARD_WIDTH);
        imageView.setFitHeight(BOARD_HEIGHT);

        root.getChildren().add(imageView);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Information");
        Scene scene = new Scene(createContent());
        //addBackground();
        addTitle();
        addInstruction();
       // addAck();
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
    public static void main(String[] args) {
        launch(args);
    }

}
