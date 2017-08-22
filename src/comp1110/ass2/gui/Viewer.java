package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A very simple viewer for piece placements in the steps game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */

public class Viewer extends Application {

    /* board layout */
    public static final int SQUARE_SIZE = 60;
    private static final int VIEWER_WIDTH = 750;
    private static final int VIEWER_HEIGHT = 500;

    /* color the 16 pieces*/
    private static final Paint AA = Color.PINK;
    private static final Paint AE = Color.PINK;
    private static final Paint BA = Color.YELLOWGREEN;
    private static final Paint BE = Color.YELLOWGREEN;
    private static final Paint CA = Color.RED;
    private static final Paint CE = Color.RED;
    private static final Paint DA = Color.GREEN;
    private static final Paint DE = Color.GREEN;
    private static final Paint EA = Color.ORANGE;
    private static final Paint EE = Color.ORANGE;
    private static final Paint FA = Color.PURPLE;
    private static final Paint FE = Color.PURPLE;
    private static final Paint GA = Color.YELLOW;
    private static final Paint GE = Color.YELLOW;
    private static final Paint HA = Color.BLUE;
    private static final Paint HE = Color.BLUE;

    /* node groups */
    private final Group root = new Group();
    private final Group pieces = new Group();
    private final Group solution = new Group();
    private final Group board = new Group();
    private final Group controls = new Group();

    TextField textField;



    /* message on completion */
    private final Text completionText = new Text("Well done!");

    /* the state of the 16 pieces */
    char[] piecestate = new char[16]; // all off screen to begin with

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement  A valid placement string
     */
    void makePlacement(String placement) {
        // FIXME Task 4: implement the simple placement viewer
    }

    /**
     * Construct a particular peg at a particular place
     */
    class Onepeg extends Circle {
        double wid;
        double x,y;
        String num;
        Onepeg(int num){
        }

    }
    /**
     * Make a list of Pegs which will displayed centered on the board, at a given
     * y-offset from the top of the board.
     *
     * This is used two ways: one to show the origin state of the game (a list of pegs(white/grey)),
     * and the other to show the current state of the game (a list of pegs).
     *
     * @param group The JavaFX group to which this list of pegs will belong.
     * @param list A string describing the list of pegs.
     * @param yoffset The y-offset from the top of the board.
     * @param opacity The opacity of this list of pegs
     */
    private void makePegList(Group group, String list, int yoffset, double opacity) {
    }

    /**
     * Set up each of the 8 pieces
     */
    private void makePieces() {
    }

    /**
     * Put all of the masks back in their home position
     */
    private void resetPieces() {
    }

    /**
     * Create a basic text field for input and a refresh button.
     */

    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField ();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
        }

    /**
     * The entry point for JavaFX.  This method gets called when JavaFX starts
     * The key setup is all done by this method.
     *
     * @param primaryStage The stage (window) in which the game occurs.
     * @throws Exception
     */

    @Override
    public  void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("StepsGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);
        root.getChildren().add(pieces);
        root.getChildren().add(board);
        root.getChildren().add(solution);
        root.getChildren().add(controls);
        Board.makeBoard("");
        makeControls();
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }