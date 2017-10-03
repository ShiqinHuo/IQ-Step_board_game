package comp1110.ass2;

import comp1110.ass2.Pieces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wenjunyang on 19/9/17.
 */
public class PartialSolution {
    private String[] solutions;
    private static final int NMASKS = 8;

    public static final int ACCEPT = 1;
    public static final int ABANDON = 2;
    public static final int CONTINUE = 3;

    public PartialSolution(int size){
        solutions = new String[size];
    }

    private boolean notObstruct(String placement, String next){
        ArrayList<String> shapes = new ArrayList<>();
        for (int i = 0; i < placement.length()/3 ; i++) {
            shapes.add(placement.substring(i * 3, i * 3 + 3));
        }

        Set<Integer> positions = new HashSet<>();
        for (String shape : shapes) {
            positions.addAll(Pieces.usedPos(shape));
        }

        Set<Integer> update = Pieces.cannotUse(positions);

        Set<Integer> nextPositions = Pieces.usedPos(next);

        for (int integer: nextPositions
                ) {
            if(update.contains(integer)) return false;
        }

        return true;
    }

    public int examine(){
        for (int i = 0; i < solutions.length; i++){
            for (int j = i+1; j < solutions.length; j++){
                if (!notObstruct(solutions[i],solutions[j])){
                    return ABANDON;
                }
            }
        }
        if (solutions.length == NMASKS){
            return ACCEPT;
        }else{
            return CONTINUE;
        }
    }

    public PartialSolution[] extend(){
        PartialSolution[] result = new PartialSolution[NMASKS];
        for (int i = 0; i < result.length; i++){

            int size = solutions.length;

            result[i] = new PartialSolution(size+1);

            for (int j = 0; j < size; j++){
                result[i].solutions[size] = solutions[j];
            }

            //result[i].solutions[size]
        }
        return result;
    }
}
