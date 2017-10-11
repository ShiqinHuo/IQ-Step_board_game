package comp1110.ass2;

import java.util.ArrayList;

/**
 * Created by wenjunyang on 8/10/17.
 */
public class StartPointGenerator {

    public static ArrayList<String> spGeneratorForSix(){

        ArrayList<String> A = MaskGenerator.maskGenerator1('A'); ArrayList<String> B = MaskGenerator.maskGenerator2();
        ArrayList<String> C = MaskGenerator.maskGenerator1('C'); ArrayList<String> D = MaskGenerator.maskGenerator1('D');
        ArrayList<String> E = MaskGenerator.maskGenerator3(); ArrayList<String> F = MaskGenerator.maskGenerator1('F');
        ArrayList<String> G = MaskGenerator.maskGenerator1('G'); ArrayList<String> H = MaskGenerator.maskGenerator1('H');

        ArrayList<String> outcome = new ArrayList<>();
        for (int i = 0; i < B.size();i++){
            for (int j = 0; j < E.size();j++){
                for (int m = 0; m < C.size();m++){
                    for (int n = 0; n < A.size();n++){
                        for (int a = 0; a < H.size();a++){
                                if (StepsGame.isPlacementSequenceValid(B.get(i)+E.get(j)+C.get(m)+A.get(n)+H.get(a))){
                                    if (SolverForHint.Solutions(B.get(i)+E.get(j)+C.get(m)+A.get(n)+H.get(a)).size() == 1){
                                        if (outcome.size() <= 1) {
                                            outcome.add(B.get(i)+E.get(j)+C.get(m)+A.get(n)+H.get(a));
                                        } else {
                                            break;
                                        }
                                    }
                                }
                        }
                        if (outcome.size() > 1) {
                            break;
                        }
                    }
                    if (outcome.size() > 1) {
                        break;
                    }
                }
                if (outcome.size() > 1) {
                    break;
                }
            }
            if (outcome.size() > 1) {
                break;
            }
        }
        return outcome;
    }

    public static void main(String[] args) {
        System.out.println(spGeneratorForSix());
    }
}
