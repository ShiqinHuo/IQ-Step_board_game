package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.HashSet;
import java.util.Set;

import static comp1110.ass2.TestUtility.SOLUTIONS_MULTI;
import static comp1110.ass2.TestUtility.SOLUTIONS_ONE;
import static org.junit.Assert.assertTrue;

/**
 * Test objective:
 *
 * Return an array of all solutions given a starting placement.
 */
public class SolutionsTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(120);


    private void testSingle(String placement, String reference) {
        String[] result = StepsGame.getSolutions(placement);
        assertTrue("Placement '"+placement+"' has solution '"+reference+"', but you returned a null array", result != null);
        assertTrue("Placement '"+placement+"' has only one solution, but you provided "+result.length+" solutions", result.length == 1);
        assertTrue("Placement '"+placement+"' has solution '"+reference+"', but you returned a null string", result[0] != null);
        String normresult = TestUtility.normalize(result[0]);
        String normref = TestUtility.normalize(reference);
        assertTrue("Placement '"+placement+"' has solution '"+reference+"' ('"+normref+"'), but you provided '"+result[0]+"' ('"+normresult+"')",normref.equals(normresult));
    }

    @Test
    public void testSimple() {
        for (int i = 0; i < SOLUTIONS_ONE.length; i++) {
            String placement = SOLUTIONS_ONE[i][1].substring(0,SOLUTIONS_ONE[i][1].length()-3); // task is to solve for last move, so use solution, less one move
            String reference = SOLUTIONS_ONE[i][1];
            testSingle(placement, reference);
        }
    }

    @Test
    public void testSingle() {
        for (int i = 0; i < SOLUTIONS_ONE.length; i++) {
            String placement = SOLUTIONS_ONE[i][0];
            String reference = SOLUTIONS_ONE[i][1];
            testSingle(placement, reference);
        }
    }

    @Test
    public void testMulti() {
        for (int i = 0; i < SOLUTIONS_MULTI.length; i++) {
            String test = SOLUTIONS_MULTI[i][0];
            int sols = SOLUTIONS_MULTI[i].length - 1;
            String[] result = StepsGame.getSolutions(test);

            assertTrue("Placement '"+test+"' has solutions, but you returned a null array", result != null);
            assertTrue("Placement '"+test+"' has "+sols+" solutions, but you provided "+result.length+" solutions", result.length == sols);
            Set<String> solset = new HashSet<>();
            for (int j = 1; j <= sols; j++) {
                solset.add(TestUtility.normalize(SOLUTIONS_MULTI[i][j]));
            }

            String mismatch = "";
            for (int j = 0; j < result.length; j++) {
                if (!solset.contains(TestUtility.normalize(result[j]))) mismatch += " "+result[j]+" ("+ TestUtility.normalize(result[j])+")";
            }
            assertTrue("Placement '"+test+"' has solution '"+solset+"', but you solution included the following: "+mismatch, mismatch.equals(""));
        }
    }
}
