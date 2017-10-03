package comp1110.ass2.gui;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

/**
 * Created by DoubleHUO on 8/9/17.
 */
public class MenuApp extends Application {
    private static final int WIDTH  = 933;
    private static final int HEIGHT = 700;

    private List<Pair<String,Runnable>> menuData = Arrays.asList(
            new Pair<String,Runnable>("Start",new Board()),
            new Pair<String,Runnable>("Difficulty",()->{}),
            new Pair<String,Runnable>("Reload",()->{}),
            new Pair<String,Runnable>("Information",()->{}),
            new Pair<String,Runnable>("Exit to Desktop", Platform::exit)
    );//https://docs.oracle.com/javase/8/javafx/api/javafx/application/Platform.html

    private Pane root = new Pane();
// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/Pane.html
    private VBox menuBox = new VBox(-5);
// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/VBox.html
    private Line line;
    private Parent createContent() {
        addBackground();
        addTitle();
        double lineX = WIDTH / 2 - 100;//WIDTH / 2 - 100;
        double lineY = HEIGHT / 3 + 120;//HEIGHT / 3 + 50;
        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY );
        startAnimation();
        return root;
    }

    private void addBackground(){
        ImageView imageView = new ImageView(new Image(getClass().getResource("res/iqsteps.png").toExternalForm()));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);
        root.getChildren().add(imageView);
    }

    private void addTitle() {
        Title title = new Title("IQ - STEPS");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 3);

        root.getChildren().add(title);
    }
    private void addLine(double x, double y) {
        line = new Line(x, y, x, y + 180);
        line.setStrokeWidth(3);
        line.setStroke(Color.color(1, 1, 1, 0.75));
        line.setEffect(new DropShadow(5, Color.BLACK));
        line.setScaleY(0);

        root.getChildren().add(line);
    }
    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
        st.setToY(1);
        st.setOnFinished(e -> {

            for (int i = 0; i < menuBox.getChildren().size(); i++) {
                Node n = menuBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.20), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }

    private void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
            MenuItem item = new MenuItem(data.getKey());
            item.setOnAction(data.getValue());
            item.setTranslateX(-300);
            Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });
        root.getChildren().add(menuBox);
    }

    @Override
    public  void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Welcome to IQ STEPS!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



