package comp1110.ass2;

import java.util.ArrayList;
import java.util.Set;

/**
 * This class provides the text interface for the Steps Game
 *
 * The game is based directly on Smart Games' IQ-Steps game
 * (http://www.smartgames.eu/en/smartgames/iq-steps)
 */
public class StepsGame {

    /**
     * Determine whether a piece placement is well-formed according to the following:
     * - it consists of exactly three characters
     * - the first character is in the range A .. H (shapes)
     * - the second character is in the range A .. H (orientations)
     * - the third character is in the range A .. Y and a .. y (locations)
     *
     * @param piecePlacement A string describing a piece placement
     * @return True if the piece placement is well-formed
     */
    static boolean isPiecePlacementWellFormed(String piecePlacement) {
        // FIXME Task 2: determine whether a piece placement is well-formed
        //Check it consists of exactly three characters
        if (piecePlacement.length() == 3) {

            char[] placementChar;
            placementChar = piecePlacement.toCharArray();

            //Check first char
            if(placementChar[0] >= 'A' && placementChar[0] <= 'H'){
                //Check second char
                if(placementChar[1] >= 'A' && placementChar[1] <= 'H'){
                    //Check third char

                    return ((placementChar[2] >= 'A' && placementChar[2] <= 'Y') ||
                            (placementChar[2] >= 'a' && placementChar[2] <= 'y'));
                }
            }
        } return false;
    }

    /**
     * Determine whether a placement string is well-formed:
     *  - it consists of exactly N three-character piece placements (where N = 1 .. 8);
     *  - each piece placement is well-formed
     *  - no shape appears more than once in the placement
     *
     * @param placement A string describing a placement of one or more pieces
     * @return True if the placement is well-formed
     */
    static boolean isPlacementWellFormed(String placement) {
      if (placement == null || placement.equals("")){
          return false;
      }else if (placement.length()%3 != 0){
          return false;
      }else {
              String[] a = new String[placement.length()/3];

              for (int i = 0; i < placement.length()/3; i++){
                  a[i] = placement.substring(3*i,3*i+3);
              }

              for (int m = 0; m < a.length; m++){
                  if (isPiecePlacementWellFormed(a[m]) == true && isDuplicate(placement) == false){
                      return true;
                  }
              }
              return false;
          }
      }

    /**
     *Here we need to implement a method called isDuplicate to decide
     whether there are shapes appearing more than once in the placement.
     * @param. placement A string describing a placement of one or more pieces
     * @return false if no shape appears more than once in the placement
     */

    public static boolean isDuplicate (String i){
        int a = i.length()/3;
        char[] b = i.toCharArray();
        ArrayList<Character> input = new ArrayList<>();
        for (int m = 0; m < i.length(); m++){
            input.add(b[m]);
        }

        for (int n = 0; n < a; n++){
            char c = input.get(3*n);
            //input.remove(3*n);
            input.remove(3*n);
            input.remove(3*n);
            if (input.contains(c)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "MCGiAAOAHnGAkFGgEDQDBABF";
        String f = "HHnAAgDBiGAkFGQEDI";
        System.out.println(isDuplicate(s));
        System.out.println(isDuplicate(f));
    }

    /**
     * Determine whether a placement sequence is valid.  To be valid, the placement
     * sequence must be well-formed and each piece placement must be a valid placement
     * (with the pieces ordered according to the order in which they are played).
     *
     * @param placement A placement sequence string
     * @return True if the placement sequence is valid
     */
    static boolean isPlacementSequenceValid(String placement) {
        // FIXME Task 5: determine whether a placement sequence is valid
        return false;
    }

    /**
     * Split the String to different pieces so as to determine whether the sections are valid or
       not.
     * @param. placement A placement sequence string
     * @return a String array divided by input string
     * */
    static String[] split(String imput) {
           String[] output = {"o"};
           return output;
    }
    /**
     * Given a string describing a placement of pieces and a string describing
     * an (unordered) objective, return a set of all possible next viable
     * piece placements.   A viable piece placement must be a piece that is
     * not already placed (ie not in the placement string), and which will not
     * obstruct any other unplaced piece.
     *
     * @param placement A valid sequence of piece placements where each piece placement is drawn from the objective
     * @param objective A valid game objective, but not necessarily a valid placement string
     * @return An set of viable piece placements
     */
    static Set<String> getViablePiecePlacements(String placement, String objective) {
        // FIXME Task 6: determine the correct order of piece placements
        return null;
    }

    /**
     * Get all unused piece , given the current placement
     * @param placement A valid piece placement string.
     * @return all the pieces which are not used
     */
    private static String getUnusedPiece(String placement){
        return null;
    }


    /**
     * Based on the placement, finding the all possible home location
     * @param placement A valid sequence of piece placements where each piece placement is drawn from the objective
     * @return An char[] which contains all the possible home locations
     */
    private static char[] getValidHomeLocation(String placement) {

        return null;
    }

    /**  We need to check in certain home location of object, is it obstruct other pieces
     * @param placement A valid sequence of piece placements where each piece placement is drawn from the objective
     * @param location A string represent location of object
     * @return true if this location is valid
     */
    private static boolean notObtructOthers(String placement, String location){
        return false;
    }



    /**
     * Return an array of all solutions to the game, given a starting placement.
     *
     * @param placement  A valid piece placement string.
     * @return An array of strings, each describing a solution to the game given the
     * starting point provided by placement.
     */
    static String[] getSolutions(String placement) {
        // FIXME Task 9: determine all solutions to the game, given a particular starting placement
        return null;
    }


    /**
     * Attach all possible candidates to form a new String list
     * @param placement A valid piece placement string.
     * @return An array of strings, each is added a piece in valid position
     */
    private static String addValidPiece(String placement){
        return null;
    }


    /**
     * To check whether all the pieces are used
     * @param placement A valid piece placement string.
     * @return true if the length is 24
     */
    private static boolean isEnd(String placement){
        return true;
    }


}
