package comp1110.ass2.gui;

/**
 * Created by luoxi on 30/08/2017.
 */
public class SquareMatrix {
    private int length;
    private int[][] matrix;

    SquareMatrix(int length){
        this.length = length;
        int[][] matrix = new int[length][length];
    }

    SquareMatrix(int[][] square){
        length = square.length;
        this.matrix = new int[length][length];
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++)
                this.matrix[i][j] = square[i][j];
    }


    public SquareMatrix transpose(){
        SquareMatrix trans = new SquareMatrix(length);
        for (int i = 0; i < length ; i++) {
            for (int j = 0; j < length ; j++) {
                trans.matrix[j][i] = matrix[i][j];
            }
        }
        return trans;
    }

    public SquareMatrix rotate90(){
  return null;
    }




}

