package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Random;

import static comp1110.ass2.TestUtility.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test objective:
 *
 * Determine whether a placement sequence is valid.  To be valid, the placement sequence must
 * be well-formed and each piece must be a valid placement, when played in the order they
 * appear in the string.
 *
 */
public class PlacementSequenceValidTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    @Test
    public void testInvalidPiece() {
        Random r = new Random();
        for (int i = 0; i < BASE_ITERATIONS; i++) {
            String test = TestUtility.invalidPiecePlacement(r);
            assertFalse("Placement '" + test + "' is invalid, but passed.", StepsGame.isPlacementSequenceValid(test));
        }
    }

    @Test
    public void testGoodPairs() {
        for (int i = 0; i < GOOD_PAIRS.length; i++) {
            String test = GOOD_PAIRS[i];
            assertTrue("Placement '" + test + "' is valid, but was rejected.", StepsGame.isPlacementSequenceValid(test));
        }
    }

    @Test
    public void testBadPairs() {
        for (int i = 0; i < BAD_PAIRS.length; i++) {
            String test = BAD_PAIRS[i];
            assertFalse("Placement '" + test + "' is invalid, but passed.", StepsGame.isPlacementSequenceValid(test));
        }
    }

    @Test
    public void testGood() {
        for (int i = 0; i < PLACEMENTS.length; i++) {
            String p = TestUtility.shufflePlacement(PLACEMENTS[i]);
            for (int j = 0; j < p.length(); j += 3) {
                String test = p.substring(j, j + 3);
                assertTrue("Simple placement string '" + test + "', is valid but was rejected", StepsGame.isPlacementSequenceValid(test));
            }
        }
    }

    @Test
    public void testBad() {
        String placement = "AALBDxCEQDBgEHuFGSGEOHEc";
        assertFalse("Placement "+placement+" is bad but was passed.", StepsGame.isPlacementSequenceValid(placement));
    }

    @Test
    public void testDoubleUp() {
        Random r = new Random();
        for (int i = 0; i < PLACEMENTS.length; i++) {
            String p = PLACEMENTS[i];
            int dup = r.nextInt(8);
            int victim;
            do {
                victim = (dup + 1 + r.nextInt(7)) % 8;
            } while (maywork(p, dup, victim));

            String bad = p.substring(3 * dup, 1 + (3 * dup));
            String base = p.substring(0, 3 * victim) + bad + p.substring(1 + 3 * victim, p.length());
            String test = TestUtility.shufflePlacement(base);
            assertFalse("Placement '" + test + "' uses peg '" + bad + "' twice, but passed.", StepsGame.isPlacementSequenceValid(test));
        }
    }

    private boolean maywork(String p, int d, int v) {
        char a = p.charAt(1+d*3);
        char b = p.charAt(1+v*3);
        if (a == 'A' || a == 'G' || a == 'K' || a == 'L') return (b == 'I' || b == 'J');
        if (b == 'A' || b == 'G' || b == 'K' || b == 'L') return (a == 'I' || a == 'J');
        return false;
    }
}
