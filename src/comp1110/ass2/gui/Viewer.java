package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    private static final String URI_BASE = "assets/";

    /* node groups */
    private final Group root = new Group();
    private final Group pieces = new Group();
//    private final Group solution = new Group();
//    private final Group board = new Group();
    private final Group controls = new Group();
    private final Group newPiece = new Group();
    private final Group pegs = new Group();

    TextField textField;

    /* message on completion */
    private final Text completionText = new Text("Well done!");

    /* the state of the 16 pieces */
    char[] piecestate = new char[16]; // all off screen to begin with

    private static final String[] Pieceset = {"AA","AE","BA","BE","CA","CE","DA","DE","EA","EE","FA","FE","GA","GE","HA","HE"};

    private static final String[] Pieceset_up = {"AA","AE","BA","BE","CA","CE","DA","DE"};

    private static final String[] Pieceset_left = {"EA","EE","FA","FE"};

    private static final String[] Pieceset_right = {"GA","GE","HA","HE"};

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement  A valid placement string
     */

    void makePlacement(String placement) {
        // FIXME Task 4: implement the simple placement viewer
        for(int i=0;1<16;i++){
            newPiece.getChildren().add(new NewPiece(Pieceset[i],placement));
        }

    }

//    String toPiece(String p, String placement){
//        for (int i = 1; i < placement.length(); i += 3){
//            if (p == placement.charAt(i)) {
//                char[] val = {placement.charAt(i-1),placement.charAt(i),placement.charAt(i+1)};
//                String s =new String(val);
//                return s;
//            }
//        }
//        return "";
//    }

    class Picture extends ImageView{
        String piece;

        Picture(String piece){
            for(int i=0;1<16;i++){
                if (Pieceset[i] == piece){
                    setImage(new Image(Viewer.class.getResource(URI_BASE+piece+".png").toString()));
                    this.piece = piece;
                    setFitHeight(SQUARE_SIZE);
                    setFitWidth(SQUARE_SIZE);
                    break;}
            }
//            throw new IllegalArgumentException("Bad "+piece);
        }
    }

    class DraggableFXPiece extends Picture {
        int position;
        int homeX, homeY;
        /**
         * Construct a draggable piece
         *
         * @param piece The piece identifier ('A' - 'H')
         */
        DraggableFXPiece(String piece) {
            super(piece);
            position = -1;
            homeX = (SQUARE_SIZE * (((piece.charAt(0) - 'A') % 2) ));
            setLayoutX(homeX);
            homeY = (100 * (((piece.charAt(0) - 'A') / 2)));
            setLayoutY(homeY);
        }
    }

    private static final Map<String,Integer> piecemap;
    static {
        piecemap = new HashMap<String,Integer>();
        piecemap.put("AA",50);    //    x  y= 150
        piecemap.put("AE",160);
        piecemap.put("BA",240);
        piecemap.put("BE",360);
        piecemap.put("CA",460);
        piecemap.put("CE",550);
        piecemap.put("DA",640);  // x 160 y == 135
        piecemap.put("DE",760);  // x 160 y == 215
        piecemap.put("EA",135); ////   y   x= 100
        piecemap.put("EE",220);
        piecemap.put("FA",280);
        piecemap.put("FE",350);
        piecemap.put("GA",130); // y 330 x 230
        piecemap.put("GE",210); // y 330 x 430
        piecemap.put("HA",135);
        piecemap.put("HE",270);
    }

    class NewPiece extends Picture{
        double homeX;
        double homeY;
        NewPiece(String piece,String placement){
            super(piece);
            if (piece=="DE"||piece=="DA"){
                homeX = 160;
                if (piece=="DA") {homeY = 125;}
                else homeY= 225;
            }
            else if (piece=="GA" || piece == "GE") {
                homeY=330;
                if (piece=="GA") {homeX =230;}
                else homeX= 430;
            }
            else if (Arrays.asList(Pieceset_up).contains(piece)){
                homeX = piecemap.get(piece);
                homeY = 20;}
            else if  (Arrays.asList(Pieceset_left).contains(piece)){
                homeY = piecemap.get(piece);
                homeX = 60;
            }
            else if  (Arrays.asList(Pieceset_right).contains(piece)){
                homeX = 560;
                homeY = piecemap.get(piece);
            }
            setLayoutX(homeX-30);
            setLayoutY(homeY );
            if (piece=="FA" || piece=="GA"||piece=="CE"||piece=="DA")
                setRotate(45);
            if (piece=="FE" || piece== "GE"||piece=="CA"||piece=="DE")
                setRotate(-45);
            if (piece=="EA")
                setRotate(90);
            if (piece=="EE")
                setRotate(-90);
            setFitHeight(100);
            setFitWidth(100);
        }
    }
    /**
     * Construct a particular peg at a particular place
     */
    class Onepeg extends Circle {
        double wid;
        double x,y;
        String num;
        Onepeg(int num){
            wid = 16.5*2;
            setRadius(4.5*2);
            if(num<=5) {
                setCenterX(250 + (num-1)* wid*1.6);
                x = 250 + (num-1)*wid;
                setCenterY(200);
                y = 200;
            }
            else if(num<=10){
                setCenterX(250+wid*1.6/2+(num-6)*wid*1.6);
                x = 250+wid+(num-1)*2*wid;
                setCenterY(200+wid/2*(Math.sqrt(3)));
                y = 200+wid/2*(Math.sqrt(3));
            }
            else if(num<=15) {
                setCenterX(250 + (num - 11) * wid*1.6);
                x =250 + (num - 11) * wid;
                setCenterY(200+wid/2*(Math.sqrt(3))*2);
                y =200+wid/2*(Math.sqrt(3))*2;
            }
            else if(num<=20) {
                setCenterX(250 + wid*1.6/2 + (num-16)*wid*1.6);
                x = 250+wid+(num-16)*2*wid;
                setCenterY(200+wid/2*(Math.sqrt(3))*3);
                y =200+wid/2*(Math.sqrt(3))*3;
            }
            else if(num<=25) {
                setCenterX(250 + (num-21)*wid*1.6);
                x = 250 + (num-21) * wid;
                setCenterY(200+wid/2*(Math.sqrt(3))*4);
                y =200+wid/2*(Math.sqrt(3))*4;
            }
            setStroke(Color.gray(0.6));
            setFill(Color.gray(0.6));
        }
    }

    private void makePegs() {
        pegs.getChildren().clear();
        for(int i = 1;i<=25;i++){
            pegs.getChildren().add(new Onepeg(i));
        }
    }

    /**
     * Set up each of the 16 pieces
     */
    private void makePieces() {
        pieces.getChildren().clear();
        for(int i=0;1<=15;i++){
            pieces.getChildren().add(new DraggableFXPiece(Pieceset[i]));
        }
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
        root.getChildren().add(newPiece);
//        root.getChildren().add(board);
//        root.getChildren().add(solution);
//        root.getChildren().add(controls);
        root.getChildren().add(pegs);
//        makePieces();
        makePegs();
        makeControls();

        Board.makeBoard("");
        primaryStage.setScene(scene);
        primaryStage.show();
        }
    }