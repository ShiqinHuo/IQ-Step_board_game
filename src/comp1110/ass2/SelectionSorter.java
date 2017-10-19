package comp1110.ass2;

/**
 * Created by wenjunyang on 7/10/17.
 */

/**
 * This class sorts an array, using the selection sort algorithm
 * Idea is from the BIG JAVA: Early Objects written by CAY HORSTMANN
 */
public class SelectionSorter {

    /**
     * Sorts the array managed by this selection sorter
     * @param a An character array
     * @return An character array which has been sorted according to order of alphabet
     */
    public static Character[] sort(Character[] a){
        Character[] outcome = new Character[a.length];
        for (int i = 0; i < a.length; i++){
            int minPos = minimumPos(a,i);
            outcome = swap(a,minPos,i);

        }
        return outcome;
    }

    /**
     * Swaps two entries of the array
     * @param input An character array
     * @param i the first position to swap
     * @param j the second position to swap
     * @return the character array after swapping the two elements
     */
    public static Character[] swap(Character[] input,int i,int j){
        Character temp = input[i];
        input[i] = input[j];
        input[j] = temp;
        return input;
    }

    /**
     * Swaps two entries of the array
     * @param a An character array
     * @param from the first position in a to compare
     * @return the position of the smallest elements in the range a[from] ... a[a.length - 1]
     */
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
