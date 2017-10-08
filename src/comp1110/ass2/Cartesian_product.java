package comp1110.ass2;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wenjunyang on 7/10/17.
 */
public class Cartesian_product {
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
}
