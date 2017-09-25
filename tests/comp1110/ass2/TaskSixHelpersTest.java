package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by luoxi on 26/09/2017.
 */
public class TaskSixHelpersTest {

    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);





    @Test
    public void testGetCandidatesCount(){
        String objective = "EEfCHSAHQFDNGBLDAiHFlBDx";
        String empty = "";
        assertTrue("test empty placement",StepsGame.getCandidates(empty,objective).size() == 8 );

        for (int i = 0; i < 8 ; i++) {
            String placement1 = objective.substring(0,i*3);
            assertTrue("test non-empty placement",StepsGame.getCandidates(placement1,objective).size() == 8-i );
        }
    }

    @Test
    public void testGetCandidatesMembers(){
        String objective = "EEfCHSAHQFDNGBLDAiHFlBDx";
        ArrayList<String> pss = new ArrayList<>();
        pss.add("EEf");
        pss.add("CHS");
        pss.add("AHQ");
        pss.add("FDN");
        pss.add("GBL");
        pss.add("DAi");
        pss.add("HFl");
        pss.add("BDx");
        String empty = "";
        assertTrue("test empty placement", StepsGame.getCandidates(empty,objective).containsAll(pss));

        for (int i = 0; i < 8 ; i++) {
            String placement = objective.substring(0,i*3);
            assertTrue("test non-empty placement", StepsGame.getCandidates(placement,objective).containsAll(pss.subList(i+1,8)));
        }

    }


    @Test
    public void testNotObstruct(){

    }

    @Test
    public void testValidOrders(){

    }


}
