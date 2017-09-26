package comp1110.ass2.gui;

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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public  class Board extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int SQUARE_SIZE = 60;
    private static final String URI_BASE = "assets/";

    TextField textField;
    /* message on completion */
    private final Text completionText = new Text("Well done!");
    private Text timeUsing = new Text("Shows timeUsing");
    private final Slider difficulty = new Slider();
    private final DropShadow dropShadow = new DropShadow();

    //set background music

    static String newstart = "";

    private static final String[] Pieceset = {"AA","AE","BA","BE","CA","CE","DA","DE","EA","EE","FA","FE","GA","GE","HA","HE"};

    private static final String[] Pieceset_up = {"AA","AE","BA","BE","CA","CE","DA","DE"};

    private static final String[] Pieceset_left = {"AA","BA","CA","DA","EA","FA","GA","HA"};

    private static final String[] Pieceset_right = {"AE","BE","CE","DE","EE","FE","GE","HE"};

    private final Group newPiece = new Group();

    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group pieces = new Group();
    private final Group solution = new Group();
   // private final Group placement = new Group();
    private static final Group pegs = new Group();
    ArrayList<Viewer.Onepeg> circleList = new ArrayList<>();
    Viewer.Onepeg highlightedCircle = null;
    ArrayList<Double> diff_Zero = new ArrayList<>();
    ArrayList<Double> diff_One = new ArrayList<>();
    ArrayList<Double> diff_Two = new ArrayList<>();
    ArrayList<Double> diff_Three = new ArrayList<>();
    ArrayList<Double> diff_Four = new ArrayList<>();

    private double startMilli = 0;
    private double endMilli = 0;
    private double useTime = 0;
    private BigDecimal UseTime;



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
        }
    }
    private void addBackground(){
        ImageView imageView = new ImageView(new Image(getClass().getResource("res/pinkblue.jpg").toExternalForm()));
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
    // FIXME Task 7: Implement a basic playable Steps Game in JavaFX that only allows pieces to be placed in valid places

    class DraggableFXpiece extends NewPiece {
        int position;
        double homeX, homeY;
        double mouseX, mouseY;
        int sign = 1;

        private Board board = new Board();

        DraggableFXpiece(String piece) {
            super(piece);
            this.board = board;
            position = -1;
            for(int i=0;1<16;i++) {
                if (Pieceset[i] == piece) {
                    homeX = new NewPiece(Pieceset[i]).homeX;
                    setLayoutX(homeX);
                    homeY = new NewPiece(Pieceset[i]).homeY;
                    setLayoutY(homeY);}break;}
            setOnScroll(event -> {
                        //hideSkulls();
                hideCompletion();
                hideUsingTime();
                rotate();
                        //bibi.play();
                checkMove();
                event.consume();
                    });

            setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2) {
                        if (sign == 1) {sign = -1;}
                        else { sign = 1;}
                        setScaleY(sign);
                        //xiu.play();
                        hideCompletion();
                        hideUsingTime();
                        //hideSkulls();
                        checkMove();}
                }
                    });

            setOnMousePressed(event -> {
                hideCompletion();
                hideUsingTime();
                //hideSkulls();
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                    });

            setOnMouseDragged(event -> {
                //hideSkulls();
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
                board.highlightNearestCircle(getLayoutX(), getLayoutY());
                event.consume();
                    });

            setOnMouseReleased(event -> {
                setOpacity(1);
                snapToGrid();
                hideCompletion();
                hideUsingTime();
                //hideSkulls();
                checkMove();
                event.consume();
                    });
                }

        /** Put the pieces onto the pegs.
          * If the piece is beyond the peg, it will snap to the original position. */
        private void snapToGrid () {
            double centerX = getLayoutX() + 150;
            double centerY = getLayoutY() + 150;
            if (isOnBoard(centerX, centerY) == true) {
                setLayoutX(findNearestCircle(getLayoutX(), getLayoutY()).getCenterX() - 150);
                setLayoutY(findNearestCircle(getLayoutX(), getLayoutY()).getCenterY() - 150);
                position = nearestCirclePosition(getLayoutX(), getLayoutY());
                }
            else {snapToHome();}
        }

        /** Whether the piece is beyond the pegs.
        * */  // --FixMe: Coordinates should be fixed
        private boolean isOnBoard ( double x, double y){
            if (y < 30 || y > 374 || x < 151.5 || x > 781.5) {
                return false;}
            else if ((y >= 30 && y <= 116) || (y >= 202 && y <= 288)) {
                if (x < 141.5 && x > 741.5) {return false;}
            }
            else if (y > 30 || y < 374) {
                if (x < 200.5 && x > 791.5) {return false;}
                else {return true;}}
                return true;
        }

        /** Check whether there are wrong connections between pieces.
        * If so, show the skulls to mention the wrong piece placement.
        * */
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

        /** Make the pieces snap to their original position.*/
        private void snapToHome() {
            if (Arrays.asList(Pieceset_left).contains(piece)){
                homeY = piecemap.get(piece);
                homeX = 50;
            }
            else if  (Arrays.asList(Pieceset_right).contains(piece)){
                homeX = 800;
                homeY = piecemap.get(piece);}
            setLayoutX(homeX);
            setLayoutY(homeY );
            if (piece=="FA" || piece=="GA"||piece=="CE"||piece=="DA")
                setRotate(45);
            if (piece=="FE" || piece== "GE"||piece=="CA"||piece=="DE")
                setRotate(-45);
            if (piece=="EA")
                setRotate(90);
            if (piece=="EE")
                setRotate(-90);
            setFitHeight(60);
            setFitWidth(60);
/*            for(int i=0;1<16;i++) {
                if (Pieceset[i] == piece) {
                    homeX = new NewPiece(Pieceset[i]).homeX;
                    setLayoutX(homeX);setLayoutX(homeX);
                    homeY = new NewPiece(Pieceset[i]).homeY;
                    setLayoutY(homeY);}break;}
            setLayoutX(homeX);
            setLayoutY(homeY );
            if (piece=="FA" || piece=="GA"||piece=="CE"||piece=="DA")
                setRotate(45);
            if (piece=="FE" || piece== "GE"||piece=="CA"||piece=="DE")
                setRotate(-45);
            if (piece=="EA")
                setRotate(90);
            if (piece=="EE")
                setRotate(-90);
            setFitHeight(60);
            setFitWidth(60);*/
        }

        /** Make the piece rotate clockwise. Each time rotate 45 degrees.
        * */
        private void rotate() {
            setRotate((getRotate() + 45) % 360);
        }

    }


    public static void makePegs() {
        pegs.getChildren().clear();
        for(int i = 1;i<=25;i++){
            pegs.getChildren().add(new Viewer.Onepeg(i));
        }
    }
    // FIXME Task 8: Implement starting placements

    /** This method is used to make the starting statement according to the difficulty level chosen,* */
    public void makePlacement() {
        newstart = "";//StepsGame.difficulty(difficulty.getValue());
        for (int i = 0; i < newstart.length() / 3; i++) {
            newPiece.getChildren().add(new FXPiece(newstart.substring(3*i,3*i+3)));
        }
    }


    private static final Map<String,Integer> piecemap;
    static {
        piecemap = new HashMap<String,Integer>();
        piecemap.put("AA",50);
        piecemap.put("AE",50);
        piecemap.put("BA",120);
        piecemap.put("BE",120);
        piecemap.put("CA",190);
        piecemap.put("CE",190);
        piecemap.put("DA",260);
        piecemap.put("DE",260);
        piecemap.put("EA",330);
        piecemap.put("EE",330);
        piecemap.put("FA",400);
        piecemap.put("FE",400);
        piecemap.put("GA",470);
        piecemap.put("GE",470);
        piecemap.put("HA",540);
        piecemap.put("HE",540);
    }



    class NewPiece extends Picture{
        double homeX;
        double homeY;
        NewPiece(String piece){
            super(piece);
            if (Arrays.asList(Pieceset_left).contains(piece)){
                homeY = piecemap.get(piece);
                homeX = 50;
            }
            else if  (Arrays.asList(Pieceset_right).contains(piece)){
                homeX = 800;
                homeY = piecemap.get(piece);}
            setLayoutX(homeX);
            setLayoutY(homeY );
            if (piece=="FA" || piece=="GA"||piece=="CE"||piece=="DA")
                setRotate(45);
            if (piece=="FE" || piece== "GE"||piece=="CA"||piece=="DE")
                setRotate(-45);
            if (piece=="EA")
                setRotate(90);
            if (piece=="EE")
                setRotate(-90);
            setFitHeight(60);
            setFitWidth(60);
        }
    }


    /** This method is used to find a cirlce which is nearest to
     * the object which invokes this method
     * */
    private Viewer.Onepeg findNearestCircle(double x, double y) {
        double min = 5000.0d;
        Viewer.Onepeg minC = null;
        for (int i = 0; i < circleList.size(); i++) {
            if (min > circleList.get(i).distance(x, y)) {
                min = circleList.get(i).distance(x, y);
                minC = circleList.get(i);
            }
        }
        return minC;
    }

    /** This method is used to return the position of the nearest circle which ia already found.
    * The interval is [0,23].
    * */
    private int nearestCirclePosition(double x, double y) {
        double min = 5000.0d;
        Viewer.Onepeg minC = null;
        int position = 0;
        for (int i = 0; i < circleList.size(); i++) {
            if (min > circleList.get(i).distance(x, y)) {
                min = circleList.get(i).distance(x, y);
                minC = circleList.get(i);
                position = i;
            }
        }
        return position;
    }
    /*
    * This method is used to highlight the nearest circle founded.
    * */
    private void highlightNearestCircle(double x, double y) {
        highlightedCircle = findNearestCircle(x, y);
    }


    /**
     * An inner class that represents pieces used in the game
     * Each of these is a visual representaton of an underlying piece
     */

    class FXPiece extends ImageView {
        String piece;

        /**
         * Construct chess
         *
         * @param piece A character representing the piece to be created
         */

        FXPiece(String piece) {
            if (!(Arrays.asList(Pieceset).contains(piece))){
                throw new IllegalArgumentException("Bad piece: \"" + piece + " \"");}

            setImage(new Image(Board.class.getResource(URI_BASE + piece + ".png").toString()));
            this.piece = piece;
            setFitWidth(Viewer.SQUARE_SIZE);
            setFitHeight(Viewer.SQUARE_SIZE);
        }
    }

    /*
* This method is used to show those draggable pieces on the board.
* */
    private void makePieces() {
        pieces.getChildren().clear();
        for (int i=0;i<16;i++) {
            pieces.getChildren().add(new DraggableFXpiece(Pieceset[i]));
        }
        pieces.toFront();
    }

    /*
    * When this method is called, the start placement will be made.
    * Also, the relative solution will be made, but their opacity will be 0 because it
    * is really silly to show the player answer at the beginning of the game. LOL!
    * */
    private void makeSolution(String solution) {
        this.solution.getChildren().clear();
        for (int i = 0; i < solution.length() / 3; i++) {
            this.solution.getChildren().add(new FXPiece(solution.substring(i * 3, (i + 1) * 3)));
        }
        this.solution.setOpacity(0);

    }



    // FIXME Task 10: Implement hints

    /** This method is used to make the completion message, adjust its position, size and add it to root.
      * When this method is invoked, the program will record the end time automatically which is the time player complete the game.
      * */
    private void makeCompletion() {
        completionText.setFill(Color.NAVY);
        completionText.setFont(Font.font("Georgia", 80));
        completionText.setLayoutX(300);
        completionText.setLayoutY(250);
        completionText.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().add(completionText);
        endMilli = System.currentTimeMillis();
    }

    /*
    * This method will show the completion message mentioned above by adjusting its opacity and making
    *it to the front.
    * */
    private void showCompletion() {
        newPiece.setOpacity(0.3);
        pieces.setOpacity(0.3);
        completionText.toFront();
        completionText.setOpacity(1);
        completionText.setEffect(dropShadow);

    }

    /** This method will hide the completion message mentioned above by adjusting its opacity
    * and making it to the back.
    * */
    private void hideCompletion() {
        completionText.toBack();
        completionText.setOpacity(0);
        pieces.setOpacity(1);
        newPiece.setOpacity(1);
    }
    /** This method is used to make the text of how many times you spend to complete the
    * whole game at the specific difficulty.
    * */
    private void makeUsingTime() {
        timeUsing.setFill(Color.DEEPPINK);
        timeUsing.setFont(Font.loadFont(MenuApp.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 25));
        timeUsing.setLayoutX(320);
        timeUsing.setLayoutY(500);
        root.getChildren().add(timeUsing);
    }
    /** This method will show the text mentioned above.* */
    private void showUsingTime() {
        timeUsing.toFront();
        timeUsing.setOpacity(1);
    }

    /*
    * This method will hide the text mentioned above.
    * */
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


    // FIXME Task 11: Generate interesting starting placements

    /**
     * Set a label for difficulty, when the bar stays at "1", representing easy mode.
     * Similarly,"2" represents medium mode;"3" represents the hard mode.
     */



    private void makeControls() {
        Button button = new Button("Start");
        button.setLayoutX(600);
        button.setLayoutY(595);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                startMilli = System.currentTimeMillis();
                //xia.play();
                newPiece.getChildren().clear();
                newPiece.setOpacity(1);
                pieces.setOpacity(1);
                //laugh.stop();
                hideCompletion();
                //hideSkulls();
                hideUsingTime();
                makePlacement();
                //makeSolution(StepsGame.getSolutions());
                makePieces();
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
     * According to the chosen difficulty, generate a new starting placement.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("IQ-Steps Viewer");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        makePegs();
        addBackground();
        addTitle();
        primaryStage.setScene(scene);
        root.getChildren().add(pieces);
//        root.getChildren().add(solution);
        root.getChildren().add(controls);
        root.getChildren().add(pegs);
        root.getChildren().add(newPiece);
        setUpHandlers(scene);
       // setUpSoundLoop();
        //makeSkulls();
        makeControls();
        //makeCompletion();
        //makeMiniInstruction();
        //makeInstruction();
        makeUsingTime();
        //makeTextEffect();
        ///newGame();
        primaryStage.setScene(scene);
        primaryStage.show();
        }}
