package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

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

        assertFalse("Obstruct next piece "+"FDN"+" passed",StepsGame.notObstruct("GBL","FDN"));
        assertTrue(StepsGame.notObstruct("","FDN"));
        assertTrue(StepsGame.notObstruct("","EEf"));

        assertTrue("Not obstruct example: ",StepsGame.notObstruct("EEfCHSAHQFDNGBLDAiHFl","BDx"));
    }

    @Test
    public void testValidOrders(){
        String key = "BGKFCNCFl";
        ArrayList<String> cands = new ArrayList<>();
        cands.add("AFn");cands.add("HHS");cands.add("GAi");cands.add("ECP");cands.add("DBg");
        Map<String,ArrayList<String>> starter = new HashMap<>();
        starter.put(key,cands);

        Set<String> orders = StepsGame.validOrders(starter);

        assertTrue(orders.contains("BGKFCNCFlGAiAFnHHSDBgECP"));
        assertTrue(orders.contains("BGKFCNCFlGAiAFnDBgHHSECP"));
        assertTrue(orders.contains("BGKFCNCFlGAiDBgAFnHHSECP"));

        assertFalse(orders.contains(""));
        assertFalse(orders.contains("ECPBGKFCNCFlGAiDBgAFnHHS"));
        assertFalse(orders.contains("ECPBGKFCNCFlDBgAFnHHSGAi"));

    }


}
