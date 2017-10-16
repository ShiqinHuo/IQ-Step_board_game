package comp1110.ass2;

import java.util.ArrayList;

/**
 * Created by wenjunyang on 7/10/17.
 */
public class MaskGenerator {
    /**
     * Generate different valid states of masks for a piece of placement which starts in A, C, D, F, G, H
     * @param first A char describe the type of masks
     * @return An ArrayList which contains all possible states for a piece of placement which starts in A, C, D, F, G, H
     */
    public static ArrayList<String> maskGenerator1(char first){
        //All the positions where a piece of placement which starts in A, C, D, F, G, H can be placed on
        char[] ACDFGH1 = {'L', 'N', 'P', 'R', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm'};
        char[] ACDFGH2 = {'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'h', 'j', 'l', 'n'};
        ArrayList<String> newArr = new ArrayList<>();
        //different possible states for one single mask
        char[] second1 = {'A','B','C','D'};  char[] second2 = {'E','F','G','H'};
        //connect the type of mask, state of mask and position of mask when the state of mask is in {'A','B','C','D'}
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < ACDFGH1.length; j++){
                newArr.add(String.valueOf(first)+String.valueOf(second1[i])+String.valueOf(ACDFGH1[j]));
            }
        }
        //connect the type of mask, state of mask and position of mask when the state of mask is in {'E','F','G','H'}
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < ACDFGH2.length; j++){
                newArr.add(String.valueOf(first)+String.valueOf(second2[i])+String.valueOf(ACDFGH2[j]));
            }
        }
        return newArr;
    }

    /**
     * Generate different valid states of masks for a piece of placement which starts in B
     * @return An ArrayList which contains all possible states for a piece of placement which starts in B
     */
    public static ArrayList<String> maskGenerator2(){
        //All the positions where a piece of placement which starts in B can be placed on
        char[] BAEC = {'L', 'N', 'P', 'R', 'U', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm'}; char[] BCEA = {'L', 'N', 'P', 'R', 'T', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm', 'o'};
        char[] BBED = {'C', 'E', 'G', 'I', 'L', 'N', 'P', 'R', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm'}; char[] BDEB = {'L', 'N', 'P', 'R', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm', 'r', 't', 'v', 'x'};
        char[] BEEG = {'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'e', 'h', 'j', 'l', 'n'}; char[] BGEE = {'K', 'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'f', 'h', 'j', 'l', 'n'};
        char[] BFEH = {'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'h', 'j', 'l', 'n', 'q', 's', 'u', 'w'}; char[] BHEF = {'B', 'D', 'F', 'H', 'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'h', 'j', 'l', 'n'};

        ArrayList<String> newArr = new ArrayList<>();
        //connect the type of mask and state of mask with position of mask
        for (int i = 0; i < BAEC.length; i++){newArr.add("BA"+BAEC[i]);} for (int i = 0; i < BBED.length; i++){newArr.add("BB"+BBED[i]);}
        for (int i = 0; i < BEEG.length; i++){newArr.add("BE"+BEEG[i]);} for (int i = 0; i < BFEH.length; i++){newArr.add("BF"+BFEH[i]);}
        for (int i = 0; i < BCEA.length; i++){newArr.add("BC"+BCEA[i]);} for (int i = 0; i < BDEB.length; i++){newArr.add("BD"+BDEB[i]);}
        for (int i = 0; i < BGEE.length; i++){newArr.add("BG"+BGEE[i]);} for (int i = 0; i < BHEF.length; i++){newArr.add("BH"+BHEF[i]);}

        return newArr;
    }

    /**
     * Generate different valid states of masks for a piece of placement which starts in E
     * @return An ArrayList which contains all possible states for a piece of placement which starts in E
     */
    public static ArrayList<String> maskGenerator3(){
        //All the positions where a piece of placement which starts in E can be placed on
        char[] BAEC = {'L', 'N', 'P', 'R', 'U', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm'}; char[] BCEA = {'L', 'N', 'P', 'R', 'T', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm', 'o'};
        char[] BBED = {'C', 'E', 'G', 'I', 'L', 'N', 'P', 'R', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm'}; char[] BDEB = {'L', 'N', 'P', 'R', 'W', 'Y', 'b', 'd', 'g', 'i', 'k', 'm', 'r', 't', 'v', 'x'};
        char[] BEEG = {'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'e', 'h', 'j', 'l', 'n'}; char[] BGEE = {'K', 'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'f', 'h', 'j', 'l', 'n'};
        char[] BFEH = {'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'h', 'j', 'l', 'n', 'q', 's', 'u', 'w'}; char[] BHEF = {'B', 'D', 'F', 'H', 'M', 'O', 'Q', 'S', 'V', 'X', 'a', 'c', 'h', 'j', 'l', 'n'};

        ArrayList<String> newArr = new ArrayList<>();
        //connect the type of mask and state of mask with position of mask
        for (int i = 0; i < BAEC.length; i++){newArr.add("EC"+BAEC[i]);} for (int i = 0; i < BBED.length; i++){newArr.add("ED"+BBED[i]);}
        for (int i = 0; i < BEEG.length; i++){newArr.add("EG"+BEEG[i]);} for (int i = 0; i < BFEH.length; i++){newArr.add("EH"+BFEH[i]);}
        for (int i = 0; i < BCEA.length; i++){newArr.add("EA"+BCEA[i]);} for (int i = 0; i < BDEB.length; i++){newArr.add("EB"+BDEB[i]);}
        for (int i = 0; i < BGEE.length; i++){newArr.add("EE"+BGEE[i]);} for (int i = 0; i < BHEF.length; i++){newArr.add("EF"+BHEF[i]);}

        return newArr;
    }
}
