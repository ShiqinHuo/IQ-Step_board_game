package comp1110.ass2;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by wenjunyang on 25/9/17.
 */
public class RotateAndReverseTest {
    @Test
    public void testRotate(){
        int[][] test1 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] result = StepsGame.rotate(test1);
        assertTrue("test1-0",result[0][0] == 7 && result[0][2] == 1 && result[0][1] == 4);
        assertTrue("test1-2",result[2][2] == 3 && result[2][0] == 9 && result[2][1] == 6);
        assertTrue("test1-1",result[1][0] == 8 && result[1][1] == 5 && result[1][2] == 2);
    }

    @Test
    public void testReverse(){
        int[][] test2 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] result = StepsGame.reverse(test2);
        assertTrue("test2-0",result[0][0] == 3 && result[0][2] == 1 && result[0][1] == 2);
        assertTrue("test2-2",result[2][2] == 7 && result[2][0] == 9 && result[2][1] == 8);
        assertTrue("test2-1",result[1][0] == 6 && result[1][1] == 5 && result[1][2] == 4);
    }
}
