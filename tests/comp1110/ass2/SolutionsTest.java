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
    public Timeout globalTimeout = Timeout.seconds(40);

    @Test
    public void testSingle() {
        for (int i = 0; i < SOLUTIONS_ONE.length; i++) {
            String test = SOLUTIONS_ONE[i][0];
            String reference = SOLUTIONS_ONE[i][1];
            System.out.println("Testing:  "+test+" -> "+reference);
            String[] result = StepsGame.getSolutions(test);
            System.out.println("Done!");
            assertTrue("Placement '"+test+"' has solution '"+reference+"', but you returned a null array", result != null);
            assertTrue("Placement '"+test+"' has only one solution, but you provided "+result.length+" solutions", result.length == 1);
            assertTrue("Placement '"+test+"' has solution '"+reference+"', but you returned a null string", result[0] != null);
            String normal = TestUtility.normalize(result[0]);
            assertTrue("Placement '"+test+"' has solution '"+reference+"', but you provided '"+result[0]+"' ('"+normal+"')",reference.equals(normal));
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
                solset.add(SOLUTIONS_MULTI[i][j]);
            }

            String mismatch = "";
            for (int j = 0; j < result.length; j++) {
                if (!solset.contains(TestUtility.normalize(result[j]))) mismatch += " "+result[j]+" ("+ TestUtility.normalize(result[j])+")";
            }
            assertTrue("Placement '"+test+"' has solution '"+solset+"', but you solution included the following: "+mismatch, mismatch.equals(""));
        }
    }
}
