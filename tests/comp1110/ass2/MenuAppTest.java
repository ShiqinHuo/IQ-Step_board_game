package comp1110.ass2;

import static org.junit.Assert.*;
/**
 * Created by DoubleHUO on 25/9/17.
 */
import comp1110.ass2.gui.MenuApp;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.Test;
public class MenuAppTest {
//https://stackoverflow.com/questions/18429422/basic-junit-test-for-javafx-8
    @Test
    public void No_Interrupt() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel(); // Initializes the JavaFx Platform
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            new MenuApp().start(new Stage()); // Create and initialize
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(10); // Time to use the app, with out this, the thread
        // will be killed before you can tell.
        //change 10 to 10000 if you want to see to animation
    }
}