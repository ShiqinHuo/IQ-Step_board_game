package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Random;
import java.util.Set;

import static comp1110.ass2.TestUtility.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test objective:
 *
 * Determine whether the game can identify viable next moves
 */
public class ViablePiecePlacementTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    @Test
    public void testEmpty() {
        for (int i = 0; i < PLACEMENTS.length; i++) {
            Set<String> placements = StepsGame.getViablePiecePlacements(PLACEMENTS[i],PLACEMENTS[i]);
            assertTrue("Expected no viable placements, but got some: "+placements, placements.size() == 0);
        }
    }


    private void testOneOne(int i) {
        Set<String> placements = StepsGame.getViablePiecePlacements(VIABLE1[i][1],VIABLE1[i][0]);
        assertTrue("Expected one viable placement for '"+VIABLE1[i][1]+"', '"+VIABLE1[i][0]+"' but got: "+placements, placements.contains(VIABLE1[i][2]));
    }

    @Test
    public void testOne() {
        for (int i = 0; i < VIABLE1.length; i++)
            testOneOne(i);
    }

    private void testOneTwo(int i) {
        Set<String> placements = StepsGame.getViablePiecePlacements(VIABLE2[i][1],VIABLE2[i][0]);
        assertTrue("Expected two viable placements for '"+VIABLE2[i][1]+"', '"+VIABLE2[i][0]+"' but got: "+placements.size(), placements.size() == 2);
        assertTrue("Expected '"+VIABLE2[i][2]+"' to be a viable placement for '"+VIABLE2[i][1]+"', '"+VIABLE2[i][0]+"' but it was not", placements.contains(VIABLE2[i][2]));
        assertTrue("Expected '"+VIABLE2[i][3]+"' to be a viable placement for '"+VIABLE2[i][1]+"', '"+VIABLE2[i][0]+"' but it was not", placements.contains(VIABLE2[i][3]));

    }
    @Test
    public void testTwo() {
        for (int i = 0; i < VIABLE2.length; i++)
            testOneTwo(i);
    }

    private void testOneThree(int i) {
        Set<String> placements = StepsGame.getViablePiecePlacements(VIABLE3[i][1],VIABLE3[i][0]);
        assertTrue("Expected three viable placements for '"+VIABLE3[i][1]+"', '"+VIABLE3[i][0]+"' but got: "+placements.size(), placements.size() == 3);
        assertTrue("Expected '"+VIABLE3[i][2]+"' to be a viable placement for '"+VIABLE3[i][1]+"', '"+VIABLE3[i][0]+"' but it was not", placements.contains(VIABLE3[i][2]));
        assertTrue("Expected '"+VIABLE3[i][3]+"' to be a viable placement for '"+VIABLE3[i][1]+"', '"+VIABLE3[i][0]+"' but it was not", placements.contains(VIABLE3[i][3]));
        assertTrue("Expected '"+VIABLE3[i][4]+"' to be a viable placement for '"+VIABLE3[i][1]+"', '"+VIABLE3[i][0]+"' but it was not", placements.contains(VIABLE3[i][4]));
    }

    @Test
    public void testThree() {
        for (int i = 0; i < VIABLE3.length; i++)
            testOneThree(i);
    }

    @Test
    public void testFirst() {
        testOneOne(0);
        testOneTwo(0);
        testOneThree(0);
    }

}

