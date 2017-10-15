package comp1110.ass2.gui;


import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
public class Title extends Pane{
    private Text text;
    public Title (String name){
        String spread = "";
        for (char c : name.toCharArray()){
            spread += c+ "";
        }
        text = new Text(spread);
        text.setFont(Font.loadFont(MenuApp.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 48));
// http://docs.oracle.com/javafx/2/text/jfxpub-text.htm
        text.setFill(Color.WHITE);
        text.setEffect(new DropShadow(30,Color.DEEPPINK));
        getChildren().addAll(text);
    }

    public double getTitleWidth(){
        return text.getLayoutBounds().getWidth();
    }

}
