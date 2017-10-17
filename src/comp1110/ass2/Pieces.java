package comp1110.ass2;

import comp1110.ass2.Alphabet;

import java.util.*;

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


    /*Reflect the relationship of the 9 grids*/
    private final static int[][] grid3x3 = {
            {-11, -10, -9},
            {-1, 0, +1},
            {+9, +10, +11}
    };

    static final int[][] AA = {
            {0, 1, -1},
            {1, 0, 1},
            {0, -1, -1}
    };

    static final int[][] AB = rotateRight(AA);
    static final int[][] AC = rotateRight(AB);
    static final int[][] AD = rotateRight(AC);
    static final int[][] AE = flipPieces(AA);
    static final int[][] AF = rotateRight(AE);
    static final int[][] AG = rotateRight(AF);
    static final int[][] AH = rotateRight(AG);


    static final int[][] BA = {
            {-1, 1, -1},
            {-1, 0, 1},
            {-1, 1, 0}
    };
    static final int[][] BB = rotateRight(BA);
    static final int[][] BC = rotateRight(BB);
    static final int[][] BD = rotateRight(BC);
    static final int[][] BE = flipPieces(BA);
    static final int[][] BF = rotateRight(BE);
    static final int[][] BG = rotateRight(BF);
    static final int[][] BH = rotateRight(BG);

    static final int[][] CA = {
            {-1, 1, -1},
            {-1, 0, 1},
            {0, 1, -1}
    };

    static final int[][] CB = rotateRight(CA);
    static final int[][] CC = rotateRight(CB);
    static final int[][] CD = rotateRight(CC);
    static final int[][] CE = flipPieces(CA);
    static final int[][] CF = rotateRight(CE);
    static final int[][] CG = rotateRight(CF);
    static final int[][] CH = rotateRight(CG);

    static final int[][] DA = {
            {-1, 1, -1},
            {1, 0, -1},
            {-1, 1, 0}
    };

    static final int[][] DB = rotateRight(DA);
    static final int[][] DC = rotateRight(DB);
    static final int[][] DD = rotateRight(DC);
    static final int[][] DE = flipPieces(DA);
    static final int[][] DF = rotateRight(DE);
    static final int[][] DG = rotateRight(DF);
    static final int[][] DH = rotateRight(DG);

    static final int[][] EA = {
            {-1, 1, -1},
            {1, 0, -1},
            {0, 1, -1}
    };

    static final int[][] EB = rotateRight(EA);
    static final int[][] EC = rotateRight(EB);
    static final int[][] ED = rotateRight(EC);
    static final int[][] EE = flipPieces(EA);
    static final int[][] EF = rotateRight(EE);
    static final int[][] EG = rotateRight(EF);
    static final int[][] EH = rotateRight(EG);

    static final int[][] FA = {
            {-1, -1, 0},
            {-1, 0, 1},
            {0, 1, -1}
    };

    static final int[][] FB = rotateRight(FA);
    static final int[][] FC = rotateRight(FB);
    static final int[][] FD = rotateRight(FC);
    static final int[][] FE = flipPieces(FA);
    static final int[][] FF = rotateRight(FE);
    static final int[][] FG = rotateRight(FF);
    static final int[][] FH = rotateRight(FG);

    static final int[][] GA = {
            {-1, 1, 0},
            {-1, 0, 1},
            {0, 1, -1}
    };

    static final int[][] GB = rotateRight(GA);
    static final int[][] GC = rotateRight(GB);
    static final int[][] GD = rotateRight(GC);
    static final int[][] GE = flipPieces(GA);
    static final int[][] GF = rotateRight(GE);
    static final int[][] GG = rotateRight(GF);
    static final int[][] GH = rotateRight(GG);

    static final int[][] HA = {
            {-1, 1, 0},
            {1, 0, -1},
            {-1, 1, 0}
    };

    static final int[][] HB = rotateRight(HA);
    static final int[][] HC = rotateRight(HB);
    static final int[][] HD = rotateRight(HC);
    static final int[][] HE = flipPieces(HA);
    static final int[][] HF = rotateRight(HE);
    static final int[][] HG = rotateRight(HF);
    static final int[][] HH = rotateRight(HG);


    static final int[][][][] pieces = {
            {AA, AB, AC, AD, AE, AF, AG, AH},
            {BA, BB, BC, BD, BE, BF, BG, BH},
            {CA, CB, CC, CD, CE, CF, CG, CH},
            {DA, DB, DC, DD, DE, DF, DG, DH},
            {EA, EB, EC, ED, EE, EF, EG, EH},
            {FA, FB, FC, FD, FE, FF, FG, FH},
            {GA, GB, GC, GD, GE, GF, GG, GH},
            {HA, HB, HC, HD, HE, HF, HG, HH}
    };


    /**
     * This method flip the given pieces
     *
     * @param m original pieces
     * @return a piece flipped
     */
    static int[][] flipPieces(int[][] m) {
        int[][] flip = new int[3][3];

        for (int i = 0; i < 3; i++) {
            flip[i][0] = m[i][2];
            flip[i][1] = m[i][1];
            flip[i][2] = m[i][0];
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (flip[i][j] == 0) flip[i][j] = 1;
                else if (flip[i][j] == 1) flip[i][j] = 0;
            }
        }
        return flip;
    }


    /**
     * This method rotate piece by 90 degree in right direction
     *
     * @param m original 3x3 matrix
     * @return new piece got by rotate m
     */
    private static int[][] rotateRight(int[][] m) {

        int[][] rotate = new int[3][3];
        /*Swap the first row and last row*/
        rotate[0] = m[2];
        rotate[2] = m[0];
        rotate[1] = m[1];

        /*Do transpose*/
        return transpose(rotate);


    }

    /**
     * This method get the transpose of the given matrix
     *
     * @param m given matrix
     * @return a transposed matrix
     */
    static int[][] transpose(int[][] m) {

        int[][] transpose = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                transpose[j][i] = m[i][j];
            }
        }
        return transpose;
    }


    /**
     * Get current position in board
     *
     * @param placement length 3 String which represent the special piece which have be used
     * @return a set of integer, telling the position
     */
    public static Set<Integer> usedPos(String placement) {

        String first = String.valueOf(placement.charAt(0));
        String second = String.valueOf(placement.charAt(1));
        String third = String.valueOf(placement.charAt(2));

        int row = Alphabet.valueOf(first).getId();
        int column = Alphabet.valueOf(second).getId();
        int homePos = Alphabet.valueOf(third).getId();

        int[][] shape = pieces[row][column];

        Set<Integer> positions = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (shape[i][j] == 0 || shape[i][j] == 1) positions.add(homePos + grid3x3[i][j]);
            }
        }
        return positions;
    }


    /**
     * This method find all the positions which cannot be used in next step
     *
     * @param pos which tell that the positions which has ring on it
     * @return all the position which cannot use by next piece
     */
    public static Set<Integer> cannotUse(Set<Integer> pos) {
        Set<Integer> update = new HashSet<>(pos);
        int[] neighbour = {-10, -1, +1, +10};
        pos.forEach(i -> {
            if (!Alphabet.isPeg(i)) {
                switch (i) {
                    case 9:
                        update.add(8);
                        update.add(19);
                        break;
                    case 10:
                        update.add(0);
                        update.add(11);
                        update.add(20);
                        break;
                    case 29:
                        update.add(19);
                        update.add(28);
                        update.add(39);
                        break;
                    case 30:
                        update.add(20);
                        update.add(31);
                        update.add(40);
                        break;
                    default:
                        for (int nei : neighbour
                                ) {
                            if (i + nei >= 0 && i + nei <= 49) update.add(i + nei);
                        }

                }
            }
        });
        return update;
    }
}

