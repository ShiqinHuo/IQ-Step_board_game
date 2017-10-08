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
import java.util.Arrays;
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
    private static final String[] Pieceset = {"AA","AE","BA","BE","CA","CE","DA","DE","EA","EE","FA","FE","GA","GE","HA","HE"};
    private static final String[] Pieceset_left = {"AA","BA","CA","DA"};
    private static final String[] Pieceset_right = {"EA","FA","GA","HA"};

    private final Group newPiece = new Group();
    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group pieces = new Group();
    private static final Group pegs = new Group();
/*
    ArrayList<Peg> circleList = new ArrayList<>();
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
    /** Create pegs helper class */
    private static class Peg extends Circle {
        double x,y;
        Alphabet letter;
        Peg (int id){
            setRadius(15);//https://stackoverflow.com/questions/1104975/a-for-loop-to-iterate-over-an-enum-in-java
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
    /** Show pegs in the original state */
    private static void makePegs() {
        pegs.getChildren().clear();
        for(int i = 0;i<=49;i++){
            pegs.getChildren().add(new Peg(i));
        }
    }

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

    /** Assign the coordinate for all pieces */
    class FlippableFXPiece extends Picture{
        double homeX;
        double homeY;
        FlippableFXPiece(String piece) {
            super(piece);//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html
            String char1 = String.valueOf(piece.charAt(0));
            String char2 = String.valueOf(piece.charAt(1));
            homeY = piecemap.get(piece);
            if ("ABCD".contains(char1)){
                homeX = 80;
            }
            else homeX = 750;
            if (char2.equals("E")) setOpacity(0);
            setLayoutX(homeX);
            setLayoutY(homeY);
            if (piece.equals("FA") || piece.equals("GA") || piece.equals("CE") || piece.equals("DA"))
                setRotate(45);
            if (piece.equals("FE") || piece.equals("GE") || piece.equals("CA") || piece.equals("DE"))
                setRotate(-45);
            if (piece.equals("EA"))
                setRotate(90);
            if (piece.equals("EE"))
                setRotate(-90);
            setFitHeight(80);
            setFitWidth(80);
        }
//https://stackoverflow.com/questions/5617175/arraylist-replace-element-if-exists-at-a-given-index
        void flippedPieces(){
            String char1 = String.valueOf(piece.charAt(0));
            String char2 = String.valueOf(piece.charAt(1));
            String newpiece;
            //System.out.println(char2 == "A"); cannot use == but .equals()
            if (char2.equals("A") ) newpiece = char1 + "E";
            else newpiece = char1 + "A";
            System.out.println("newpiece..."+newpiece);
            int index = piecelist.indexOf(piece);
            System.out.println("index"+index);
            System.out.println(piecelist);
            piecelist.set(index,newpiece);// replace the piece with its flipped counterpart
            System.out.println("changed????"+newpiece);
            System.out.println("clear....");
            System.out.println(piecelist.contains(piece));
            System.out.println(piecelist);
            makeUpdatedPieces();
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

    //https://www.mkyong.com/java/how-to-loop-arraylist-in-java/
    /** This method is used to show those draggable pieces on the board. **/
    private void makeOriginalPieces() {
        originalPieces();
        pieces.getChildren().clear();
        for (String piece : piecelist) {
            pieces.getChildren().add(new DraggableFXPiece(piece));
        }
        pieces.toFront();
    }

    /** This method is used to show those draggable pieces on the board. **/
    private void makeUpdatedPieces() {
        pieces.getChildren().clear();
        for (String piece : piecelist) {
            pieces.getChildren().add(new DraggableFXPiece(piece));
        }
        pieces.toFront();
    }

    // FIXME Task 7: Implement a basic playable Steps Game in JavaFX that only allows pieces to be placed in valid places

    class DraggableFXPiece extends FlippableFXPiece {
        double homeX, homeY;
        double mouseX, mouseY;

        private Board board = new Board();

        DraggableFXPiece(String piece) {
            super(piece);
            for(String p : piecelist) {
                if ( p.equals(piece)) {
                    //if(i%2==0){
                    homeX = new FlippableFXPiece(p).homeX;
                    setLayoutX(homeX);
                    homeY = new FlippableFXPiece(p).homeY;
                    setLayoutY(homeY);break;//}
                }
            }
            setOnScroll(event -> {
                hideCompletion();
                hideUsingTime();
                rotate();
                checkMove();
                event.consume();
                    });
//https://stackoverflow.com/questions/22542015/how-to-add-a-mouse-doubleclick-event-listener-to-the-cells-of-a-listview-in-java
            setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent click) {
                    if (click.getClickCount() == 2) {
                        //updatePieces(piece);// flip
                        flippedPieces();
                        hideCompletion();
                        hideUsingTime();
                        checkMove();}
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
                //board.highlightNearestCircle(getLayoutX(), getLayoutY());
                event.consume();
                    });

            setOnMouseReleased(event -> {
                setOpacity(1);
               // snapToGrid();
                hideCompletion();
                hideUsingTime();
                checkMove();
                event.consume();
                    });
                }

        /** Check whether there are wrong connections between pieces.
        *   If so, show the skulls to mention the wrong piece placement. **/
        private void checkMove () {
            String placement = newstart;
            for (Node p : pieces.getChildren()) {
                placement += p.toString();}
            if (!StepsGame.isPlacementSequenceValid(placement)) {
                //showSkulls(StepsGame.getError(placement));
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
            if (Arrays.asList(Pieceset_left).contains(piece)){
                homeY = piecemap.get(piece);
                homeX = 50;
            }
            else if  (Arrays.asList(Pieceset_right).contains(piece)){
                homeX = 800;
                homeY = piecemap.get(piece);}
            setLayoutX(homeX);
            setLayoutY(homeY);
            if (piece.equals("FA") || piece.equals("GA") || piece.equals("CE") || piece.equals("DA"))
                setRotate(45);
            if (piece.equals("FE") || piece.equals("GE") || piece.equals("CA") || piece.equals("DE"))
                setRotate(-45);
            if (piece.equals("EA"))
                setRotate(90);
            if (piece.equals("EE"))
                setRotate(-90);
            setFitHeight(80);
            setFitWidth(80);
        }

        /** Make the piece rotate clockwise. Each time rotate 45 degrees. **/
        private void rotate() {
            setRotate((getRotate() + 45) % 360);
        }

    }

    // FIXME Task 8: Implement starting placements



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
        timeUsing.setFont(Font.loadFont(MenuApp.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 25));
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
        pegmapY.put(1,280); // 2nd / 4th row ->  1st peg x-Coord
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
        addBackground();
        addTitle();
        primaryStage.setScene(scene);
        root.getChildren().add(pieces);
        root.getChildren().add(controls);
        root.getChildren().add(pegs);
        //root.getChildren().add(newPiece);
        setUpHandlers(scene);
        makeControls();
        makeUsingTime();
        primaryStage.setScene(scene);
        primaryStage.show();
        }}
