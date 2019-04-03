/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee.gamedynamics;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gabriel.Maxfield
 */
public class RollTest {
    
    public RollTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRollable method, of class Roll.
     */
    @Test
    public void testGetRollable() {
        System.out.println("getRollable");
        Roll instance = new Roll();
        List<Integer> rerolls = new ArrayList<>();
        rerolls.add(1);
        rerolls.add(2);
        rerolls.add(3);
        instance.reroll(rerolls);
        boolean[] exp = {true, true, true, false, false};
        assertArrayEquals(exp, instance.getUsed());
        rerolls.remove(2);
        exp[2] = false;
        instance.reroll(rerolls);
        assertArrayEquals(exp, instance.getUsed());
        
    }

    
}
