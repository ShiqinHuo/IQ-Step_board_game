package comp1110.ass2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by wenjunyang on 25/9/17.
 */
public class CombinationTest {

    @Test
    public void testOne(){
        String[] testOne = {"A","B","C"};
        ArrayList<String[]> one = new ArrayList<>();
        one.add(testOne);
        List<List<String>> firstOutcome = StepsGame.combineAlg(one);
        assertTrue(firstOutcome.toString().equals("[[A], [B], [C]]"));
    }

    @Test
    public void testTwo(){
        String[] testOne = {"A","B","C"};
        String[] testTwo = {"D","E","F"};
        ArrayList<String[]> one = new ArrayList<>();
        one.add(testOne);
        one.add(testTwo);
        List<List<String>> firstOutcome = StepsGame.combineAlg(one);
        assertTrue(firstOutcome.toString().equals("[[A, D], [A, E], [A, F], [B, D], [B, E], [B, F], [C, D], [C, E], [C, F]]"));
    }

    @Test
    public void testThree(){
        String[] testOne = {"A","B","C"};
        String[] testTwo = {"D","E","F"};
        String[] testThree = {"G","H","I"};
        ArrayList<String[]> one = new ArrayList<>();
        one.add(testOne);
        one.add(testTwo);
        one.add(testThree);
        List<List<String>> firstOutcome = StepsGame.combineAlg(one);
        assertTrue(firstOutcome.toString().equals("[[A, D, G], [A, D, H], [A, D, I], [A, E, G], [A, E, H], [A, E, I], [A, F, G], [A, F, H], [A, F, I], [B, D, G], [B, D, H], [B, D, I], [B, E, G], [B, E, H], [B, E, I], [B, F, G], [B, F, H], [B, F, I], [C, D, G], [C, D, H], [C, D, I], " + "[C, E, G], [C, E, H], [C, E, I], [C, F, G], [C, F, H], [C, F, I]]"));
    }

    @Test
    public void testDifference(){
        String[] testOne = {"A"};
        String[] testTwo = {"D","E"};
        String[] testThree = {"G","H","I"};
        ArrayList<String[]> one = new ArrayList<>();
        one.add(testOne);
        one.add(testTwo);
        one.add(testThree);
        List<List<String>> firstOutcome = StepsGame.combineAlg(one);
        assertTrue(firstOutcome.toString().equals("[[A, D, G], [A, D, H], [A, D, I], [A, E, G], [A, E, H], [A, E, I]]"));
    }

}
