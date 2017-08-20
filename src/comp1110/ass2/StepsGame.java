package comp1110.ass2;

import java.util.HashSet;
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

            byte[] placementInt = new byte[3];

            //Convert char to related ASCII number
            for (int i = 0; i < 3; i ++) {
                placementInt[i] = (byte) placementChar[i];
            }

            //Check first char
            if(placementInt[0] >= 65 && placementInt[0] <= 72){
                //Check second char
                if(placementInt[1] >= 65 && placementInt[1] <= 72){
                    //Check third char
                    return ((placementInt[2] >= 65 && placementInt[2] <= 89) ||
                            (placementInt[2] >= 97 && placementInt[2] <= 121));
                } return false;
            } return false;
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
      }else{
        String[] a = new String[placement.length()/3];
        for (int i = 0; i < placement.length()/3; i++){
            a[i] = placement.substring(3*i,3*i+3);
        }

        for (int m = 0; m < a.length; m++){
            if (isPiecePlacementWellFormed(a[m]) == true && isDuplicate(a) == false){
                return true;
            }
        }
        return false;
      }
    }

    public static boolean isDuplicate (String[] i){
        HashSet<String> m = new HashSet<>();
        for (int n = 0; n < i.length; n++){
            m.add(i[n]);
        }
        if (m.size() == i.length){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = {"HHn", "EDI"};
        System.out.println(isDuplicate(a));

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
}
