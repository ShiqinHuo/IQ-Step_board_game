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

      //if the placement is null or empty, then return false
      if (placement == null || placement.equals("")){
          return false;
      }else if (placement.length()%3 != 0){ //if the length of placement is not divisible by three, return false
          return false;
      }else {
              String[] a = new String[placement.length()/3];

              for (int i = 0; i < placement.length()/3; i++){
                  a[i] = placement.substring(3*i,3*i+3);       //split the placement in order to get the state of each mask
              }

              Set<String> k = new HashSet<>();
              for (int n = 0; n < a.length; n++){        //add the element in the array into an hashset
                  k.add(a[n]);
              }

              if (k.size() != a.length){         //check whether there are duplicate elements in the mask
                  return false;
              }
          
              for (int m = 0; m < a.length; m++){
                  if (isPiecePlacementWellFormed(a[m]) == true && isDuplicate(placement) == false){
                      return true;           //check whether each mask in the placement is well-formed
                  }                          //and whether there are shapes appearing more than once in the placement
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
            input.add(b[3*m]);         //add every shape of the mask into an arrayList
        }

        if (i.length() == 3){
            return false;       //if the length of each mask is 3, then the mask is not duplicate
        }else {
            Set<Character> k = new HashSet<>();
            for (int n = 0; n < input.size(); n++){
                k.add(input.get(n));
            }

            if (k.size() != input.size()){   //if the size of set is not equal to the length of array, which means
                return true;                 // there exists the same mask in the placement, the mask is duplicate
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
        if (!isPlacementWellFormed(placement)){   //if the placement is not well-formed, then return false
            return false;
        }else{
            String[] place = new String[placement.length()/3];

            for (int i = 0; i < placement.length()/3; i++){
                place[i] = placement.substring(3*i,3*i+3);   //split the placement in order to get the state of each mask

            }

            for (int m = 0; m < place.length; m++){
                if (!isValidPieceString(place[m])){   //check whether each mask is placed in the board(not outside the board) and
                    return false;                     //whether the centre of each mask is placed on the right peg
                }
            }

            ArrayList<Integer> outcome = new ArrayList<>();
            for (int v = 0; v < place.length; v++){                   //add the coordinate of each mask(in the form of number) into a ArrayList
                ArrayList<Integer> list = getCoordinate(place[v]);
                for (int w = 0; w <list.size(); w++){
                    outcome.add(list.get(w));
                }
            }

            Set<Integer> out = new HashSet<>();                     //add the coordinate of each mask into a set
            for (int j = 0; j < outcome.size(); j++){
                out.add(outcome.get(j));
            }

            if (outcome.size() != out.size()){                     //check whether the coordinate of each mask is duplicate or not
                return false;                                      //so as to check whether the mask placed on the board overlaps
            }
        }
        return true;
    }

    /**
     * Helper for task5
     * Select elements from an array according to the change of element in another multi-dimensional array
     * @param a A multi-dimensional array which is a model for a mask.
     * @param b A 3*3 square which include all the coordinate of mask
     * @return An ArrayList which stores the coordinate of a String
     */
    static ArrayList<Integer> selection (int[][] a, int[] b){
        ArrayList<Integer> arr1 = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                //select all the elements in the position of 0 in the multi-dimensional array
                if (a[i][j] == 0){
                arr1.add(b[3*i+j]);
                }
            }
        }

        return arr1;
    }

    /**
     * Helper for task5
     * Get all the coordinates of the spot of mask
     * @param s a String whose length is 3
     * @return An ArrayList which stores the coordinate of a String
     */
    static ArrayList<Integer> getCoordinate(String s){
        Map<Character, Integer> pin = new HashMap<>();
        //All the coordinate of mask on the board
        char[] position = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
                           'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y'};

        //Transform all the characters to the number(coordinate)
        for (int i = 0; i < position.length; i++){
            pin.put(position[i],i);
        }

        //Get the shape of a mask
        int third = pin.get(s.charAt(2));

        //Get a 3*3 square which include the coordinate of masks
        int[] gobal = {third-11, third-10, third-9, third-1, third, third+1, third+9, third+10, third+11};

        //A model for each shape of mask
        int[][] arrA = {{0, 0, -1},{0, 0, 0}, {0, -1, -1}}; int[][] arrB = {{-1, 0, -1},{-1, 0, 0},{-1, 0, 0}};
        int[][] arrC = {{-1, 0, -1},{-1, 0, 0},{ 0, 0, -1}}; int[][] arrD = {{-1, 0, -1},{0, 0, -1},{-1, 0, 0}};
        int[][] arrE = {{-1, 0, -1},{0, 0, -1},{0, 0, -1}}; int[][] arrF = {{-1, -1, 0},{-1, 0, 0},{0, 0, -1}};
        int[][] arrG = {{-1, 0, 0},{-1, 0, 0},{0, 0, -1}}; int[][] arrH = {{-1, 0, 0}, {0, 0, -1},{-1, 0, 0}};

        if (s.charAt(0) == 'A'){ //decide the first element of a String whose length is 3
            if (s.charAt(1) == 'A'){
                return selection(arrA,gobal); //Select elements from global according to the elements in arrA
            }else if (s.charAt(1) == 'B'){
                return selection(rotate(arrA),gobal); //Select elements from global according to the elements in arrA whcih has been rotated right
            }else if (s.charAt(1) == 'C'){
                return selection(rotate(rotate(arrA)),gobal);  //Select elements from global according to the elements in arrA whcih has been rotated 180 degrees
            }else if (s.charAt(1) == 'D'){
                return selection(rotate(rotate(rotate(arrA))),gobal); //Select elements from global according to the elements in arrA whcih has been rotated left
            }else if (s.charAt(1) == 'E'){
                return selection(reverse(arrA),gobal); //Select elements from global according to the elements in arrA which has been reversed
            }else if (s.charAt(1) == 'F'){
                return selection(rotate(reverse(arrA)),gobal); //Select elements from global according to the elements in arrA which has been reversed first and then rotated right
            }else if (s.charAt(1) == 'G'){
                return selection(rotate(rotate(reverse(arrA))),gobal); //Select elements from global according to the elements in arrA which has been reversed first and then rotated 180 degrees
            }else if (s.charAt(1) == 'H'){
                return selection(rotate(rotate(rotate(reverse(arrA)))),gobal); //Select elements from global according to the elements in arrA which has been reversed first and then rotated left
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

    /**
     * Helper for task5
     * Rotate a matrix
     * @param matrix a two-dimensional array which stores the coordinates of mask
     * @return A two-dimensional array which has been rotated
     */
    static int[][] rotate(int[][] matrix) {
        int[][] temp = new int[3][3];
        int[][] outcome = new int[3][3];
        //Swap the first line of matrix with the third
        temp[0] = matrix[2];
        temp[2] = matrix[0];
        temp[1] = matrix[1];

        //Swap the element in different lines
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                outcome[j][i] = temp[i][j];
            }
        }
        return outcome;
    }

    /**
     * Helper for task5
     * Reverse a matrix
     * @param matrix a two-dimensional array which stores the coordinates of mask
     * @return A two-dimensional array which has been reverse
     */
    static int[][] reverse(int[][] matrix){
        int[][]outcome = new int[3][3];
        //Swap the element in different lines according to their position
        for (int i = 0;i<3;i++){
            for (int j =0 ;j<3;j++ ){
                outcome[i][j]= matrix[i][2-j];
            }
        }
        return outcome;
    }

    /**
     * Helper for task5
     * Judge whether the String is in the board and whether the centre of each mask is placed on the right peg
     * @param p an input String which represents the state of a mask
     * @return boolean which decide whether the state of input String is valid or not
     */
    static boolean isValidPieceString(String p){
        //All possible position on the board for different masks with different directions
        char[] ACDFGH1 = {'L', 'N', 'P', 'R', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm'}; char[] ACDFGH2 = {'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'h', 'j', 'l', 'n'};
        char[] BAEC = {'L', 'N', 'P', 'R', 'U', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm'}; char[] BCEA = {'L', 'N', 'P', 'R', 'T', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm', 'o'};
        char[] BBED = {'C', 'E', 'G', 'I', 'L', 'N', 'P', 'R', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm'}; char[] BDEB = {'L', 'N', 'P', 'R', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm', 'r', 't', 'v', 'x'};
        char[] BEEG = {'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'e', 'h', 'j', 'l', 'n'}; char[] BGEE = {'K', 'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'f', 'h', 'j', 'l', 'n'};
        char[] BFEH = {'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'h', 'j', 'l', 'n', 'q', 's', 'u', 'w'}; char[] BHEF = {'B', 'D', 'F', 'H', 'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'h', 'j', 'l', 'n'};

        //decide the first character of the mask
        if (p.charAt(0) == 'A'|| p.charAt(0) == 'C' || p.charAt(0) == 'D' || p.charAt(0) == 'F' || p.charAt(0) == 'G' || p.charAt(0) == 'H'){
            //decide the second character of the mask
            if (p.charAt(1) >= 'A' && p.charAt(1)<='D'){
                ArrayList<Character> ACDFGHList1 = new ArrayList<>();
                for (int m = 0; m < ACDFGH1.length; m++){
                    ACDFGHList1.add(ACDFGH1[m]);
                }
                if (ACDFGHList1.contains(p.charAt(2))){ //decide whether the coordinate of mask is in the valid choice
                    return true;
                }else{
                    return false;
                }
            }else if (p.charAt(1) >= 'E' && p.charAt(1)<='H'){
                ArrayList<Character> ACDFGHList2 = new ArrayList<>();
                for (int m = 0; m < ACDFGH2.length; m++){
                    ACDFGHList2.add(ACDFGH2[m]);
                }
                if (ACDFGHList2.contains(p.charAt(2))){   //decide whether the coordinate of mask is in the valid choice
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

        /*Consider valid object*/
        if(!isPlacementSequenceValid(objective)){
            return new HashSet<>();
        }


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
            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

            /*Clone the values*/
            ArrayList<String> delete = new ArrayList<>(value);



            for (String de: value) {
                if(notObstruct(key,de)){
                    delete.remove(de);
                    newStarter.put(key+de,delete);
                    //System.out.println(newStarter);

                    /*Reset the array list delete*/
                    delete = new ArrayList<>(value);
                }else{
                    break;
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
     public static boolean notObstruct(String placement, String next){
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

        //Get all the solutions of a startPoint placement
        Set<String> nl = possibleSolutions(placement);
        ArrayList<String> out = new ArrayList<>(nl);
        String[] outcome = new String[nl.size()];

        //Put all the elements into an array
       for (int i = 0; i < out.size(); i++){
           outcome[i] = out.get(i);
       }

       //return the array
        return outcome;
    }

    /**
     * Helper for task 9
     * Given a start point placement and return a set which contains all the possible solution
     * @param placement A startPoint placement for the game
     * @return A set which contains all the possible solutions for the input placement
     */
    public static Set<String> possibleSolutions(String placement){
        //All the possible states of the different masks
        ArrayList<String> A = MaskGenerator.maskGenerator1('A'); ArrayList<String> B = MaskGenerator.maskGenerator2();
        ArrayList<String> C = MaskGenerator.maskGenerator1('C'); ArrayList<String> D = MaskGenerator.maskGenerator1('D');
        ArrayList<String> E = MaskGenerator.maskGenerator3(); ArrayList<String> F = MaskGenerator.maskGenerator1('F');
        ArrayList<String> G = MaskGenerator.maskGenerator1('G'); ArrayList<String> H = MaskGenerator.maskGenerator1('H');

        ArrayList<Character> firstAlphabet = new ArrayList<>();

        //Get the first alphabet of an arbitrary mask
        for (int i = 0; i < placement.length()/3; i++){
            firstAlphabet.add(placement.charAt(3*i));
        }

        //Create a Map which contains all the possible states of masks according to the first alphabet of 3-length String
        Map<Character,ArrayList<String>> newMap = new HashMap<>();
        newMap.put('A',A);newMap.put('B',B);newMap.put('C',C);newMap.put('D',D);newMap.put('E',E);newMap.put('F',F);newMap.put('G',G);newMap.put('H',H);

        Set<Character> key = newMap.keySet();

        //Remove all the masks which has existed in the start String
        for (int i = 0; i < firstAlphabet.size(); i++){
            key.remove(firstAlphabet.get(i));
        }

        ArrayList<Character> keyList = new ArrayList<>(key);

        Set<String> orders = new HashSet<>();
        //Discuss the situation when every mask has been placed on the board
        if (key.size() == 0){
            orders.add(placement);
            return orders;
        //Discuss the situation when one mask has not been placed on the board
        }else if (key.size() == 1){
            ArrayList<String> one = new ArrayList<>();
            String process;
            for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(0)).get(i))){  //Check the mask which will be placed on the board does not obstruct the masks which have been placed on the board
                        process = placement + newMap.get(keyList.get(0)).get(i);
                        one.add(process);
                }
            }
            Set<String> two = new HashSet<>(one);
            return two;
            //Discuss the situation when two masks have not been placed on the board
        }else if (key.size() == 2){
            ArrayList<String> one = new ArrayList<>();
            Set<String> two = new HashSet<>();
            String process1;
            for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(0)).get(i))){
                    process1 = placement + newMap.get(keyList.get(0)).get(i); // Check the mask which will be placed on the board does not obstruct the masks which have been placed on the board
                    one.add(process1);                                        // If so, then add the valid mask to the initial placement
                }  //place the first element in the map first
                for (String b : one){
                    for (int j = 0; j < newMap.get(keyList.get(1)).size(); j++){  //Similar to the operation above
                        if (notObstruct(b,newMap.get(keyList.get(1)).get(j))){
                        b = b + newMap.get(keyList.get(1)).get(j);
                        two.add(b);  //then place the second element in the map
                        }
                    }
                }
            }

            ArrayList<String> three = new ArrayList<>();
            Set<String> four = new HashSet<>();
            String process2;
            if (two.isEmpty()){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (notObstruct(placement,newMap.get(keyList.get(1)).get(i))){
                        process2 = placement + newMap.get(keyList.get(1)).get(i);
                        three.add(process2);                         //place the second element in the map first
                    }
                    for (String b : three){
                        for (int j = 0; j < newMap.get(keyList.get(0)).size(); j++){
                            if (notObstruct(b,newMap.get(keyList.get(0)).get(j))){
                                b = b + newMap.get(keyList.get(0)).get(j);
                                four.add(b);                         // then place the first element
                            }
                        }
                    }
                }
            }
            if (two.isEmpty()){
                return four;
            }else {
                return two;
            }
            //The code below uses the same idea as the code above
        }else if (key.size() == 3){
            ArrayList<String> one = new ArrayList<>();
            String process0; String process1; String process2;
            for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(0)).get(i))){
                    process0 = placement + newMap.get(keyList.get(0)).get(i);
                    one.add(process0);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(1)).get(i))){
                    process1 = placement + newMap.get(keyList.get(1)).get(i);
                    one.add(process1);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(2)).get(i))){
                    process2 = placement + newMap.get(keyList.get(2)).get(i);
                    one.add(process2);
                }
            }


            ArrayList<String> two = new ArrayList<>();
            String process5; String process6; String process7;
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process5 = s + newMap.get(keyList.get(0)).get(i);
                        if (isPlacementSequenceValid(process5)){
                            two.add(process5);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process6 = s + newMap.get(keyList.get(1)).get(i);
                        if (isPlacementSequenceValid(process6)){
                            two.add(process6);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process7 = s + newMap.get(keyList.get(2)).get(i);
                        if (isPlacementSequenceValid(process7)){
                            two.add(process7);
                        }
                    }
                }
            }


            ArrayList<String> three = new ArrayList<>();
            String process10; String process11; String process12;
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process10 = s + newMap.get(keyList.get(0)).get(i);
                        if (isPlacementSequenceValid(process10)){
                            three.add(process10);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process11 = s + newMap.get(keyList.get(1)).get(i);
                        if (isPlacementSequenceValid(process11)){
                            three.add(process11);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process12 = s + newMap.get(keyList.get(2)).get(i);
                        if (isPlacementSequenceValid(process12)){
                            three.add(process12);
                        }
                    }
                }
            }
            return afterSort(three);
        }else if (key.size() == 4) {
            ArrayList<String> one = new ArrayList<>();
            String process0; String process1; String process2; String process3;
            for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(0)).get(i))){
                    process0 = placement + newMap.get(keyList.get(0)).get(i);
                    one.add(process0);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(1)).get(i))){
                    process1 = placement + newMap.get(keyList.get(1)).get(i);
                    one.add(process1);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(2)).get(i))){
                    process2 = placement + newMap.get(keyList.get(2)).get(i);
                    one.add(process2);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(3)).get(i))){
                    process3 = placement + newMap.get(keyList.get(3)).get(i);
                    one.add(process3);
                }
            }

            ArrayList<String> two = new ArrayList<>();
            String process5; String process6; String process7; String process8;
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process5 = s + newMap.get(keyList.get(0)).get(i);
                        if (isPlacementSequenceValid(process5)){
                            two.add(process5);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process6 = s + newMap.get(keyList.get(1)).get(i);
                        if (isPlacementSequenceValid(process6)){
                            two.add(process6);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process7 = s + newMap.get(keyList.get(2)).get(i);
                        if (isPlacementSequenceValid(process7)){
                            two.add(process7);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process8 = s + newMap.get(keyList.get(3)).get(i);
                        if (isPlacementSequenceValid(process8)){
                            two.add(process8);
                        }
                    }
                }
            }

            ArrayList<String> three = new ArrayList<>();
            String process10; String process11; String process12; String process13;
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process10 = s + newMap.get(keyList.get(0)).get(i);
                        if (isPlacementSequenceValid(process10)){
                            three.add(process10);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process11 = s + newMap.get(keyList.get(1)).get(i);
                        if (isPlacementSequenceValid(process11)){
                            three.add(process11);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process12 = s + newMap.get(keyList.get(2)).get(i);
                        if (isPlacementSequenceValid(process12)){
                            three.add(process12);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process13 = s + newMap.get(keyList.get(3)).get(i);
                        if (isPlacementSequenceValid(process13)){
                            three.add(process13);
                        }
                    }
                }
            }

            ArrayList<String> four = new ArrayList<>();
            String process15; String process16; String process17; String process18;
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process15 = s + newMap.get(keyList.get(0)).get(i);
                        if (isPlacementSequenceValid(process15)){
                            four.add(process15);
                        }
                    }
                }
            }
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process16 = s + newMap.get(keyList.get(1)).get(i);
                        if (isPlacementSequenceValid(process16)){
                            four.add(process16);
                        }
                    }
                }
            }
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process17 = s + newMap.get(keyList.get(2)).get(i);
                        if (isPlacementSequenceValid(process17)){
                            four.add(process17);
                        }
                    }
                }
            }
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process18 = s + newMap.get(keyList.get(3)).get(i);
                        if (isPlacementSequenceValid(process18)){
                            four.add(process18);
                        }
                    }
                }
            }

            return afterSort(four);
        }else if (key.size() == 5) {
            ArrayList<String> one = new ArrayList<>();
            String process1; String process3; String process4;
            for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(1)).get(i))){
                    process1 = placement + newMap.get(keyList.get(1)).get(i);
                    one.add(process1);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(3)).get(i))){
                    process3 = placement + newMap.get(keyList.get(3)).get(i);
                    one.add(process3);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(4)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(4)).get(i))){
                    process4 = placement + newMap.get(keyList.get(4)).get(i);
                    one.add(process4);
                }
            }

            ArrayList<String> two = new ArrayList<>();
            String process5; String process6; String process7; String process9;
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process5 = s + newMap.get(keyList.get(0)).get(i);
                        if (isPlacementSequenceValid(process5)){
                            two.add(process5);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process6 = s + newMap.get(keyList.get(1)).get(i);
                        if (isPlacementSequenceValid(process6)){
                            two.add(process6);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process7 = s + newMap.get(keyList.get(2)).get(i);
                        if (isPlacementSequenceValid(process7)){
                            two.add(process7);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(4)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(4)).get(i))){
                        process9 = s + newMap.get(keyList.get(4)).get(i);
                        if (isPlacementSequenceValid(process9)){
                            two.add(process9);
                        }
                    }
                }
            }

            ArrayList<String> three = new ArrayList<>();
            String process10; String process12; String process14;
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process10 = s + newMap.get(keyList.get(0)).get(i);
                        if (isPlacementSequenceValid(process10)){
                        three.add(process10);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process12 = s + newMap.get(keyList.get(2)).get(i);
                        if (isPlacementSequenceValid(process12)){
                            three.add(process12);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(4)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(4)).get(i))){
                        process14 = s + newMap.get(keyList.get(4)).get(i);
                        if (isPlacementSequenceValid(process14)){
                            three.add(process14);
                        }
                    }
                }
            }

            ArrayList<String> four = new ArrayList<>();
            String process15; String process16; String process17; String process18; String process19;
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process15 = s + newMap.get(keyList.get(0)).get(i);
                        if (isPlacementSequenceValid(process15)){
                            four.add(process15);
                        }
                    }
                }
            }
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process16 = s + newMap.get(keyList.get(1)).get(i);
                        if (isPlacementSequenceValid(process16)){
                            four.add(process16);
                        }
                    }
                }
            }
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process17 = s + newMap.get(keyList.get(2)).get(i);
                        if (isPlacementSequenceValid(process17)){
                            four.add(process17);
                        }
                    }
                }
            }

            ArrayList<String> five = new ArrayList<>();
            String process21; String process22; String process23;
            for (String s : four){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process21 = s + newMap.get(keyList.get(1)).get(i);
                        if (isPlacementSequenceValid(process21)){
                            five.add(process21);
                        }
                    }
                }
            }
            for (String s : four){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process22 = s + newMap.get(keyList.get(2)).get(i);
                        if (isPlacementSequenceValid(process22)){
                            five.add(process22);
                        }
                    }
                }
            }
            for (String s : four){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process23 = s + newMap.get(keyList.get(3)).get(i);
                        if (isPlacementSequenceValid(process23)){
                            five.add(process23);
                        }
                    }
                }
            }
            return afterSort(five);
        } else if (key.size() == 6) {
            ArrayList<String> one = new ArrayList<>();
            String process0; String process1; String process2; String process3; String process4; String process100;
            for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(0)).get(i))){
                    process0 = placement + newMap.get(keyList.get(0)).get(i);
                    one.add(process0);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(1)).get(i))){
                    process1 = placement + newMap.get(keyList.get(1)).get(i);
                    one.add(process1);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(2)).get(i))){
                    process2 = placement + newMap.get(keyList.get(2)).get(i);
                    one.add(process2);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(3)).get(i))){
                    process3 = placement + newMap.get(keyList.get(3)).get(i);
                    one.add(process3);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(4)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(4)).get(i))){
                    process4 = placement + newMap.get(keyList.get(4)).get(i);
                    one.add(process4);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(5)).size(); i++){
                if (notObstruct(placement,newMap.get(keyList.get(5)).get(i))){
                    process100 = placement + newMap.get(keyList.get(5)).get(i);
                    one.add(process100);
                }
            }

            ArrayList<String> two = new ArrayList<>();
            String process5; String process6; String process7; String process8; String process9; String process101;
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process5 = s + newMap.get(keyList.get(0)).get(i);
                        if (isPlacementSequenceValid(process5)){
                            two.add(process5);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process6 = s + newMap.get(keyList.get(1)).get(i);
                        if (isPlacementSequenceValid(process6)){
                            two.add(process6);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process7 = s + newMap.get(keyList.get(2)).get(i);
                        if (isPlacementSequenceValid(process7)){
                            two.add(process7);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process8 = s + newMap.get(keyList.get(3)).get(i);
                        if (isPlacementSequenceValid(process8)){
                            two.add(process8);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(4)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(4)).get(i))){
                        process9 = s + newMap.get(keyList.get(4)).get(i);
                        if (isPlacementSequenceValid(process9)){
                            two.add(process9);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(5)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(5)).get(i))){
                        process101 = s + newMap.get(keyList.get(5)).get(i);
                        if (isPlacementSequenceValid(process101)){
                            two.add(process101);
                        }
                    }
                }
            }

            ArrayList<String> three = new ArrayList<>();
            String process11;
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process11 = s + newMap.get(keyList.get(1)).get(i);
                        if (isPlacementSequenceValid(process11)){
                            three.add(process11);
                        }
                    }
                }
            }

            ArrayList<String> four = new ArrayList<>();
            String process18;
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process18 = s + newMap.get(keyList.get(3)).get(i);
                        if (isPlacementSequenceValid(process18)){
                            four.add(process18);
                        }
                    }
                }
            }

            ArrayList<String> five = new ArrayList<>();
            String process20;
            for (String s : four){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process20 = s + newMap.get(keyList.get(0)).get(i);
                        if (isPlacementSequenceValid(process20)){
                            five.add(process20);
                        }
                    }
                }
            }

            ArrayList<String> six = new ArrayList<>();
            String process27;
            for (String s : five){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process27 = s + newMap.get(keyList.get(2)).get(i);
                        if (isPlacementSequenceValid(process27)){
                            six.add(process27);
                        }
                    }
                }
            }
            return afterSort(six);
        } else {
            return null;
        }
    }

    /**
     * Helper for task 9
     * Normalize all the correct solutions(the solutions are all correct,
     * the order of masks may be different, we need to select one solution
     * from the solutions with different order)
     * @param outcome A ArrayList which contains all the correct solutions
     * @return A set which contains solution(s) which has been normalized
     */
    public static Set<String> afterSort(ArrayList<String> outcome){
        ArrayList<String> ll = new ArrayList<>();
        Map<String,Character[]> gg = new HashMap<>();

        //Create a map which stores all the possible solutions and their corresponding characters after sorting
        for (int i = 0; i < outcome.size(); i++){
            gg.put(outcome.get(i),SelectionSorter.sort(MaskStringSplit(outcome.get(i))));
        }

        for (int i = 0 ; i < outcome.size();i++){
            //Define that the initial state is 0
            int flag = 0;
            for (int j = 0 ; j < i;j++){
                if (Arrays.equals(gg.get(outcome.get(i)),gg.get(outcome.get(j)))){ //If the array of character is the same, change the state
                    flag = 1;
                }
            }
            if (flag == 0){ // Record all the states which are 0 (indicate that the array of character is different)
                ll.add(outcome.get(i));
            }
        }
        //Put all all the results into a set
        Set<String> result = new HashSet<>(ll);
        return result;
    }

    /**
     * Helper for task 9
     * Split the String and get every character in the input String
     * @param a A String with 24 characters
     * @return An array of character which stores every character of the input String
     */
    public static Character[] MaskStringSplit(String a){
        Character[] outcome = new Character[24];
        for (int i = 0; i < a.length(); i++){
            outcome[i] = a.charAt(i);
        }
        return outcome;
    }
}