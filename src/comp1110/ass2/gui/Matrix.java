package comp1110.ass2.gui;

/**
 * Created by luoxi on 31/08/2017.
 */
public class Matrix {

    private final int len;
    private final double[][] data;

    Matrix(int[][] m){
        len = m.length;
        this.data = new double[len][len];
        for (int i = 0; i < len; i++)
            for (int j = 0; j < len; j++)
                this.data[i][j] = data[i][j];
    }
}
