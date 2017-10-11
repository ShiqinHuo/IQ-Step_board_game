package comp1110.ass2.gui;

import comp1110.ass2.Alphabet;
import comp1110.ass2.StepsGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static comp1110.ass2.Alphabet.isPeg;

public class Board extends Application implements Runnable  {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final String URI_BASE = "assets/";

    TextField textField;
    /** message on completion */
    private final Text completionText = new Text("Well done!");
    private Text timeUsing = new Text("Shows timeUsing");
    private final Slider difficulty = new Slider();
    private final DropShadow dropShadow = new DropShadow();

    private static String newstart = "";
    private static String placement = "";

    HashMap<String,Double> hashCoordX = new HashMap();
    HashMap<String,Double> hashCoordY = new HashMap();
    HashMap<String,Double> placedRotated = new HashMap<>();

    private final Group root = new Group();
    private final Group controls = new Group();
    private static final Group pegs = new Group();
    private static final Group letters = new Group();
    private final Group pieces = new Group();
    //private final Group correctpieces = new Group();
    private final Group newpieces = new Group();
    private final Group boardpieces = new Group();
    ArrayList<Peg> peglist = new ArrayList<>();
//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/StackPane.html
/*
    Peg highlightedCircle = null;
    ArrayList<Double> diff_Zero = new ArrayList<>();
    ArrayList<Double> diff_One = new ArrayList<>();
    ArrayList<Double> diff_Two = new ArrayList<>();
    ArrayList<Double> diff_Three = new ArrayList<>();
    ArrayList<Double> diff_Four = new ArrayList<>();
*/
    private double startMilli = 0;
    private double endMilli = 0;
    private double useTime = 0;
    private BigDecimal UseTime;

    @Override
    public void run() {
        try {
            new Board().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /* initialize : preparation for the pegs arrangement & original pieces' arrangement */

    /** Create pegs helper class */
    private static class Peg extends Circle {
        double x,y;
        Alphabet letter;
        Peg (int id){
            setRadius(12);//https://stackoverflow.com/questions/1104975/a-for-loop-to-iterate-over-an-enum-in-java
            for (Alphabet a : Alphabet.values())
                if (isPeg(id) && (a.getId() == id)){
                    letter = a;
                    //System.out.println("...."+letter);
                    setCenterX(pegmapX.get(id%10)+80);
                    x = pegmapX.get(id%10) ;
                    setCenterY(pegmapY.get(id/10));
                    y = pegmapY.get(id/10);
                    setStroke(Color.gray(0.6));
                    setFill(Color.gray(0.6));
            }
        }
    }
//https://stackoverflow.com/questions/17437411/how-to-put-a-text-into-a-circle-object-to-display-it-from-circles-center
    /** Show pegs in the original state */
    private void makePegs() {
        //pane.getChildren().clear();
        pegs.getChildren().clear();
        for(int i = 0;i<=49;i++){
            if (isPeg(i)) {
                Peg onepeg = new Peg(i);
               // System.out.println("xxxxxxx"+ onepeg.x);
               // System.out.println("yyyyyy"+ onepeg.y);
                System.out.println("peg letter: "+onepeg.letter+" x-pos: "+onepeg.x+" y-pos: "+onepeg.y);
                peglist.add(onepeg);
                Label letter = new Label(onepeg.letter.toString());
                letter.setLayoutX(onepeg.x+73);
                letter.setLayoutY(onepeg.y-18);
                //https://www.dafont.com/top.php
                letter.setFont(Font.loadFont(MenuApp.class.getResource("res/Meatloaf.ttf").toExternalForm(), 30));
                //https://www.dafont.com/theme.php?cat=117
                // http://docs.oracle.com/javafx/2/text/jfxpub-text.htm
                letter.setTextFill(Color.WHITE);
                letter.setEffect(new DropShadow(30,Color.DEEPPINK));
              //  System.out.println("lllllletter"+letter);
                letters.getChildren().add(letter);
                letters.toFront();
                //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/StackPane.html
                //pegs.getChildren().add(onepeg);
                pegs.getChildren().add(onepeg);
            }
        }
    }
//https://stackoverflow.com/questions/41246688/circle-wont-move-position-in-javafx/41247282
//https://stackoverflow.com/questions/21118394/explicitly-positioning-nodes-in-javafx
    /** Assign the pieces' original coordinate */
    private static final Map<String,Integer> piecemap;
    static {
        piecemap = new HashMap<>();
        piecemap.put("AA",50);
        piecemap.put("AE",50);
        piecemap.put("BA",190);
        piecemap.put("BE",190);
        piecemap.put("CA",330);
        piecemap.put("CE",330);
        piecemap.put("DA",470);
        piecemap.put("DE",470);
        piecemap.put("EA",50);
        piecemap.put("EE",50);
        piecemap.put("FA",190);
        piecemap.put("FE",190);
        piecemap.put("GA",330);
        piecemap.put("GE",330);
        piecemap.put("HA",470);
        piecemap.put("HE",470);
    }

    /* Helpers function to show the images in assets folder */
    /** Traversal through the assets images and pick up the given piece to show */
    class Picture extends ImageView{
        String piece;
        Picture(String piece){
            for(String p : piecelist){
                if (p.equals(piece)){
                    setImage(new Image(Viewer.class.getResource(URI_BASE+piece+".png").toString()));
                    this.piece = piece;
                    setFitHeight(80);
                    setFitWidth(80);
                    break;}}}}

    class FXPiece extends Picture{
        double fixedX;
        double fixedY;
        double rotate;
        FXPiece(String piece){
            super(piece);
            //if (placedpieces.contains(piece)){
                fixedX = hashCoordX.get(piece);
                fixedY = hashCoordY.get(piece);
                rotate = placedRotated.get(piece);
                setLayoutX(fixedX);
                System.out.println("fffffffxxxxxx"+fixedX);
                setLayoutY(fixedY);
                System.out.println("fffffffyyyyy"+fixedY);
                setRotate(rotate);
                System.out.println("rotateeeeeeee"+rotate);
                setFitHeight(110);
                setFitWidth(110);
                setOpacity(1);
            //}
        }
    }

    /** Assign the coordinate for all pieces */
    class FlippableFXPiece extends Picture{
        double homeX;
        double homeY;
        FlippableFXPiece(String piece) {
            super(piece);//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html
            if (piecelist.contains(piece)){
            String char1 = String.valueOf(piece.charAt(0));
            homeY = piecemap.get(piece);
            if ("ABCD".contains(char1)){
                homeX = 80;
            }
            else homeX = 750;
            setLayoutX(homeX);
            setLayoutY(homeY);
            setRotate(0);
            setFitHeight(80);
            setFitWidth(80);}

        }//ttps://stackoverflow.com/questions/3290681/initializing-a-double-object-with-a-primitive-double-value

//https://stackoverflow.com/questions/5617175/arraylist-replace-element-if-exists-at-a-given-index
        void flippedPieces(){
            String char1 = String.valueOf(piece.charAt(0));
            String char2 = String.valueOf(piece.charAt(1));
            String newpiece;
            //System.out.println(char2 == "A"); cannot use == but .equals()
            if (char2.equals("A") ) newpiece = char1 + "E";
            else newpiece = char1 + "A";
            System.out.println("newpiece..."+newpiece);
            if (piecelist.contains(piece)){
            int index = piecelist.indexOf(piece);
            System.out.println("index"+index);
            //System.out.println(piecelist);
            piecelist.set(index,newpiece);// replace the piece with its flipped counterpart
            //System.out.println("changed????"+newpiece);
            //System.out.println("clear....");
            //System.out.println(piecelist.contains(piece));
            System.out.println("listtodoooooo"+piecelist);
            System.out.println("donedonedone"+placedpieces);
            makeUpdatedPieces();}
           // makeCorrectPieces();
           //System.out.println("corrrrrrrrrrrrrr"+correctpieces);
        }
    }

    private ArrayList<String> piecelist = new ArrayList<>();
    private void originalPieces(){
        if(piecelist.size()==0){
            piecelist.add("AA");
            piecelist.add("BA");
            piecelist.add("CA");
            piecelist.add("DA");
            piecelist.add("EA");
            piecelist.add("FA");
            piecelist.add("GA");
            piecelist.add("HA");}
        // initialise
    }
    private ArrayList<String> placedpieces = new ArrayList<>();

    //https://www.mkyong.com/java/how-to-loop-arraylist-in-java/
    /** This method is used to show those draggable pieces on the board. **/
    private void makeOriginalPieces() {
        originalPieces();
        pieces.getChildren().clear(); // updated piecelist states : including the flipped side
        newpieces.getChildren().clear();
        for (String piece : piecelist) {
            newpieces.getChildren().add(new DraggableFXPiece(piece));
        } // newpieces : original group
        newpieces.toFront();
    }

    /** This method is used to show those draggable pieces on the board. **/
    private void makeUpdatedPieces() {
        newpieces.getChildren().clear();// initial pieces
        boardpieces.getChildren().clear();
        pieces.getChildren().clear();

/*        for (String piece : placedpieces) {// boardpieces : arranged group
            boardpieces.getChildren().add(new DraggableFXPiece(piece)); // pieces on board
        }*/

        for (String piece : piecelist) { // disarranged group : pieces
            pieces.getChildren().add(new DraggableFXPiece(piece));
        }
        boardpieces.toFront();
    }

    // FIXME Task 7: Implement a basic playable Steps Game in JavaFX that only allows pieces to be placed in valid places

    class DraggableFXPiece extends FlippableFXPiece {
        double homeX, homeY;
        double mouseX, mouseY;

        private Board board = new Board();

        DraggableFXPiece(String piece) {
            super(piece);
            if (placedpieces.contains(piece))
            for (String p : placedpieces) {
                //System.out.println("placedpieces"+placedpieces);
                //System.out.println("pppppp"+p+"iiii"+piece);
                //System.out.println(p.equals(piece));
                //System.out.println("innnnnnnnnn");// bug : spacename
                if (p.equals(piece)) {
                  //System.out.println("nonghaode dongxi");
                    homeX = new FXPiece(p).fixedX;
                    homeY = new FXPiece(p).fixedY;
                    setLayoutX(homeX);
                    setLayoutY(homeY);
                    setRotate(new FXPiece(p).rotate);
                    break;}
            }
            if (piecelist.contains(piece))
            for(String p : piecelist) {
                if ( p.equals(piece)) {
                   // System.out.println("piecelist"+piecelist);
                    //System.out.println("to do");
                    homeX = new FlippableFXPiece(p).homeX;
                    setLayoutX(homeX);
                    homeY = new FlippableFXPiece(p).homeY;
                    setLayoutY(homeY);
                }
            }

            setOnScroll(event -> {if (!isOnBoard()) {
                hideCompletion();
                hideUsingTime();
                rotate();
                checkMove();
                event.consume();
            }});
//https://stackoverflow.com/questions/22542015/how-to-add-a-mouse-doubleclick-event-listener-to-the-cells-of-a-listview-in-java
            setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent click) {
                    if (homeX == getLayoutX() & homeY == getLayoutY()){
                        if (click.getClickCount() == 2) {
                        //updatePieces(piece);// flip
                            flippedPieces();
                            //makeCorrectPieces();//fixed
                            hideCompletion();
                            hideUsingTime();
                            checkMove();}}
                }
            });

            setOnMousePressed(event -> {
                hideCompletion();
                hideUsingTime();
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                    });

            setOnMouseDragged(event -> {
                hideCompletion();
                hideUsingTime();
                setFitHeight(110);
                setFitWidth(110);
                setOpacity(0.3);
                double movementX = event.getSceneX() - mouseX;
                double movementY = event.getSceneY() - mouseY;
                setLayoutX(getLayoutX() + movementX);
                setLayoutY(getLayoutY() + movementY);
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                event.consume();
                    });

            setOnMouseReleased(event -> {
                if (isOnBoard()) {
                   // System.out.println("onononXXXXX"+getLayoutX());
                    //System.out.println("ooooonnYYYYY"+getLayoutY());
                    setOpacity(1);
                    snapToGrid();
                    hideCompletion();
                    hideUsingTime();
                    checkMove();
                    event.consume();
                    updatedPieces();
                }
                else {
                    System.out.println("gggggXXX"+getLayoutX());
                    System.out.println("ggggYYY"+getLayoutY());
                    snapToHome();
                    setOpacity(1);}
            });
                }

//https://stackoverflow.com/questions/521171/a-java-collection-of-value-pairs-tuples

        /** remove pieces already on the board from the piecelist. **/
        public void updatedPieces() {
            if (piecelist.contains(piece)){
                int index = piecelist.indexOf(piece);
                piecelist.remove(index);
                if (! placedpieces.contains(piece)){
                    boardpieces.getChildren().add(new DraggableFXPiece(piece));
                    placedpieces.add(piece);
                  //  System.out.println("ppppppplaced"+placedpieces);
                  //  System.out.println("pairXXXXX"+getLayoutX());
                  //  System.out.println("pairYYYYY"+getLayoutY()); // on-board coordinates
                    hashCoordX.put(piece,getLayoutX());
                    hashCoordY.put(piece,getLayoutY());
                    placedRotated.put(piece,getRotate());
                }
            }
        }

        boolean isOnBoard(){
            if ( piece.equals("BA") && getRotate() == 0 || piece.equals("EA") && getRotate() == 180){
                if (getLayoutX()<270||getLayoutX()>520 || getLayoutY()<220 || getLayoutY()>290){
                    return false;
                }}
            else if ( piece.equals("BA") && getRotate() == 90 || piece.equals("EA") && getRotate() == 270){
                if (getLayoutX()<300 || getLayoutX()>520||getLayoutY()<190 || getLayoutY()>290){
                    return false;
                }}
            else if (piece.equals("BA") && getRotate() == 180 || piece.equals("EA") && getRotate() == 0){
                if (getLayoutX()<300 || getLayoutX()>550 || getLayoutY()<220 || getLayoutY()>290){
                    return false;
                }}
            else if (piece.equals("BA") && getRotate() == 270 || piece.equals("EA") && getRotate() == 90) {
                if (getLayoutX() < 300 || getLayoutX() > 520 || getLayoutY() < 220 || getLayoutY() > 320) {
                    return false;
                }}
            else if (getLayoutX()<300 || getLayoutX()>520 || getLayoutY()<220 || getLayoutY()>290){
                return false;
            }
            return true;
        }

        /** Check whether there are wrong connections between pieces.
        *   If so, show the skulls to mention the wrong piece placement. **/
        private void checkMove () {
            String placement = newstart;
            for (Node p : pieces.getChildren()) {
                placement += p.toString();}
            if (!StepsGame.isPlacementSequenceValid(placement)) {
                //showSkulls(StepsGame.getError(placement));
                //snapToHome();
            }
            else {
                if (StepsGame.isPlacementSequenceValid(placement) && placement.length() > 34) {
                    endMilli = System.currentTimeMillis();
                    useTime = endMilli - startMilli;
                    if (useTime != startMilli) {
                        if (difficulty.getValue() == 0) {
                       //     diff_Zero.add(useTime);
                            }
                        else if (difficulty.getValue() == 1) {
                         //   diff_One.add(useTime);
                            }
                        else if (difficulty.getValue() == 2) {
                        //    diff_Two.add(useTime);
                            }
                        else if (difficulty.getValue() == 3) {
                         //   diff_Three.add(useTime);
                            }
                        else if (difficulty.getValue() == 4) {
                         //   diff_Four.add(useTime);
                            }
                    }
                    if (useTime > 60000) {
                        UseTime = new BigDecimal(useTime / 60000);
                        useTime = UseTime.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        timeUsing = new Text("use time: " + useTime + " min");}
                    else {
                        UseTime = new BigDecimal(useTime / 1000);
                        useTime = UseTime.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        timeUsing = new Text("use time: " + useTime + " s");}
                    makeUsingTime();
                    //laugh.play();
                    showCompletion();
                    showUsingTime();
                    }
                }
            }

        /** Make the pieces snap to their original position. */
        private void snapToHome() {
            homeY = piecemap.get(piece);
            String char1 = String.valueOf(piece.charAt(0));
            if ("ABCD".contains(char1)){
                homeX = 80;}
            else homeX = 750;
            setLayoutX(homeX);
            setLayoutY(homeY);
/*            if (piece.equals("FA") || piece.equals("GA") || piece.equals("CE") || piece.equals("DA"))
                setRotate(45);
            if (piece.equals("FE") || piece.equals("GE") || piece.equals("CA") || piece.equals("DE"))
                setRotate(-45);
            if (piece.equals("EA"))
                setRotate(90);
            if (piece.equals("EE"))
                setRotate(-90);*/
            setRotate(0);
            setFitHeight(80);
            setFitWidth(80);
        }

        private void snapToGrid(){
            String ori = "";
            String pieceplacement = "";
            String char1 = String.valueOf(piece.charAt(0));
            String char2 = String.valueOf(piece.charAt(1));
            System.out.println("grid??");
            for (Peg a : peglist){
                //System.out.println("layout x: " +getLayoutX()+" xpos: "+a.x+" peg: "+a.letter.toString());
                //System.out.println("layout y: " +getLayoutY()+" ypos: "+a.y+" peg: "+a.letter.toString());
                if(getLayoutX()-25 <= a.x+20 && getLayoutX()-25 >= a.x-20 && getLayoutY()+ 55 <= a.y+20 && getLayoutY()+55  >= a.y-20){
                    setLayoutY(a.y - 55); // getLayoutY() - 20 <= pos <= getLayoutY() + 20
                    setLayoutX(a.x + 25);
                    setFitHeight(110);
                    setFitWidth(110);
                }else continue;
                if (char2 == "E") { // flipped
                    ori =  String.valueOf('E'+(getRotate()/90)); // E F G H
                }
                else if (char2 == "A") {// non-flipped
                    ori = String.valueOf('A'+(getRotate()/90));}
                pieceplacement = char1 + ori + a.letter.toString();
                if (placement.length()==0)
                    placement = newstart + pieceplacement;
                else
                    placement = placement + pieceplacement;
                break;
            }
            if (pieceplacement.equals(""))
                snapToHome();
            else if(StepsGame.isPlacementSequenceValid(placement)){
                snapToHome();
                placement = placement.substring(0,placement.length()-3);
            }
        }

        /** Make the piece rotate clockwise. Each time rotate 45 degrees. **/
        private void rotate() {
            setRotate((getRotate() + 90) % 360);}

    }

    // FIXME Task 8: Implement starting placements


/*
    class NewPiece extends FXPiece{
        NewPiece(String piece, String placement){
            super(piece);
            String

        }
    }
*/





    // FIXME Task 10: Implement hints
    /* Hints helper functions */

    /** This method is used to make the completion message, adjust its position, size and add it to root.
      * When this method is invoked, the program will record the end time automatically which is the time player complete the game. **/
    private void makeCompletion() {
        completionText.setFill(Color.NAVY);
        completionText.setFont(Font.font("Georgia", 80));
        completionText.setLayoutX(300);
        completionText.setLayoutY(250);
        completionText.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().add(completionText);
        endMilli = System.currentTimeMillis();
    }

    /** This method will show the completion message mentioned above by adjusting its opacity and making
    *it to the front. **/
    private void showCompletion() {
        //newPiece.setOpacity(0.3);
        pieces.setOpacity(0.3);
        completionText.toFront();
        completionText.setOpacity(1);
        completionText.setEffect(dropShadow);

    }

    /** This method will hide the completion message mentioned above by adjusting its opacity
    * and making it to the back. **/
    private void hideCompletion() {
        completionText.toBack();
        completionText.setOpacity(0);
        pieces.setOpacity(1);
        //newPiece.setOpacity(1);
    }

    /** This method is used to make the text of how many times you spend to complete the
    * whole game at the specific difficulty. **/
    private void makeUsingTime() {
        timeUsing.setFill(Color.DEEPPINK);
        timeUsing.setFont(Font.loadFont(MenuApp.class.getResource("res/handwriting-draft_free-version.ttf").toExternalForm(), 25));
        timeUsing.setLayoutX(320);
        timeUsing.setLayoutY(500);
        root.getChildren().add(timeUsing);
    }
    /** This method will show the text mentioned above. **/
    private void showUsingTime() {
        timeUsing.toFront();
        timeUsing.setOpacity(1);
    }

    /** This method will hide the text mentioned above. **/
    private void hideUsingTime() {
        timeUsing.toBack();
        timeUsing.setOpacity(0);
    }

    private void setUpHandlers(Scene scene) {
        scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.Q) {
                        Platform.exit();
                        event.consume();
                    }
                });}

    /** Coordinates helper functions */
    private static final Map<Integer,Integer> pegmapX;
    static {
        pegmapX = new HashMap<>();
        pegmapX.put(0,250); //
        pegmapX.put(1,280); // 2nd / 4th row ->  1st peg x-Coord
        pegmapX.put(2,310); //
        pegmapX.put(3,340);
        pegmapX.put(4,370);
        pegmapX.put(5,400);
        pegmapX.put(6,430);
        pegmapX.put(7,460);
        pegmapX.put(8,490);
        pegmapX.put(9,520);
    }
    private static final Map<Integer,Integer> pegmapY;
    static {
        pegmapY = new HashMap<>();
        pegmapY.put(0,250); //
        pegmapY.put(1,280); //                  y-Coord
        pegmapY.put(2,310); //
        pegmapY.put(3,340);
        pegmapY.put(4,370);
    }
    /** UI helper functions */
    private void addBackground(){
        //ImageView imageView = new ImageView(new Image(getClass().getResource("res/Colourful.jpg").toExternalForm()));
        //ImageView imageView = new ImageView(new Image(getClass().getResource("res/pinkblue.jpg").toExternalForm()));
        ImageView imageView = new ImageView(new Image(getClass().getResource("res/pinkpurple.jpg").toExternalForm()));
        imageView.setFitWidth(BOARD_WIDTH);
        imageView.setFitHeight(BOARD_HEIGHT);
        root.getChildren().add(imageView);
    }

    private void addTitle() {
        Title title = new Title("IQ - STEPS");
        title.setTranslateX(BOARD_WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(BOARD_HEIGHT / 5);

        root.getChildren().add(title);
    }

    // FIXME Task 11: Generate interesting starting placements

    /**
     * Set a label for difficulty, when the bar stays at "1", representing easy mode.
     * Similarly,"2" represents medium mode;"3" represents the hard mode.*/

    private void makeControls() {
        Button button = new Button("Start");
        button.setLayoutX(600);
        button.setLayoutY(595);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                startMilli = System.currentTimeMillis();
                //xia.play();
                //newPiece.getChildren().clear();
                //newPiece.setOpacity(1);
                pieces.setOpacity(1);
                //laugh.stop();
                hideCompletion();
                //hideSkulls();
                hideUsingTime();
                //makePlacement();
                //makeSolution(StepsGame.getSolutions());
                makeOriginalPieces();
                //makeUpdatedPieces();
            }
        });
        controls.getChildren().add(button);

        difficulty.setMin(0);
        difficulty.setMax(4);
        difficulty.setValue(0);
        difficulty.setPrefWidth(250);
        difficulty.setShowTickLabels(true);
        difficulty.setShowTickMarks(true);
        difficulty.setMajorTickUnit(5);
        difficulty.setMinorTickCount(4);
        difficulty.setSnapToTicks(true);

        difficulty.setLayoutX(300);
        difficulty.setLayoutY(600);
        controls.getChildren().add(difficulty);

        final Label difficultyCaption = new Label("Difficulty:");
        difficultyCaption.setTextFill(Color.GREY);
        difficultyCaption.setLayoutX(315);
        difficultyCaption.setLayoutY(580);
        controls.getChildren().add(difficultyCaption);
    }

    /**
     * According to the chosen difficulty, generate a new starting placement. */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("IQ-Steps Viewer");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        makePegs();
        //addBackground();
        addTitle();
        primaryStage.setScene(scene);
        root.getChildren().add(pegs);
        root.getChildren().add(letters);
        root.getChildren().add(controls);
        root.getChildren().add(newpieces);
        root.getChildren().add(pieces);
        root.getChildren().add(boardpieces);
        //root.getChildren().add(correct-pieces);
        //root.getChildren().add(pane);
        //root.getChildren().add(newPiece);
        setUpHandlers(scene);
        makeControls();
        makeUsingTime();
        primaryStage.setScene(scene);
        primaryStage.show();
        }}
