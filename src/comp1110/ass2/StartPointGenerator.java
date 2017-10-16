package comp1110.ass2;

import java.util.ArrayList;

/**
 * Created by wenjunyang on 8/10/17.
 */
public class StartPointGenerator {
    public static ArrayList<String> spGeneratorForThree(){
        ArrayList<String> A = MaskGenerator.maskGenerator1('A'); ArrayList<String> B = MaskGenerator.maskGenerator2();
        ArrayList<String> C = MaskGenerator.maskGenerator1('C'); ArrayList<String> D = MaskGenerator.maskGenerator1('D');
        ArrayList<String> E = MaskGenerator.maskGenerator3(); ArrayList<String> F = MaskGenerator.maskGenerator1('F');
        ArrayList<String> G = MaskGenerator.maskGenerator1('G'); ArrayList<String> H = MaskGenerator.maskGenerator1('H');
        ArrayList<String> outcome = new ArrayList<>();
        for (int i = 0; i < C.size();i++){
            for (int j = 0; j < E.size();j++){
                for (int m = 0; m < G.size();m++) {
                    String st = C.get(i)+E.get(j)+G.get(m);
                    if (StepsGame.isPlacementSequenceValid(st)){
                        if (st.length() == 9){
                            outcome.add(st);
                        }
                    }
                }
            }
        }
        return outcome;
    }
    public static void main(String[] args) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            result.add(spGeneratorForThree().get(i));
        }
        for (String a : result){
            if (SolverForHint.Solutions(a).size()==1){
                System.out.println(a);
            }else{
                System.out.println("Failed");
            }
        }
    }
}
