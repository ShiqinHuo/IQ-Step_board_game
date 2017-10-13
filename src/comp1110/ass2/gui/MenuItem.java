package comp1110.ass2.gui;

import javafx.beans.binding.Bindings;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * Created by DoubleHUO on 8/9/17.
 * This class is based on
 * https://github.com/AlmasB/FXTutorials/tree/master/src/com/almasb/civ6menu
 */
// used ideas given by the YouTuber Almas Baimagambetov and the link:
// https://www.youtube.com/watch?v=N2EmtYGLh4U&index=1&list=PL4h6ypqTi3RQWPZfR6t73rxZK_TFkyURe
// The source code is from:
// https://github.com/AlmasB/FXTutorials/tree/master/src/com/almasb/civ6menu

public class MenuItem extends Pane{
    private Text text;
//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/text/Text.html
    private Effect shadow = new DropShadow(5, Color.DEEPPINK);
    private Effect blur = new BoxBlur(1,1,3);

    public MenuItem(String name){
        Polygon bg = new Polygon(
//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Polygon.html
                0,0,
                200,0,
                215, 15,
                200,30,
                0,30
        );
        bg.setStroke(Color.color(1, 1, 1, 0.85));
        bg.setEffect(new GaussianBlur());
//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/effect/GaussianBlur.html
        bg.fillProperty().bind(
                Bindings.when(pressedProperty())
                        .then(Color.color(0, 0, 0, 0.85))
                        .otherwise(Color.color(0, 0, 0, 0.4))
//http://docs.oracle.com/javafx/2/events/KeyboardExample.java.htm
//pressedProperty
        );
        text = new Text(name);
        text.setTranslateX(5);
        text.setTranslateY(20);
        text.setFont(Font.loadFont(MenuApp.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 14));
        text.setFill(Color.WHITE);
        text.effectProperty().bind(
                Bindings.when(hoverProperty())
                        .then(blur)
                        .otherwise(shadow)
        );//https://stackoverflow.com/questions/31572889/conditional-binding
        getChildren().addAll(bg, text);
    }
    public void setOnAction (Runnable action){
        setOnMouseClicked(e->action.run());
    }//http://docs.oracle.com/javafx/2/events/convenience_methods.htm
}
