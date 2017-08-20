package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Random;

import static comp1110.ass2.TestUtility.BASE_ITERATIONS;
import static comp1110.ass2.TestUtility.PLACEMENTS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test objective:
 *
 * Determine whether a placement string is well-formed:
 *  - it consists of exactly N three-character piece placements (where N = 1 .. 8);
 *  - each piece placement is well-formed
 *  - no piece appears more than once in the placement
 */
public class PlacementWellFormedTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    @Test
    public void testEmpty() {
        assertFalse("Null placement string is not OK, but passed", StepsGame.isPlacementWellFormed(null));
        assertFalse("Empty placement string is not OK, but passed", StepsGame.isPlacementWellFormed(""));
    }

    @Test
    public void testIncomplete() {
        String incomplete = PLACEMENTS[0].substring(0,23);
        assertFalse("Placement string '"+incomplete+"'was incomplete, but passed", StepsGame.isPlacementWellFormed(incomplete));
    }

    @Test
    public void testGood() {
        Random r = new Random();

        for (int i = 0; i < PLACEMENTS.length; i++) {
            String p = TestUtility.shufflePlacement(PLACEMENTS[i]);
            for (int j = 0; j < BASE_ITERATIONS / 4; j++) {
                int start = r.nextInt(4);
                int end = start + r.nextInt(7 - start) + 1;
                String test = p.substring(3*start, 3*end);
                assertTrue("Placement '" + test + "' is well-formed, but failed ", StepsGame.isPlacementWellFormed(test));
            }
        }
    }

    @Test
    public void testBad() {
        Random r = new Random();
        for (int i = 0; i < BASE_ITERATIONS; i++) {
            String test = TestUtility.badlyFormedPiecePlacement(r);
            assertFalse("Placement '" + test + "' is badly formed, but passed", StepsGame.isPlacementWellFormed(test));
        }
    }

    @Test
    public void testDuplicate() {
        Random r = new Random();

        for (int i = 0; i < PLACEMENTS.length; i++) {
            String p = PLACEMENTS[i];
            int dup = r.nextInt(8);
            int victim = (dup + 1 + r.nextInt(7)) % 8;

            String bad = p.substring(3 * dup, 3 * (dup+1));
            String base = p.substring(0, 3 * victim) + bad + p.substring(3 * (victim+1), p.length());
            String test = TestUtility.shufflePlacement(base);
            assertFalse("Placement '" + test + "' uses piece '" + bad + "' twice, but passed.", StepsGame.isPlacementWellFormed(test));
        }
    }
}

