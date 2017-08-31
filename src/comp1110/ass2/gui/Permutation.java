package comp1110.ass2.gui;

import java.util.ArrayList;

/**
 * Created by luoxi on 31/08/2017.
 */
public class Permutation {
    /**
     * Helper for task6
     * Based on the placement, finding the all possible home location
     * Get idea from Heap's Algorithm
     * @param candidates the unused pieces draw from objective
     * @return An ArrayList<String[]> which contains all the possible permutation
     */
    
    public static ArrayList<String> getPermutations(ArrayList<String> candidates, int n) {

        /*Create an ArrayLIst to store the permutations*/
        ArrayList<String> permutations = new ArrayList<>();


        /*Heap's algorithm : generate all the possible permutations*/
        int[] c = new int[n];
        for (int i = 0; i < n ; i++) {
            c[i] = 0;
        }
        String starter = ConvertToString(candidates);
        permutations.add(starter);

        for(int i = 0; i < n ; i++) {
            if (c[i] < i){
                if(i % 2 == 0) {
                    swap(0,i,candidates);

                } else {
                    swap(c[i],i,candidates);

                }
                System.out.println(candidates);
                String newPer = ConvertToString(candidates);
                permutations.add(newPer);
                c[i]++;
                i = 0;
            } else {
                c[i] = 0 ;
            }

        } // end while

        System.out.println("SIZE!"+permutations.size());
        return permutations;
    }

    private static void swap(int i, int j, ArrayList<String> candidates){
        String c = candidates.get(i);
        candidates.set(i, candidates.get(j));
        candidates.set(j,c);
    }



    private static String ConvertToString(ArrayList<String> list){
        StringBuilder sb = new StringBuilder();

        for (String str: list
             ) {
            sb.append(str);
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        ArrayList<String> abcd = new ArrayList<>();
        abcd.add("a");
        abcd.add("b");
        abcd.add("c");
        abcd.add("d");
        swap(0,1,abcd);
        ArrayList<String> pers = getPermutations(abcd,abcd.size());

        for (String per: pers
             ) {
            System.out.println(per);
            }


    }
}
