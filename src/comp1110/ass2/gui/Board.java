package comp1110.ass2.gui;

import comp1110.ass2.Alphabet;
import comp1110.ass2.SolverForHint;
import comp1110.ass2.StepsGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static comp1110.ass2.Alphabet.isPeg;

public class Board extends Application implements Runnable  {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final String URI_BASE = "assets/";

    TextField textField;
    /** message on completion */
    private final Text completionText = new Text("Good Job!");
    private final Text insText = new Text(" Press Q =>to=> Quit game\n\n Press '/' =>to=> Show/Hide Game hints.\n\n Press S =>to=> Show best score \n\n Mouse Double click =>to=> flip piece\n\n Mouse scroll =>to=> rotate.\n\n Slide the \"Difficulty\" =>to=> change difficulty levels.\n\n Press \"Start\" =>to=> a new game. ");
    private Text timeUsing = new Text("Shows timeUsing");
    private final Slider difficulty = new Slider();
    private final DropShadow dropShadow = new DropShadow();

    private static String newstart = "";
    private static String placement = "";

    private final Group root = new Group();
    private final Group controls = new Group();
    private static final Group pegs = new Group();
    private static final Group letters = new Group();
    private final Group pieces = new Group();
    private final Group newpieces = new Group();
    private final Group boardpieces = new Group();
    private final Group solution = new Group();
    private ArrayList<Peg> peglist = new ArrayList<>();

    private ArrayList<Double> diff_0 = new ArrayList<>();
    private ArrayList<Double> diff_1 = new ArrayList<>();
    private ArrayList<Double> diff_2 = new ArrayList<>();
    private ArrayList<Double> diff_3 = new ArrayList<>();
    private ArrayList<Double> diff_4 = new ArrayList<>();


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
    }/* initialize : preparation for the pegs arrangement & original pieces' arrangement */

    /** Create pegs helper class */
    private static class Peg extends Circle {
        double x,y;
        Alphabet letter;
        Peg (int id){
            setRadius(12);//https://stackoverflow.com/questions/1104975/a-for-loop-to-iterate-over-an-enum-in-java
            for (Alphabet a : Alphabet.values())
                if (isPeg(id) && (a.getId() == id)){
                    letter = a;
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
                System.out.println("peg letter: "+onepeg.letter+" x-pos: "+onepeg.x+" y-pos: "+onepeg.y);
                peglist.add(onepeg);
                Label letter = new Label(onepeg.letter.toString());
                letter.setLayoutX(onepeg.x+73);
                letter.setLayoutY(onepeg.y-18);//https://www.dafont.com/top.php
                letter.setFont(Font.loadFont(MenuApp.class.getResource("res/Meatloaf.ttf").toExternalForm(), 30));
                //https://www.dafont.com/theme.php?cat=117
                // http://docs.oracle.com/javafx/2/text/jfxpub-text.htm
                letter.setTextFill(Color.WHITE);
                letter.setEffect(new DropShadow(30,Color.DEEPPINK));
                letters.getChildren().add(letter);
                letters.toFront();
                pegs.getChildren().add(onepeg);
            }
        }
    }
    private HashMap<String,Double> hashCoordX = new HashMap();
    private HashMap<String,Double> hashCoordY = new HashMap();
    /* Record the rotated */
    private HashMap<String,Double> mapRotated = new HashMap<>();

//https://stackoverflow.com/questions/41246688/circle-wont-move-position-in-javafx/41247282
//https://stackoverflow.com/questions/21118394/explicitly-positioning-nodes-in-javafx
/** Assign the pieces' original coordinate */
    private static final Map<String,Integer> piecemapY;
    static {
        piecemapY = new HashMap<>();
        piecemapY.put("AA",50);
        piecemapY.put("AE",50);
        piecemapY.put("BA",190);
        piecemapY.put("BE",190);
        piecemapY.put("CA",330);
        piecemapY.put("CE",330);
        piecemapY.put("DA",470);
        piecemapY.put("DE",470);
        piecemapY.put("EA",50);
        piecemapY.put("EE",50);
        piecemapY.put("FA",190);
        piecemapY.put("FE",190);
        piecemapY.put("GA",330);
        piecemapY.put("GE",330);
        piecemapY.put("HA",470);
        piecemapY.put("HE",470);
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
/**  */
    class FXPiece extends Picture{
        double fixedX;
        double fixedY;
        double rotate;
        FXPiece(String piece){
            super(piece);
            //if (placedpieces.contains(piece)){
                fixedX = hashCoordX.get(piece);
                fixedY = hashCoordY.get(piece);
                rotate = mapRotated.get(piece);
                setLayoutX(fixedX);
                System.out.println("fffffffxxxxxx"+fixedX);
                setLayoutY(fixedY);
                System.out.println("fffffffyyyyy"+fixedY);
                setRotate(rotate);
                System.out.println("rotateeeeeeee"+rotate);
                setFitHeight(110);
                setFitWidth(110);
                setOpacity(1);
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
            homeY = piecemapY.get(piece);
            if ("ABCD".contains(char1)){
                homeX = 80;
            }
            else homeX = 750;
            setLayoutX(homeX);
            setLayoutY(homeY);
            setRotate(0);
            setFitHeight(80);
            setFitWidth(80);}
        }
//https://stackoverflow.com/questions/3290681/initializing-a-double-object-with-a-primitive-double-value
//https://stackoverflow.com/questions/5617175/arraylist-replace-element-if-exists-at-a-given-index
        void flippedPieces(){
            String char1 = String.valueOf(piece.charAt(0));
            String char2 = String.valueOf(piece.charAt(1));
            String newpiece = "";
            if (char2.equals("A") ) newpiece = char1 + "E";
            else if (char2.equals("E")) newpiece = char1 + "A";
            this.piece = newpiece;
            System.out.println(piece + " ->updated-> " +newpiece );
            setImage(new Image(Viewer.class.getResource(URI_BASE+newpiece+".png").toString()));
            setFitHeight(80);
            setFitWidth(80);

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


    // FIXME Task 7: Implement a basic playable Steps Game in JavaFX that only allows pieces to be placed in valid places

    class DraggableFXPiece extends FlippableFXPiece {
        double homeX, homeY;
        double mouseX, mouseY;

        private Board board = new Board();

        DraggableFXPiece(String piece) {
            super(piece);
            if (piecelist.contains(piece))
            for(String p : piecelist) {
                if ( p.equals(piece)) {

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
                            flippedPieces();
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
                    //System.out.println("gggggXXX"+getLayoutX());
                    //System.out.println("ggggYYY"+getLayoutY());
                    snapToHome();
                    setOpacity(1);}
            });
                }

//https://stackoverflow.com/questions/521171/a-java-collection-of-value-pairs-tuples

        //** remove pieces already on the board from the piecelist. **//*
        private void updatedPieces() {
            if (piecelist.contains(piece)){
                int index = piecelist.indexOf(piece);
                piecelist.remove(index);
                if (! placedpieces.contains(piece)){
                    boardpieces.getChildren().add(new DraggableFXPiece(piece));
                    placedpieces.add(piece);
                    hashCoordX.put(piece,getLayoutX());
                    hashCoordY.put(piece,getLayoutY());
                    mapRotated.put(piece,getRotate());
                }
            }}


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
                //snapToHome(); -- Fixme
            }
            else {
                if (StepsGame.isPlacementSequenceValid(placement) && placement.length() > 34) {
                    endMilli = System.currentTimeMillis();
                    useTime = endMilli - startMilli;
                    if (useTime != startMilli) {
                        if (difficulty.getValue() == 0) {
                             diff_0.add(useTime);
                            }
                        else if (difficulty.getValue() == 1) {
                             diff_1.add(useTime);
                            }
                        else if (difficulty.getValue() == 2) {
                            diff_2.add(useTime);
                            }
                        else if (difficulty.getValue() == 3) {
                            diff_3.add(useTime);
                            }
                        else if (difficulty.getValue() == 4) {
                            diff_4.add(useTime);
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
            homeY = piecemapY.get(piece);
            String char1 = String.valueOf(piece.charAt(0));
            if ("ABCD".contains(char1)){
                homeX = 80;}
            else homeX = 750;
            setLayoutX(homeX);
            setLayoutY(homeY);
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
                    System.out.println();
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


    // FIXME Task 10: Implement hints

    String nextMask(String placement){
        Set<String> solution = SolverForHint.Solutions(placement);
        ArrayList<String> temp = new ArrayList<>(solution);
        int leng = placement.length();
        String result = temp.get(0);
        return result.substring(leng,leng+3);
    }

    boolean isValidCurrentStep(String pastPlacement,String appendMask){
        Set<String> solution = SolverForHint.Solutions (pastPlacement);
        ArrayList<String> temp = new ArrayList<>(solution);
        int leng = pastPlacement.length();
        String[] validAppendMask = new String[temp.size()];
        for (int i = 0; i < validAppendMask.length; i++){
            validAppendMask[i] = temp.get(i).substring(leng,leng+3);
        }
        for (int j = 0; j < validAppendMask.length; j++){
            return  (validAppendMask[j].equals(appendMask));
        }
        return false;
    }

    /* Hints helper functions */

    /** helps to set the effect of text, arranging the text's position, size.
      * When called, record current time automatically.**/
    private void compTextEffect() {
        completionText.setFill(Color.DEEPPINK);
        completionText.setFont(Font.loadFont(MenuApp.class.getResource("res/handwriting-draft_free-version.ttf").toExternalForm(), 20));
        completionText.setLayoutX(400);
        completionText.setLayoutY(70);
        //completionText.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().add(completionText);
        endMilli = System.currentTimeMillis();
    }

    /** This method helps to show the completion text -> set it front **/
    private void showCompletion() {
        //newPiece.setOpacity(0.3);
        pieces.setOpacity(0.3);
        completionText.toFront();
        completionText.setOpacity(1);
        completionText.setEffect(dropShadow);
    }

    /** helps to hide the completion message  **/
    private void hideCompletion() {
        completionText.toBack();
        completionText.setOpacity(0);
        pieces.setOpacity(1);
        //newPiece.setOpacity(1);
    }

    /** helps to set properties for the "timeUsing" text
    * the total time to complete the game at the corresponding difficulty. **/
    private void makeUsingTime() {
        timeUsing.setFill(Color.DEEPPINK);
        timeUsing.setFont(Font.loadFont(MenuApp.class.getResource("res/handwriting-draft_free-version.ttf").toExternalForm(), 15));
        timeUsing.setTextAlignment(TextAlignment.LEFT);
        timeUsing.setLayoutY(650);
        root.getChildren().add(timeUsing);
    }
    /** helps to show the "timeUsing" text. **/
    private void showUsingTime() {
        timeUsing.toFront();
        timeUsing.setOpacity(1);
    }

    /** helps to hide the "timeUsing" text. **/
    private void hideUsingTime() {
        timeUsing.toBack();
        timeUsing.setOpacity(0);
    }

    /**
     * This method helps to catch the keyboard code and activate the corresponding operation.
     * Specifically,
     * SLASH -> hints for the next step appears
     *     Q -> quit the game viewer and back to the menu
     *     S -> inner stage will appear with the best score in current difficulty
     *     I -> the instruction text appears
     * @param scene the current game scene
     */
    private void keyboardHandlers(Scene scene) {
        System.out.println("Handlers up");
        scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.Q) {
                        Platform.exit();
                        event.consume();
                    }
                    else if (event.getCode() == KeyCode.SLASH){
                        this.solution.setOpacity(0.8);
                        event.consume();
                    }
                    else if (event.getCode() == KeyCode.I){
                        showInsturnction();
                        event.consume();
                    } // show an inner stage with the best score in this difficulty
                    else if (event.getCode() == KeyCode.S){
                        if(BestScore(difficulty.getValue())>1000000){
                            double best = BestScore(difficulty.getValue());
                            BigDecimal Best = new BigDecimal(best/6000);
                            best = Best.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                            new innerStage().display("The best score for "+ difficulty.getValue(),+best+" min!");
                        }
                        else if (BestScore(difficulty.getValue()) == 0){
                            new innerStage().display("The best score for "+ difficulty.getValue(), "No record yet!");
                        }
                        else{
                            double best = BestScore(difficulty.getValue());
                            BigDecimal Best = new BigDecimal(best/1000);
                            best = Best.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                            new innerStage().display("The best score for "+ difficulty.getValue(), +best+ " s!");
                        }
                    }
                });
                scene.setOnKeyReleased(event -> {
                    if (event.getCode() == KeyCode.SLASH){
                        this.solution.setOpacity(0);
                        event.consume();}
                    else if (event.getCode() == KeyCode.I){
                        hideInstruction();
                    }
                });}

 /** helper functions to show the hints' text */
    private void InsTextEffect(){
        insText.setFill(Color.DEEPPINK);
        insText.setFont(Font.loadFont(MenuApp.class.getResource("res/handwriting-draft_free-version.ttf").toExternalForm(), 10));
        insText.setLayoutX(330);
        insText.setLayoutY(430);
        root.getChildren().add(insText);
    }

   private void showInsturnction (){
        insText.toFront();
        insText.setOpacity(1);
   }

   private void hideInstruction () {
       insText.toBack();
       insText.setOpacity(0);
   }
 /** helper class to show the best score in an inner stage */
//https://stackoverflow.com/questions/38062219/how-can-i-get-textfield-from-inner-stage-in-javafx
   private class innerStage{
       private void display(String title, String message){
           Stage inner = new Stage();
           inner.setTitle(title);
           inner.initModality(Modality.APPLICATION_MODAL);
           inner.setMinWidth(300);
           inner.setMinHeight(100);

           Label label = new Label(message);
           VBox layout = new VBox(10);
           layout.getChildren().addAll(label);
           layout.setAlignment(Pos.CENTER);

           Scene scene = new Scene(layout);
           inner.setScene(scene);
           inner.showAndWait();
       }
   }
  /* add "if-statement" to return the best corresponding to current difficulty */
   private double BestScore(double difficulty){
       double best = 0; // initialise
       if (difficulty == 0){
            best = getBest(diff_0);
       }
       else if (difficulty == 1){
           best = getBest(diff_1);
       }
       else if (difficulty == 2){
           best = getBest(diff_2);
       }
       else if (difficulty == 3){
           best = getBest(diff_3);
       }
       else if (difficulty == 4){
           best = getBest(diff_4);
       }
       return best;

   }
    /* find the best result in one certain difficulty */
   private double getBest (ArrayList<Double> list){
       double min = 1000000; //initialise
       if (list.size() == 0){
           min = 0;
       }
       else {
           for (Double i : list){ // traversal through the list to get the min
               if (i<min)  min = i;
           }
       }
       return min; // represents the best score
   }

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
        difficulty.setMax(3);
        difficulty.setValue(0);
        difficulty.setPrefWidth(250);
        difficulty.setShowTickLabels(true);
        difficulty.setShowTickMarks(true);
        difficulty.setMajorTickUnit(4);
        difficulty.setMinorTickCount(3);
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
        keyboardHandlers(scene);
        InsTextEffect();
        compTextEffect();
        makeControls();
        makeUsingTime();


        primaryStage.setScene(scene);
        primaryStage.show();
        }}
