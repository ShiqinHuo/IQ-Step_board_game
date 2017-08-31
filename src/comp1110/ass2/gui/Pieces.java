package comp1110.ass2.gui;

/**
 * Created by luoxi on 30/08/2017.
 */
public class Pieces {

    /*
    * Store all 16 pieces in 3x3 matrix
    * 0 for bottom rings
    * 1 for top rings
    * -1 for empty position
    */

    static final int[][] AA = {
            { 0, 1, -1},
            { 1, 0,  1},
            { 0,-1, -1}
    };

    static final int[][] AE = flipPieces(AA);


    static final int[][] BA = {
            {-1, 1, -1},
            {-1, 0,  1},
            {-1, 1,  0}
    };

    static final int[][] BE = flipPieces(BA);

    static final int[][] CA = {
            {-1, 1, -1},
            {-1, 0,  1},
            {0,  1, -1}
    };

    static final int[][] CE = flipPieces(CA);

    static final int[][] DA = {
            {-1, 1, -1},
            {1 , 0, -1},
            {-1, 1,  0}
    };

    static final int[][] DE = flipPieces(DA);

    static final int[][] EA = {
            {-1, 1, -1},
            {1 , 0, -1},
            {0 , 1, -1}
    };


    static final int[][] EE = flipPieces(EA);


    static final int[][] FA = {
            {-1,-1,  0},
            {-1, 0,  1},
            {0 , 1, -1}
    };

    static final int[][] FE = flipPieces(FA);

    static final int[][] GA = {
            {-1, 1,  0},
            {-1, 0,  1},
            {0 , 1, -1}
    };

    static final int[][] GE = flipPieces(GA);

    static final int[][] HA = {
            {-1, 1,  0},
            { 1, 0, -1},
            {-1, 1,  0}
    };

    static final int[][] HE = flipPieces(HA);


    /**
     * This method flip the given pieces
     * @param m original pieces
     * @return a piece flipped
     */
    private static int[][] flipPieces(int[][] m){
        int[][] flip = new int[3][3];

        for (int i = 0; i < 3 ; i++) {
            flip[i][0] = m[i][2];
            flip[i][1] = m[i][1];
            flip[i][2] = m[i][0];
        }

        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 3 ; j++) {
                if(flip[i][j] == 0) flip[i][j] = 1;
                else if(flip[i][j] == 1) flip[i][j] = 0;
            }
        }
        return flip;
    }



    public static void main(String[] args) {
        int[][]test = flipPieces(EA);
        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 3 ; j++) {
                System.out.print(test[i][j]+" ");
            }

        }
    }


}
