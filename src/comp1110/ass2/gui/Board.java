package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;


public  class Board extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final String URI_BASE = "assets/";

    /**
     * An inner class that represents pieces used in the game
     * Each of these is a visual representaton of an underlying piece
     */
    // FIXME Task 7: Implement a basic playable Steps Game in JavaFX that only allows pieces to be placed in valid places


    class FXPiece extends ImageView {
        char piece;

        /**
         * Construct chess A-H
         *
         * @param piece A character representing the piece to be created (A..H)
         */

        FXPiece(char piece) {
            if (!(piece >= 'A' && piece <= 'H')) {
                throw new IllegalArgumentException("Bad piece: \"" + piece + " \"");
            }

            setImage(new Image(Board.class.getResource(URI_BASE + piece + ".png").toString()));
            this.piece = piece;
            setFitWidth(Viewer.SQUARE_SIZE);
            setFitHeight(Viewer.SQUARE_SIZE);
        }

        /**
         * Construct a particular peg at a given position
         *
         * @param piece A character representing the piece to be created (A..H)
         * @param ori   A character representing the orientation of the piece
         * @param pos   An integer reflecting the position on the grid (0..99) (consider 2 levels)
         */

        FXPiece(char piece, char ori, int pos) {

        }
    }


    // FIXME Task 8: Implement starting placements

    private static String current = "";
    private static String placement = "";
    private final ArrayList<Viewer.Onepeg> pegs2 = new ArrayList<>();


    /**
     * This class extends FXPiece with the capacity for it to be dragged and dropped,
     * and snap-to-grid.
     */
    class DraggableFXPiece extends FXPiece {
        int position;
        int homeX, homeY;
        double mouseX, mouseY;

        /**
         * Construct a draggable piece (A..H)
         *
         * @param piece The piece identifier ('A'..'H')
         */
        DraggableFXPiece(char piece) {
            super(piece);
            position = -1;
            homeX = 0; // undefined
            setLayoutX(homeX);
            homeY = 0; // undefined
            setLayoutY(homeY);

            /* event handlers */
            setOnScroll(event -> {            // scroll to change orientation
                rotate();
                event.consume();
            });

            setOnMouseClicked(event2 -> {
                if (event2.getClickCount() == 2 && piece != 'A') {
                    flip();
                } else if (event2.getClickCount() == 2 && piece == 'A') {
                    setRotate(180);
                }
            });
            setOnMousePressed(event -> {      // mouse press indicates begin of drag
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                setFitWidth(Viewer.SQUARE_SIZE * 1.5);  // undefined
                setFitHeight(Viewer.SQUARE_SIZE * 1.5); // undefined

            });
            setOnMouseDragged(event -> {      // mouse is being dragged

                toFront();
                double movementX = event.getSceneX() - mouseX;
                double movementY = event.getSceneY() - mouseY;
                setLayoutX(getLayoutX() + movementX);
                setLayoutY(getLayoutY() + movementY);
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                event.consume();
            });
            setOnMouseReleased(event -> {     // drag is complete
                snapToGrid();
                showCompletion();
            });
        }

        /**
         * @return true if the piece is on the board
         */
        private boolean onBoard() {
            return true;
        }

        /**
         * Snap the piece to its home position (if it is not on the grid)
         */
        private void snapToHome() {
            setLayoutX(homeX);
            setLayoutY(homeY);
            setRotate(0);
            setScaleY(1);
            position = -1;
            setFitWidth(Viewer.SQUARE_SIZE);
            setFitHeight(Viewer.SQUARE_SIZE);
        }

        /**
         * Rotate the piece by 90 degrees (unless this is mask zero and there is a constraint on mask zero)
         */
        private void rotate() {
            setRotate((getRotate() + 60) % 360);
        }

        /**
         * Flip the piece
         */
        private void flip() {
            setScaleY(getScaleY() * (-1));
        }


        /**
         * Snap the piece to the nearest grid position (if it is over the grid)
         */
        private void snapToGrid() {

/*            String or = "";
            String now = "";
            for (int i = 1; i <= placement.length() - 1; i += 3)
                if (piece == placement.charAt(i))
                    placement = placement.substring(0, i - 1) + placement.substring(i + 2);
            for (Viewer.Onepeg a : pegs2) {
                if (getLayoutY() + 50 + 25 <= a.y + 20 && getLayoutY() + 50 + 25 >= a.y - 20 && getLayoutX() + 50 + 25 <= a.x + 20 && getLayoutX() + 50 + 25 >= a.x - 20) {
                    setLayoutY(a.y - 50 - 25);
                    setLayoutX(a.x - 50 - 25);
                    setFitWidth(Viewer.SQUARE_SIZE * 1.5);
                    setFitHeight(Viewer.SQUARE_SIZE * 1.5);

                } else
                    continue;
                if (getScaleY() == -1 && piece != 'A')
                    or = or + piece + (char) ('G' + (getRotate() / 60 == 360 ? 0 : getRotate() / 60));
                else if (getScaleY() == 1)
                    or = or + piece + (char) ('A' + (getRotate() / 60 == 360 ? 0 : getRotate() / 60));
                now = a.num + or;
                if (placement.length() == 0)
                    placement = current + placement + now;
                else
                    placement = placement + now;
                break;
            }
            if (now.equals(""))
                snapToHome();
            else if (!StepsGame.isPlacementSequenceValid(placement)) {
                snapToHome();
                placement = placement.substring(0, placement.length() - 3);
            }

        }*/
        }


        /**
         * @return true if this piece can be rotated
         */
        private boolean canRotate() {
            return true; // undefined
        }


        /**
         * @return true if this piece can be flipped
         */
        private boolean canfilpped() {
            return true; // undefined
        }


        /**
         * Determine the grid-position of the origin of the piece (0 .. 15) how to index??
         * or -1 if it is off the grid, taking into account its rotation.
         */
        private void setPosition() {
        }
    }

    /**
     * Set up event handlers for the main game
     *
     * @param scene  The Scene used by the game.
     */
    private void setUpHandlers(Scene scene) {
	/* create handlers for key press and release events */
    }


    /**
     * Set up the sound loop (to play when the 'xx' key is pressed)
     */
    private void setUpSoundLoop() {
    }


    /**
     * Turn the sound loop on or off
     */
    private void toggleSoundLoop() {
    }

    /**
     * Set up the group that represents the solution (and make it transparent)
     * @param solution The solution string.
     */
    private void makeSolution(String solution) {
    }


    // FIXME Task 10: Implement hints

    /**
     * Create the message to be displayed when the player completes the puzzle.
     */
    private void makeCompletion() {
    }


    /**
     * Show the completion message
     */
    private void showCompletion() {
    }


    /**
     * Hide the completion message
     */
    private void hideCompletion() {
    }

    /**
     * Start a new game, resetting everything as necessary
     */
    private void newGame(){
    }


    // FIXME Task 11: Generate interesting starting placements

    /**
     * Set a label for difficulty, when the bar stays at "1", representing easy mode.
     * Similarly,"2" represents medium mode;"3" represents the hard mode.
     */
    public void makeControl() {
    }

    /**
     * save the game process to a file folder
     */
    public void save() throws IOException{

    }
    /**
     * reload the saved game in a specified folder
     */
    public void reload() throws IOException{

    }

    /**
     * give the solution to the next 1 step automatically
     */
    public void hints() {

    }

    /**
     * According to the chosen difficulty, generate a new starting placement.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        }
}
