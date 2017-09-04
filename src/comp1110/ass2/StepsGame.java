package comp1110.ass2;

import com.sun.deploy.util.StringUtils;
import comp1110.ass2.gui.Permutation;
import comp1110.ass2.gui.Pieces;
import gittest.A;

import java.util.*;

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

              Set<String> k = new HashSet<>();
              for (int n = 0; n < a.length; n++){
                  k.add(a[n]);
              }

              if (k.size() != a.length){
                  return false;
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

        for (int m = 0; m < a; m++){
            input.add(b[3*m]);
        }

        if (i.length() == 3){
            return false;
        }else {
            Set<Character> k = new HashSet<>();
            for (int n = 0; n < input.size(); n++){
                k.add(input.get(n));
            }

            if (k.size() != input.size()){
                return true;
            }
        }
        return false;
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
        if (!isPlacementWellFormed(placement)){
            return false;
        }else{
            String[] place = new String[placement.length()/3];

            for (int i = 0; i < placement.length()/3; i++){
                place[i] = placement.substring(3*i,3*i+3);

            }

            for (int m = 0; m < place.length; m++){
                if (!isValidPieceString(place[m])){
                    return false;
                }
            }

            ArrayList<Integer> outcome = new ArrayList<>();
            for (int v = 0; v < place.length; v++){
                ArrayList<Integer> list = getCoordinate(place[v]);
                for (int w = 0; w <list.size(); w++){
                    outcome.add(list.get(w));
                }
            }

            Set<Integer> out = new HashSet<>();
            for (int j = 0; j < outcome.size(); j++){
                out.add(outcome.get(j));
            }

            if (outcome.size() != out.size()){
                return false;
            }
        }
        return true;
    }

    static ArrayList<Integer> selection (int[][] a, int[] b){
        ArrayList<Integer> arr1 = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (a[i][j] == 0){
                arr1.add(b[3*i+j]);
                }
            }
        }

        return arr1;
    }


    static ArrayList<Integer> getCoordinate(String s){
        Map<Character, Integer> pin = new HashMap<>();
        char[] position = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
                           'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y'};

        for (int i = 0; i < position.length; i++){
            pin.put(position[i],i);
        }

        int third = pin.get(s.charAt(2));

        int[] gobal = {third-11, third-10, third-9, third-1, third, third+1, third+9,third+10,third+11};

        int[][] arrA = {{0, 0, -1},{0, 0, 0}, {0, -1, -1}}; int[][] arrB = {{-1, 0, -1},{-1, 0, 0},{-1, 0, 0}};
        int[][] arrC = {{-1, 0, -1},{-1, 0, 0},{ 0, 0, -1}}; int[][] arrD = {{-1, 0, -1},{0, 0, -1},{-1, 0, 0}};
        int[][] arrE = {{-1, 0, -1},{0, 0, -1},{0, 0, -1}}; int[][] arrF = {{-1, -1, 0},{-1, 0, 0},{0, 0, -1}};
        int[][] arrG = {{-1, 0, 0},{-1, 0, 0},{0, 0, -1}}; int[][] arrH = {{-1, 0, 0}, {0, 0, -1},{-1, 0, 0}};

        if (s.charAt(0) == 'A'){
            if (s.charAt(1) == 'A'){
                return selection(arrA,gobal);
            }else if (s.charAt(1) == 'B'){
                return selection(rotate(arrA),gobal);
            }else if (s.charAt(1) == 'C'){
                return selection(rotate(rotate(arrA)),gobal);
            }else if (s.charAt(1) == 'D'){
                return selection(rotate(rotate(rotate(arrA))),gobal);
            }else if (s.charAt(1) == 'E'){
                return selection(reverse(arrA),gobal);
            }else if (s.charAt(1) == 'F'){
                return selection(rotate(reverse(arrA)),gobal);
            }else if (s.charAt(1) == 'G'){
                return selection(rotate(rotate(reverse(arrA))),gobal);
            }else if (s.charAt(1) == 'H'){
                return selection(rotate(rotate(rotate(reverse(arrA)))),gobal);
            }
        }else if (s.charAt(0) == 'B'){
            if (s.charAt(1) == 'A'){
                return selection(arrB,gobal);
            }else if (s.charAt(1) == 'B'){
                return selection(rotate(arrB),gobal);
            }else if (s.charAt(1) == 'C'){
                return selection(rotate(rotate(arrB)),gobal);
            }else if (s.charAt(1) == 'D'){
                return selection(rotate(rotate(rotate(arrB))),gobal);
            }else if (s.charAt(1) == 'E'){
                return selection(reverse(arrB),gobal);
            }else if (s.charAt(1) == 'F'){
                return selection(rotate(reverse(arrB)),gobal);
            }else if (s.charAt(1) == 'G'){
                return selection(rotate(rotate(reverse(arrB))),gobal);
            }else if (s.charAt(1) == 'H'){
                return selection(rotate(rotate(rotate(reverse(arrB)))),gobal);
            }
        }else if (s.charAt(0) == 'C'){
            if (s.charAt(1) == 'A'){
                return selection(arrC,gobal);
            }else if (s.charAt(1) == 'B'){
                return selection(rotate(arrC),gobal);
            }else if (s.charAt(1) == 'C'){
                return selection(rotate(rotate(arrC)),gobal);
            }else if (s.charAt(1) == 'D'){
                return selection(rotate(rotate(rotate(arrC))),gobal);
            }else if (s.charAt(1) == 'E'){
                return selection(reverse(arrC),gobal);
            }else if (s.charAt(1) == 'F'){
                return selection(rotate(reverse(arrC)),gobal);
            }else if (s.charAt(1) == 'G'){
                return selection(rotate(rotate(reverse(arrC))),gobal);
            }else if (s.charAt(1) == 'H'){
                return selection(rotate(rotate(rotate(reverse(arrC)))),gobal);
            }
        }else if (s.charAt(0) == 'D'){
            if (s.charAt(1) == 'A'){
                return selection(arrD,gobal);
            }else if (s.charAt(1) == 'B'){
                return selection(rotate(arrD),gobal);
            }else if (s.charAt(1) == 'C'){
                return selection(rotate(rotate(arrD)),gobal);
            }else if (s.charAt(1) == 'D'){
                return selection(rotate(rotate(rotate(arrD))),gobal);
            }else if (s.charAt(1) == 'E'){
                return selection(reverse(arrD),gobal);
            }else if (s.charAt(1) == 'F'){
                return selection(rotate(reverse(arrD)),gobal);
            }else if (s.charAt(1) == 'G'){
                return selection(rotate(rotate(reverse(arrD))),gobal);
            }else if (s.charAt(1) == 'H'){
                return selection(rotate(rotate(rotate(reverse(arrD)))),gobal);
            }
        }else if (s.charAt(0) == 'E'){
            if (s.charAt(1) == 'A'){
                return selection(arrE,gobal);
            }else if (s.charAt(1) == 'B'){
                return selection(rotate(arrE),gobal);
            }else if (s.charAt(1) == 'C'){
                return selection(rotate(rotate(arrE)),gobal);
            }else if (s.charAt(1) == 'D'){
                return selection(rotate(rotate(rotate(arrE))),gobal);
            }else if (s.charAt(1) == 'E'){
                return selection(reverse(arrE),gobal);
            }else if (s.charAt(1) == 'F'){
                return selection(rotate(reverse(arrE)),gobal);
            }else if (s.charAt(1) == 'G'){
                return selection(rotate(rotate(reverse(arrE))),gobal);
            }else if (s.charAt(1) == 'H'){
                return selection(rotate(rotate(rotate(reverse(arrE)))),gobal);
            }
        }else if (s.charAt(0) == 'F'){
            if (s.charAt(1) == 'A'){
                return selection(arrF,gobal);
            }else if (s.charAt(1) == 'B'){
                return selection(rotate(arrF),gobal);
            }else if (s.charAt(1) == 'C'){
                return selection(rotate(rotate(arrF)),gobal);
            }else if (s.charAt(1) == 'D'){
                return selection(rotate(rotate(rotate(arrF))),gobal);
            }else if (s.charAt(1) == 'E'){
                return selection(reverse(arrF),gobal);
            }else if (s.charAt(1) == 'F'){
                return selection(rotate(reverse(arrF)),gobal);
            }else if (s.charAt(1) == 'G'){
                return selection(rotate(rotate(reverse(arrF))),gobal);
            }else if (s.charAt(1) == 'H'){
                return selection(rotate(rotate(rotate(reverse(arrF)))),gobal);
            }
        }else if (s.charAt(0) == 'G'){
            if (s.charAt(1) == 'A'){
                return selection(arrG,gobal);
            }else if (s.charAt(1) == 'B'){
                return selection(rotate(arrG),gobal);
            }else if (s.charAt(1) == 'C'){
                return selection(rotate(rotate(arrG)),gobal);
            }else if (s.charAt(1) == 'D'){
                return selection(rotate(rotate(rotate(arrG))),gobal);
            }else if (s.charAt(1) == 'E'){
                return selection(reverse(arrG),gobal);
            }else if (s.charAt(1) == 'F'){
                return selection(rotate(reverse(arrG)),gobal);
            }else if (s.charAt(1) == 'G'){
                return selection(rotate(rotate(reverse(arrG))),gobal);
            }else if (s.charAt(1) == 'H'){
                return selection(rotate(rotate(rotate(reverse(arrG)))),gobal);
            }
        }else if (s.charAt(0) == 'H'){
            if (s.charAt(1) == 'A'){
                return selection(arrH,gobal);
            }else if (s.charAt(1) == 'B'){
                return selection(rotate(arrH),gobal);
            }else if (s.charAt(1) == 'C'){
                return selection(rotate(rotate(arrH)),gobal);
            }else if (s.charAt(1) == 'D'){
                return selection(rotate(rotate(rotate(arrH))),gobal);
            }else if (s.charAt(1) == 'E'){
                return selection(reverse(arrH),gobal);
            }else if (s.charAt(1) == 'F'){
                return selection(rotate(reverse(arrH)),gobal);
            }else if (s.charAt(1) == 'G'){
                return selection(rotate(rotate(reverse(arrH))),gobal);
            }else if (s.charAt(1) == 'H'){
                return selection(rotate(rotate(rotate(reverse(arrH)))),gobal);
            }
        }
        return null;
    }

    static int[][] rotate(int[][] matrix) {
        int[][] temp = new int[3][3];
        int[][] outcome = new int[3][3];
        temp[0] = matrix[2];
        temp[2] = matrix[0];
        temp[1] = matrix[1];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                outcome[j][i] = temp[i][j];
            }
        }
        return outcome;
    }

    static int[][] reverse(int[][] matrix){
        int[][]outcome = new int[3][3];
        for (int i = 0;i<3;i++){
            for (int j =0 ;j<3;j++ ){
                outcome[i][j]= matrix[i][2-j];
            }
        }
        return outcome;
    }

    static boolean isValidPieceString(String p){
        char[] ACDFGH1 = {'L', 'N', 'P', 'R', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm'}; char[] ACDFGH2 = {'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'h', 'j', 'l', 'n'};
        char[] BAEC = {'L', 'N', 'P', 'R', 'U', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm'}; char[] BCEA = {'L', 'N', 'P', 'R', 'T', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm', 'o'};
        char[] BBED = {'C', 'E', 'G', 'I', 'L', 'N', 'P', 'R', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm'}; char[] BDEB = {'L', 'N', 'P', 'R', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm', 'r', 't', 'v', 'x'};
        char[] BEEG = {'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'e', 'h', 'j', 'l', 'n'}; char[] BGEE = {'K', 'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'f', 'h', 'j', 'l', 'n'};
        char[] BFEH = {'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'h', 'j', 'l', 'n', 'q', 's', 'u', 'w'}; char[] BHEF = {'B', 'D', 'F', 'H', 'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'h', 'j', 'l', 'n'};

        if (p.charAt(0) == 'A'|| p.charAt(0) == 'C' || p.charAt(0) == 'D' || p.charAt(0) == 'F' || p.charAt(0) == 'G' || p.charAt(0) == 'H'){
            if (p.charAt(1) >= 'A' && p.charAt(1)<='D'){
                ArrayList<Character> ACDFGHList1 = new ArrayList<>();
                for (int m = 0; m < ACDFGH1.length; m++){
                    ACDFGHList1.add(ACDFGH1[m]);
                }
                if (ACDFGHList1.contains(p.charAt(2))){
                    return true;
                }else{
                    return false;
                }
            }else if (p.charAt(1) >= 'E' && p.charAt(1)<='H'){
                ArrayList<Character> ACDFGHList2 = new ArrayList<>();
                for (int m = 0; m < ACDFGH2.length; m++){
                    ACDFGHList2.add(ACDFGH2[m]);
                }
                if (ACDFGHList2.contains(p.charAt(2))){
                    return true;
                }else{
                    return false;
                }
            }
        }else if ((p.charAt(0) == 'B' && p.charAt(1) == 'A')||(p.charAt(0) == 'E' && p.charAt(1) == 'C')){
            ArrayList<Character> BAECList = new ArrayList<>();
            for (int m = 0; m < BAEC.length; m++){
                BAECList.add(BAEC[m]);
            }
            if (BAECList.contains(p.charAt(2))){
                return true;
            }else{
                return false;
            }
        }else if ((p.charAt(0) == 'B' && p.charAt(1) == 'C')||(p.charAt(0) == 'E' && p.charAt(1) == 'A')){
            ArrayList<Character> BCEAList = new ArrayList<>();
            for (int m = 0; m < BCEA.length; m++){
                BCEAList.add(BCEA[m]);
            }
            if (BCEAList.contains(p.charAt(2))){
                return true;
            }else{
                return false;
            }
        }else if ((p.charAt(0) == 'B' && p.charAt(1) == 'B')||(p.charAt(0) == 'E' && p.charAt(1) == 'D')){
            ArrayList<Character> BBEDList = new ArrayList<>();
            for (int m = 0; m < BBED.length; m++){
                BBEDList.add(BBED[m]);
            }
            if (BBEDList.contains(p.charAt(2))){
                return true;
            }else{
                return false;
            }
        } else if ((p.charAt(0) == 'B' && p.charAt(1) == 'D')||(p.charAt(0) == 'E' && p.charAt(1) == 'B')){
            ArrayList<Character> BDEBList = new ArrayList<>();
            for (int m = 0; m < BDEB.length; m++){
                BDEBList.add(BDEB[m]);
            }
            if (BDEBList.contains(p.charAt(2))){
                return true;
            }else{
                return false;
            }
        } else if ((p.charAt(0) == 'B' && p.charAt(1) == 'E')||(p.charAt(0) == 'E' && p.charAt(1) == 'G')){
            ArrayList<Character> BEEGList = new ArrayList<>();
            for (int m = 0; m < BEEG.length; m++){
                BEEGList.add(BEEG[m]);
            }
            if (BEEGList.contains(p.charAt(2))){
                return true;
            }else{
                return false;
            }
        } else if ((p.charAt(0) == 'B' && p.charAt(1) == 'G')||(p.charAt(0) == 'E' && p.charAt(1) == 'E')){
            ArrayList<Character> BGEEList = new ArrayList<>();
            for (int m = 0; m < BGEE.length; m++){
                BGEEList.add(BGEE[m]);
            }
            if (BGEEList.contains(p.charAt(2))){
                return true;
            }else{
                return false;
            }
        } else if ((p.charAt(0) == 'B' && p.charAt(1) == 'F')||(p.charAt(0) == 'E' && p.charAt(1) == 'H')){
            ArrayList<Character> BFEHList = new ArrayList<>();
            for (int m = 0; m < BFEH.length; m++){
                BFEHList.add(BFEH[m]);
            }
            if (BFEHList.contains(p.charAt(2))){
                return true;
            }else{
                return false;
            }
        }else if ((p.charAt(0) == 'B' && p.charAt(1) == 'H')||(p.charAt(0) == 'E' && p.charAt(1) == 'F')){
            ArrayList<Character> BHEFList = new ArrayList<>();
            for (int m = 0; m < BHEF.length; m++){
                BHEFList.add(BHEF[m]);
            }
            if (BHEFList.contains(p.charAt(2))){
                return true;
            }else{
                return false;
            }
        }
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


        /*Consider no more piece can be used*/
        if(placement.length() == objective.length()) return new HashSet<>();

        /*Create new set to store viable piece placement*/
        Set<String> validCandidates = new HashSet<>();




        return validCandidates;


    } // end getViablePiecePlacement



    /**
     * Helper for task6
     * Get all rest placements by discarding used pieces , given the current placement and objective
     * @param placement A valid piece placement string.
     * @param objective A valid game objective, but not necessarily a valid placement string
     * @return all the pieces' placements which are not used
     */
    private static ArrayList<String> getCandidates(String placement, String objective){

        ArrayList<String> cands = new ArrayList<>();
        String restPlacements = objective.substring(placement.length());

        int lenRest = objective.length() - placement.length();

        for (int i = 0; i < lenRest / 3  ; i++) {
            cands.add(restPlacements.substring(i * 3, (i+1) * 3));
        }

        //print
        cands.forEach(can -> System.out.print("can:" + can + " "));

        return cands;
    }




    /**  We need to check in certain home location of object, is it obstruct other pieces
     * @param placements A list of placement
     * @param objective A string represent location of object
     * @return true if this location is valid
     */
    private static ArrayList<String> validOrder(ArrayList<String> placements, ArrayList<String[]> candidates, String objective){

        /*Append each candidates and check whether obstruct*/
        /*Recursively*/
        ArrayList<String> newPlacements = new ArrayList<>();
        ArrayList<String[]> newCandidates = new ArrayList<>();

        StringBuilder sb = new StringBuilder();


        do {
            for (int i = 0; i < candidates.size() ; i++) {
                for (String piece: candidates.get(i)
                     ) {
                    if(notObstruct(placements.get(i), piece)){
                       sb.setLength(0);
                       sb.append(newPlacements.get(i));
                       sb.append(piece);
                       newPlacements.add(sb.toString());
                       //newCandidates.add(getCandidates(sb.toString(),objective));
                    }
                }
            }

            for (String str: newPlacements
                 ) {
                System.out.println("###"+str);
            }

            validOrder(newPlacements,newCandidates,objective);
        } while (newPlacements.get(0).length() < 24);
        return newPlacements;
    }


    public static boolean notObstruct(String placement, String next){
        ArrayList<String> shapes = new ArrayList<>();
        for (int i = 0; i < placement.length()/3 ; i++) {
            shapes.add(placement.substring(i * 3, i * 3 + 3));
        }

        Set<Integer> positions = new HashSet<>();
        for (String shape : shapes) {
            positions.addAll(Pieces.usedPos(shape));
        }

        Set<Integer> update = Pieces.cannotUse(positions);

        Set<Integer> nextPositions = Pieces.usedPos(next);
        for (int integer: nextPositions
             ) {
            if(update.contains(integer)) return false;
        }

        return true;

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
