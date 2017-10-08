package comp1110.ass2;

import com.sun.deploy.util.ArrayUtil;

/**
 * Created by wenjunyang on 7/10/17.
 */
public class SelectionSorter {
    public static Character[] sort(Character[] a){
        Character[] outcome = new Character[a.length];
        for (int i = 0; i < a.length; i++){
            int minPos = minimumPos(a,i);
            outcome = swap(a,minPos,i);

        }
        return outcome;
    }

    public static Character[] swap(Character[] input,int i,int j){
        Character temp = input[i];
        input[i] = input[j];
        input[j] = temp;
        return input;
    }

    public static int minimumPos(Character[] a, int from){
        int minPos = from;
        for (int i = from + 1; i < a.length; i++){
            if (a[i] < a[minPos]){
                minPos = i;
            }
        }
        return minPos;
    }
}
