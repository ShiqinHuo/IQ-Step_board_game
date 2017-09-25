package comp1110.ass2;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
/**
 * Created by wenjunyang on 25/9/17.
 */
public class GetCoordinateTest {
    @Test
    public void testCorrectCoordinate(){
        assertTrue("test11",StepsGame.getCoordinate("CEQ").toString().equals("[6, 15, 16, 26, 27]"));
        assertTrue("test12",StepsGame.getCoordinate("EHu").toString().equals("[35, 36, 44, 45, 46]"));
        assertTrue("test13",StepsGame.getCoordinate("AAL").toString().equals("[0, 1, 10, 11, 12, 20]"));
    }

    @Test
    public void testWrongCoordinate(){
        assertFalse("test21",StepsGame.getCoordinate("EAo").toString().equals("[29, 38, 39, 48, 40]"));
        assertFalse("test22",StepsGame.getCoordinate("CAk").toString().equals("[25, 35, 36, 42, 45]"));
        assertFalse("test23",StepsGame.getCoordinate("HCi").toString().equals("[21, 23, 33, 34, 42, 43]"));
    }
}
