package comp1110.ass2;

/**
 * Created by DoubleHUO on 25/9/17.
 * testReverse & testRotate (created by Wenjun Yang on 25/9/17.)
 */

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class PieceOperationTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    @Test
    public void testFlip (){
        int[][] test1 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] result = Pieces.flipPieces(test1);
        assertTrue("testFirstRow",result[0][0] == 3 && result[0][1] == 2 && result[0][2] == 0);
        assertTrue("testSecondRow",result[1][0] == 6 && result[1][1] == 5 && result[1][2] == 4);
        assertTrue("testThirdRow",result[2][0] == 9 && result[2][1] == 8 && result[2][2] == 7);
    }

    @Test
    public void testTranspose(){
        int[][] test1 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] result = Pieces.transpose(test1);
        assertTrue("testFirstRow",result[0][0] == 1 && result[0][1] == 4 && result[0][2] == 7);
        assertTrue("testSecondRow",result[1][0] == 2 && result[1][1] == 5 && result[1][2] == 8);
        assertTrue("testThirdRow",result[2][0] == 3 && result[2][1] == 6 && result[2][2] == 9);
    }

    @Test
    public void testRotate(){
        int[][] test1 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] result = StepsGame.rotate(test1);
        assertTrue("testFirstRow",result[0][0] == 7 && result[0][2] == 1 && result[0][1] == 4);
        assertTrue("testSecondRow",result[2][2] == 3 && result[2][0] == 9 && result[2][1] == 6);
        assertTrue("testThirdRow",result[1][0] == 8 && result[1][1] == 5 && result[1][2] == 2);
    }

    @Test
    public void testReverse(){
        int[][] test2 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] result = StepsGame.reverse(test2);
        assertTrue("testFirstRow",result[0][0] == 3 && result[0][2] == 1 && result[0][1] == 2);
        assertTrue("testSecondRow",result[2][2] == 7 && result[2][0] == 9 && result[2][1] == 8);
        assertTrue("testThirdRow",result[1][0] == 6 && result[1][1] == 5 && result[1][2] == 4);
    }

    @Test
    public void testUsedPos() {
        String[] test = {"BGS", "DAi", "FHn"};
// The executed outcomes:
        Set<Integer> result_1 = Pieces.usedPos(test[0]);
        Set<Integer> result_2 = Pieces.usedPos(test[1]);
        Set<Integer> result_3 = Pieces.usedPos(test[2]);
// The expected outcomes:
        Set<Integer> expected_1 = new HashSet<Integer>(5){{
            add(18);add(19);add(8);add(9);add(28);
        }};
        Set<Integer> expected_2 = new HashSet<Integer>(5){{
            add(32);add(33);add(23);add(43);add(44);
        }};
        Set<Integer> expected_3 = new HashSet<Integer>(5){{
            add(48);add(38);add(39);add(29);add(47);
        }};
// Test 3 examples:
        assertTrue("testFirst",result_1.equals(expected_1));
        assertTrue("testSecond",result_2.equals(expected_2));
        assertTrue("testThird",result_3.equals(expected_3));
    }

}
