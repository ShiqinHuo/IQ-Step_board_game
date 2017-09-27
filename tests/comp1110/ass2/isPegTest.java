package comp1110.ass2;

/**
 * Created by DoubleHUO on 25/9/17.
 */

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static comp1110.ass2.Alphabet.isPeg;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class isPegTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    boolean ispeg = false; // flag boolean

    private static int[] pegset = {0, 2, 4, 6, 8, 11, 13, 15, 17, 19, 20, 22, 24,
            26, 28, 31, 33, 35, 37, 39, 40, 42, 44, 46, 48}; // correct position

//test 3 examples of peg positions
    @Test
    public void testGoodPeg(){
        int pos1 = 0;
        int pos2 = 19;
        int pos3 = 40;
        assertTrue("testGoodPeg1",isPeg(pos1));
        assertTrue("testGoodPeg2",isPeg(pos2));
        assertTrue("testGoodPeg3",isPeg(pos3));
    }

//test 3 examples of NOT peg positions
    @Test
    public void testBadPeg(){
        int pos1 = 5;
        int pos2 = 18;
        int pos3 = 23;
        assertFalse("testBadPeg1",isPeg(pos1));
        assertFalse("testBadPeg2",isPeg(pos2));
        assertFalse("testBadPeg3",isPeg(pos3));
    }
//test position of random integer (from 0 to 49)

//used an idea from a stackoverflow-question:
//https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
    int randomNum = (int) (Math.random() * 49);
    @Test
    public void testRandomPos() {
        for (int j = 0; j<pegset.length;j++){
            if (randomNum == pegset[j])
            {
                ispeg = true;
                System.out.println("The index in the pegset is" + j);
                break;}
        }
        System.out.println("The random number is" + randomNum);
        System.out.println("Whether the pos is peg:" + ispeg);
        assertTrue(ispeg==isPeg(randomNum));
        }

    }


