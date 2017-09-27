package comp1110.ass2;

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
    public static boolean isPlacementSequenceValid(String placement) {
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

        /*Create starting hashmap*/
        Map<String, ArrayList<String>> stater = new HashMap<>();
        stater.put(placement,getCandidates(placement,objective));

        Set<String> orders = validOrders(stater);

        /*Draw viable pieces from the orders*/
        Set<String> viablePieces = new HashSet<>();
        orders.forEach(order -> viablePieces.add(order.substring(placement.length(),placement.length() + 3)));

        return viablePieces;
    }



    /**
     * Helper for task6
     * Get all rest placements by discarding used pieces , given the current placement and objective
     * @param placement A valid piece placement string.
     * @param objective A valid game objective, but not necessarily a valid placement string
     * @return all the pieces' placements which are not used
     */
    public static ArrayList<String> getCandidates(String placement, String objective){

        /*Get the eight item from objective*/
        ArrayList<String> eightPieces = new ArrayList<>();
        for (int i = 0; i < 8 ; i++) {
            eightPieces.add(objective.substring(i*3, (i+1) * 3));
        }

        /*Consider empty string, just return eight unused item*/
        if(placement.equals("")) return eightPieces;


        /*Find all used item from input placement */
        ArrayList<String> used = new ArrayList<>();
        for (int i = 0; i < placement.length() / 3; i++) {
            used.add(placement.substring(i*3, (i+1) * 3));
        }

        /*Collect items which do not occur in used*/
        ArrayList<String> candidates = new ArrayList<>();
        for (String piece: eightPieces
             ) {
            if(!used.contains(piece)) candidates.add(piece);
        }

        return candidates;
    }




    /**
     * Given a Map of String as starter, find all possible order for the given objective
     * @param starter a Map (K:current placement, V:current candidates)
     * @return all possible orders in an array list
     */
     static Set<String> validOrders( Map<String, ArrayList<String>> starter){

        /*Termination condition*/
        if(noCandidates(starter)) return starter.keySet();

        /*Append each candidates and check whether obstruct*/
        /*Recursively*/

        Map<String, ArrayList<String>> newStarter = new HashMap<>();

        for(Map.Entry<String,ArrayList<String>> entry : starter.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();

            /*Clone the values*/
            ArrayList<String> delete = new ArrayList<>(value);


            for (String de: value
                 ) {
                if(notObstruct(key,de)){
                    delete.remove(de);
                    newStarter.put(key+de,delete);

                    /*Reset the array list delete*/
                    delete = new ArrayList<>(value);
                }
            }
        }

        return validOrders(newStarter);
    }


    /**
     * Helper for validOrders, determine the termination of the recursion
     * @param map a Map (K:current placement, V:current candidates)
     * @return true if no more candidates for current placement
     */
    private static boolean noCandidates( Map<String, ArrayList<String>> map){
        for(Map.Entry<String,ArrayList<String>> entry : map.entrySet()){
            ArrayList<String> value = entry.getValue();
            if(value.isEmpty()) return true;
        }
        return false;
    }



    /**
     * Helper for task 6
     * @param placement A valid sequence of piece placements where each piece placement is drawn from the objective
     * @param next The next 3 length String who is trying to put on the board
     * @return true if the next piece (which is going to put in) does not obstruct the current pieces
     */
     static boolean notObstruct(String placement, String next){
        ArrayList<String> shapes = new ArrayList<>();
        for (int i = 0; i < placement.length()/3 ; i++) {
            shapes.add(placement.substring(i * 3, i * 3 + 3));
        }

        /*Record the positions where the input placement have used*/
        Set<Integer> positions = new HashSet<>();
        for (String shape : shapes) {
            positions.addAll(Pieces.usedPos(shape));
        }

        /*Record the positions the next piece cannot use*/
        Set<Integer> update = Pieces.cannotUse(positions);


        /*Find positions the next pieces will take*/
        Set<Integer> nextPositions = Pieces.usedPos(next);
        for (int integer: nextPositions
             ) {
            if(update.contains(integer)) return false;
        }

        return true;

    }





    /**
     * Return an array of all unique (unordered) solutions to the game, given a
     * starting placement.   A given unique solution may have more than one than
     * one placement sequence, however, only a single (unordered) solution should
     * be returned for each such case.
     *
     * @return An array of strings, each describing a unique unordered solution to
     * the game given the starting point provided by placement.
     */
    public static String[] getSolutions(String placement) {
        // FIXME Task 9: determine all solutions to the game, given a particular starting placement
        return null;
    }

    public static void main(String[] args) {
        Set<String> newSet = new HashSet<>();
        //newSet = getViablePiecePlacements("CEQEHuGEO","CEQEHuGEOBDxFGSHCiAALDBg");
        for (String k : newSet){
            //System.out.println(k);
        }

        ArrayList<String> newArr = new ArrayList<>();
        //newArr = getCandidates("CEQEHuGEO","CEQEHuGEOBDxFGSHCiAALDBg");
        for (String m : newArr){
            // System.out.println(m);
        }

        //boolean a = notObstruct("AALBBGCAkDBgEAoFDNGHS","HCi");
        //System.out.println(a);

        for (String m : possibleSolutions("CEQEHuGEOBDxFGS")){
            System.out.println(m);
        }

        /*Map<String,ArrayList<String>> newMap = new HashMap<>();
        ArrayList<String> newArr1 = new ArrayList<>();
        newArr1.add("AAL"); newArr1.add("DBg"); newArr1.add("HCi");
        newMap.put("CEQEHuGEOBDxFGS",newArr1);
        Set<String> orders = validOrders(newMap);
        for (String a : orders){
            System.out.println(a);
        }*/
    }

    public static Set<String> possibleSolutions(String placement){
        ArrayList<String> A = maskGenerator1('A'); ArrayList<String> B = maskGenerator2();
        ArrayList<String> C = maskGenerator1('C'); ArrayList<String> D = maskGenerator1('D');
        ArrayList<String> E = maskGenerator3(); ArrayList<String> F = maskGenerator1('F');
        ArrayList<String> G = maskGenerator1('G'); ArrayList<String> H = maskGenerator1('H');

        ArrayList<Character> firstAlphabet = new ArrayList<>();

        for (int i = 0; i < placement.length()/3; i++){
            firstAlphabet.add(placement.charAt(3*i));
        }

        Map<Character,ArrayList<String>> newMap = new HashMap<>();
        newMap.put('A',A);newMap.put('B',B);newMap.put('C',C);newMap.put('D',D);newMap.put('E',E);newMap.put('F',F);newMap.put('G',G);newMap.put('H',H);

        Set<Character> key = newMap.keySet();

        for (int i = 0; i < firstAlphabet.size(); i++){
            key.remove(firstAlphabet.get(i));
        }

        ArrayList<Character> keyList = new ArrayList<>(key);

        //List<String[]> newList = new ArrayList<>();

        /*for (int i = 0; i < keyList.size(); i++){
            newMap.get(keyList.get(i));
            String [] c = newMap.get(keyList.get(i)).toArray(new String[newMap.get(keyList.get(i)).size()]);
            newList.add(c);
        }

        List<List<String>> firstOutcome = combineAlg(newList);

        ArrayList<String> outcome = new ArrayList<>();

        String a = "";
        for (int i = 0; i < firstOutcome.size(); i++){
            for (int j = 0; j < firstOutcome.get(i).size(); j++){
                a+=firstOutcome.get(i).get(j);
            }
            if (isPlacementSequenceValid(a))
                if (isPlacementSequenceValid(a+placement)){
                    outcome.add(a+placement);
                }
            a = "";
        }

        return outcome;*/
        ArrayList<String> outcome = new ArrayList<>();
        Set<String> orders = new HashSet<>();
        if (key.size() == 0){
            orders.add(placement);
            return orders;
        }else if (key.size() == 1){
            for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                if (isPlacementSequenceValid(newMap.get(keyList.get(0)).get(i))){
                    if (isPlacementSequenceValid(newMap.get(keyList.get(0)).get(i) + placement)){
                        Map<String,ArrayList<String>> newap = new HashMap<>();
                        ArrayList<String> k = new ArrayList<>();
                        k.add(newMap.get(keyList.get(0)).get(i));
                        newap.put(placement,k);
                        orders = validOrders(newap);
                    }
                }
            }
            return orders;
        }else if (key.size() == 2){
            for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                for (int j = 0; j < newMap.get(keyList.get(1)).size(); j++){
                    if (isPlacementSequenceValid(newMap.get(keyList.get(0)).get(i)+newMap.get(keyList.get(1)).get(j))){
                        if (isPlacementSequenceValid(newMap.get(keyList.get(0)).get(i)+newMap.get(keyList.get(1)).get(j)+placement)){
                            Map<String,ArrayList<String>> newap = new HashMap<>();
                            ArrayList<String> k = new ArrayList<>();
                            k.add(newMap.get(keyList.get(0)).get(i));k.add(newMap.get(keyList.get(1)).get(j));
                            newap.put(placement,k);
                            orders = validOrders(newap);
                        }
                    }
                }
            }
            return orders;
        }else if (key.size() == 3){
            for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                for (int j = 0; j < newMap.get(keyList.get(1)).size(); j++){
                    for (int m = 0; m < newMap.get(keyList.get(2)).size(); m++){
                        if (isPlacementSequenceValid(newMap.get(keyList.get(0)).get(i)+newMap.get(keyList.get(1)).get(j)+newMap.get(keyList.get(2)).get(m))){
                            if (isPlacementSequenceValid(newMap.get(keyList.get(0)).get(i)+newMap.get(keyList.get(1)).get(j)+newMap.get(keyList.get(2)).get(m)+placement)){
                                Map<String,ArrayList<String>> newap = new HashMap<>();
                                ArrayList<String> k = new ArrayList<>();
                                k.add(newMap.get(keyList.get(0)).get(i));k.add(newMap.get(keyList.get(1)).get(j));k.add(newMap.get(keyList.get(2)).get(m));
                                newap.put(placement,k);
                                orders = validOrders(newap);
                            }
                        }
                    }
                }
            }
            return orders;
        }else if (key.size() == 4) {
            for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++) {
                for (int j = 0; j < newMap.get(keyList.get(1)).size(); j++) {
                    for (int m = 0; m < newMap.get(keyList.get(2)).size(); m++) {
                        for (int n = 0; n < newMap.get(keyList.get(3)).size(); n++) {
                            if (isPlacementSequenceValid(newMap.get(keyList.get(0)).get(i) + newMap.get(keyList.get(1)).get(j) + newMap.get(keyList.get(2)).get(m) + newMap.get(keyList.get(3)).get(n)))
                                if (isPlacementSequenceValid(newMap.get(keyList.get(0)).get(i) + newMap.get(keyList.get(1)).get(j) + newMap.get(keyList.get(2)).get(m) + newMap.get(keyList.get(3)).get(n) + placement))
                                outcome.add(newMap.get(keyList.get(0)).get(i) + newMap.get(keyList.get(1)).get(j) + newMap.get(keyList.get(2)).get(m) + newMap.get(keyList.get(3)).get(n) + placement);
                        }
                    }
                }
            }
            return orders;
        }else if (key.size() == 5) {
            for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++) {
                for (int j = 0; j < newMap.get(keyList.get(1)).size(); j++) {
                    for (int m = 0; m < newMap.get(keyList.get(2)).size(); m++) {
                        for (int n = 0; n < newMap.get(keyList.get(3)).size(); n++) {
                            for (int a = 0; a < newMap.get(keyList.get(4)).size(); a++){
                                if (isPlacementSequenceValid(newMap.get(keyList.get(0)).get(i) + newMap.get(keyList.get(1)).get(j) + newMap.get(keyList.get(2)).get(m) + newMap.get(keyList.get(3)).get(n)+newMap.get(keyList.get(4)).get(a)))
                                    if (isPlacementSequenceValid(newMap.get(keyList.get(0)).get(i) + newMap.get(keyList.get(1)).get(j) + newMap.get(keyList.get(2)).get(m) + newMap.get(keyList.get(3)).get(n) + newMap.get(keyList.get(4)).get(a)+ placement))
                                        outcome.add(newMap.get(keyList.get(0)).get(i) + newMap.get(keyList.get(1)).get(j) + newMap.get(keyList.get(2)).get(m) + newMap.get(keyList.get(3)).get(n) +newMap.get(keyList.get(4)).get(a)+ placement);
                            }
                        }
                    }
                }
            }
            return orders;
        }else {
            return null;
        }
    }

    public static List<List<String>> combineAlg(List<String[]> nArray) {
        List<List<String>> values = new LinkedList<List<String>>();
        int[] x = new int[nArray.size()];
        for (int i = 0; i < x.length; i++) {
            x[i] = 0;
        }

        int flag = 0;
        do {
            List<String> objs = new LinkedList<String>();
            for (int looper = 0; looper < nArray.size(); looper++) {
                objs.add(nArray.get(looper)[x[looper]]);
            }
            flag = NextPermutation(x, nArray);
            values.add(objs);
        } while (flag != 1);
        return values;
    }

    public static int NextPermutation(int[] x, List<String[]> nArray) {
        int carry = 0;
        for (int looper = nArray.size() - 1; looper >= 0; looper--) {
            if (x[looper] + 1 == nArray.get(looper).length) {
                carry = 1;
                x[looper] = 0;
            } else {
                x[looper] = x[looper] + 1;
                carry = 0;
                return 0;
            }
        }

        if (carry == 1)
            return 1;
        else
            return 0;
    }

    public static ArrayList<String> maskGenerator1(char first){
        char[] ACDFGH1 = {'L', 'N', 'P', 'R', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm'};
        char[] ACDFGH2 = {'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'h', 'j', 'l', 'n'};
        ArrayList<String> newArr = new ArrayList<>();
        char[] second1 = {'A','B','C','D'};  char[] second2 = {'E','F','G','H'};
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < ACDFGH1.length; j++){
                newArr.add(String.valueOf(first)+String.valueOf(second1[i])+String.valueOf(ACDFGH1[j]));
            }
        }
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < ACDFGH2.length; j++){
                newArr.add(String.valueOf(first)+String.valueOf(second2[i])+String.valueOf(ACDFGH2[j]));
            }
        }
        return newArr;
    }

    public static ArrayList<String> maskGenerator2(){
        char[] BAEC = {'L', 'N', 'P', 'R', 'U', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm'}; char[] BCEA = {'L', 'N', 'P', 'R', 'T', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm', 'o'};
        char[] BBED = {'C', 'E', 'G', 'I', 'L', 'N', 'P', 'R', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm'}; char[] BDEB = {'L', 'N', 'P', 'R', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm', 'r', 't', 'v', 'x'};
        char[] BEEG = {'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'e', 'h', 'j', 'l', 'n'}; char[] BGEE = {'K', 'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'f', 'h', 'j', 'l', 'n'};
        char[] BFEH = {'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'h', 'j', 'l', 'n', 'q', 's', 'u', 'w'}; char[] BHEF = {'B', 'D', 'F', 'H', 'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'h', 'j', 'l', 'n'};

        ArrayList<String> newArr = new ArrayList<>();
        for (int i = 0; i < BAEC.length; i++){newArr.add("BA"+BAEC[i]);} for (int i = 0; i < BBED.length; i++){newArr.add("BB"+BBED[i]);}
        for (int i = 0; i < BEEG.length; i++){newArr.add("BE"+BEEG[i]);} for (int i = 0; i < BFEH.length; i++){newArr.add("BF"+BFEH[i]);}
        for (int i = 0; i < BCEA.length; i++){newArr.add("BC"+BCEA[i]);} for (int i = 0; i < BDEB.length; i++){newArr.add("BD"+BDEB[i]);}
        for (int i = 0; i < BGEE.length; i++){newArr.add("BG"+BGEE[i]);} for (int i = 0; i < BHEF.length; i++){newArr.add("BH"+BHEF[i]);}

        return newArr;
    }

    public static ArrayList<String> maskGenerator3(){
        char[] BAEC = {'L', 'N', 'P', 'R', 'U', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm'}; char[] BCEA = {'L', 'N', 'P', 'R', 'T', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm', 'o'};
        char[] BBED = {'C', 'E', 'G', 'I', 'L', 'N', 'P', 'R', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm'}; char[] BDEB = {'L', 'N', 'P', 'R', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm', 'r', 't', 'v', 'x'};
        char[] BEEG = {'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'e', 'h', 'j', 'l', 'n'}; char[] BGEE = {'K', 'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'f', 'h', 'j', 'l', 'n'};
        char[] BFEH = {'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'h', 'j', 'l', 'n', 'q', 's', 'u', 'w'}; char[] BHEF = {'B', 'D', 'F', 'H', 'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'h', 'j', 'l', 'n'};

        ArrayList<String> newArr = new ArrayList<>();
        for (int i = 0; i < BAEC.length; i++){newArr.add("EC"+BAEC[i]);} for (int i = 0; i < BBED.length; i++){newArr.add("ED"+BBED[i]);}
        for (int i = 0; i < BEEG.length; i++){newArr.add("EG"+BEEG[i]);} for (int i = 0; i < BFEH.length; i++){newArr.add("EH"+BFEH[i]);}
        for (int i = 0; i < BCEA.length; i++){newArr.add("EA"+BCEA[i]);} for (int i = 0; i < BDEB.length; i++){newArr.add("EB"+BDEB[i]);}
        for (int i = 0; i < BGEE.length; i++){newArr.add("EE"+BGEE[i]);} for (int i = 0; i < BHEF.length; i++){newArr.add("EF"+BHEF[i]);}

        return newArr;
    }


}
