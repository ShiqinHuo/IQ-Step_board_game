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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * A very simple viewer for piece placements in the steps game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
// How to make visual pieces on the board was discussed with Peng Yong & Wenjun Yang(u6251843)

public class Viewer extends Application {

    /* board layout */
    private static final int VIEWER_WIDTH = 750;
    private static final int VIEWER_HEIGHT = 500;
    private static int refershed = 0;

    /* node groups */
    private final Group root = new Group();
    private final Group controls = new Group();
    private static final Group pegs = new Group();

    TextField textField;

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement  A valid placement string
     */

    void makePlacement(String placement) {
        // FIXME Task 4: implement the simple placement viewer
        int group = placement.length()/3;
        for (int i=0;i<group;i++){
            placePieces(placement.substring(3*i,3*i+3));
        }
    }

    public void placePieces (String piece){
        //System.out.println(piece);
        ImageView Piece = new ImageView();
        String char1 = String.valueOf(piece.charAt(0));
        String char2 = String.valueOf(piece.charAt(1));
        String location = String.valueOf(piece.charAt(2));
        int wid = 120;
        String imageName;
        String ImageChar2 = "ABCD";  // non-flipped
        // decode the board:
        final String[] Rotate = {"BF","CG","DH"}; //Rotate_90_180_270
        // Column 1-10 (index 0-9)
        final String[] Column = {"AKUfp","BLVgq","CMWhr","DNXis","EOYjt","FPaku","GQblv","HRcmw","ISdnx","JTeoy"};
        // Row 1-5 (index 0-4)
        final String[] Row = {"ABCDEFGHIJ","KLMNOPQRST","UVWXYabcde","fghijklmno","pqrstuvwxy"};
        // set the imageName (whether flipped)  imported from the folder
        if (ImageChar2.contains(char2))
            imageName = char1 + "A";
        else
            imageName = char1 + "E";
        Image name = new Image(Viewer.class.getResource("assets/"+imageName+".png").toString());
        Piece.setImage(name);
        Piece.setFitWidth(110);  //image-size
        Piece.setFitHeight(110);

        // rotate the pieces
        for (int i=0;i<3;i++){
            if(Rotate[i].contains(char2))
                Piece.setRotate(90*(i+1));
        }
        // locate the board
        Onepeg first_peg =new Onepeg( 1);

        //set the x-coordinate
        for (int i =0;i<10;i++){
            if(Column[i].contains(location))
                Piece.setX(first_peg.x+(i-1)*30-25);
        }
        //set the y-coordinate
        for (int i=0;i<5;i++){
            if(Row[i].contains(location))
                Piece.setY(first_peg.y+(i-1)*30-25);
        }

        root.getChildren().add(Piece);
    }


    /**
     * Construct a particular peg at a particular place
     */
    private static class Onepeg extends Circle {
        double x,y;
        //String peg;
        Onepeg(int num){
            setRadius(10);
            if(num<=5) {
                setCenterX(250 + (num-1)* 60);
                x = 250 + (num-1) * 60;
                setCenterY(200);
                y = 200;
            }
            else if(num<=10){
                setCenterX(280+(num-6)* 60);
                x = 280+(num-6)* 60;
                setCenterY(200+30);
                y = 200+30;
            }
            else if(num<=15) {
                setCenterX(250 + (num - 11) * 60);
                x =250 + (num - 11) * 60;
                setCenterY(200+30*2);
                y =200+30*2;
            }
            else if(num<=20) {
                setCenterX(280 + (num-16) * 60);
                x = 280+(num-16)*60;
                setCenterY(200+30*3);
                y =200+30*3;
            }
            else if(num<=25) {
                setCenterX(250 + (num-21)*60);
                x = 250 + (num-21) * 60;
                setCenterY(200+30*4);
                y =200+30*4;
            }
            setStroke(Color.gray(0.6));
            setFill(Color.gray(0.6));
        }
    }

    public static void makePegs() {
        pegs.getChildren().clear();
        for(int i = 1;i<=25;i++){
            pegs.getChildren().add(new Onepeg(i));
        }
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
                if(refershed==0){
                    makePlacement(textField.getText());
                    textField.clear();
                    refershed += 1;
                }
                else {
                    root.getChildren().clear();
                    root.getChildren().add(pegs);
                    root.getChildren().add(controls);
                    makePegs();
                    HBox hb = new HBox();
                    hb.getChildren().addAll(label1, textField, button);
                    hb.setSpacing(10);
                    hb.setLayoutX(130);
                    hb.setLayoutY(VIEWER_HEIGHT - 50);
                    controls.getChildren().add(hb);
                    makePlacement(textField.getText());
                    textField.setPrefWidth(300);
                    textField.clear();
                }
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
        root.getChildren().add(pegs);
        makePegs();
        makeControls();
        primaryStage.setScene(scene);
        primaryStage.show();
        }
    }