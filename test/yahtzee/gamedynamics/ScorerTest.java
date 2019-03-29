/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee.gamedynamics;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import yahtzee.gamedynamics.Scorer.EScore;

/**
 *
 * @author Gabriel.Maxfield
 */
public class ScorerTest {
    
    public ScorerTest() {
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
     * Test of score method, of class Scorer.
     */
    @Test
    public void testScore() {
        System.out.println("score");
        EScore score = EScore.CHANCE;
        int[] rolls = {1,4,5,2,6};
        Scorer instance = new Scorer();
        boolean result = instance.score(score, rolls);
        assertEquals(true, result);
        result = instance.score(score, rolls);
        assertEquals(false, result);
    }

    /**
     * Test of xOfKind method, of class Scorer.
     */
    @Test
    public void testXOfKind() {
        System.out.println("xOfKind");
        int[] rollsThree = {2,2,2,3,4};
        Scorer instance = new Scorer();
        int expResult = 13;
        instance.xOfKind(3, rollsThree);
        assertEquals(expResult, instance.getScore());
        int[] rollsFour = {2,4,4,4,4};
        expResult += 18;
        instance.xOfKind(4, rollsFour);
        assertEquals(expResult, instance.getScore());
    }
        @Test
    public void testUpper() {
        System.out.println("upper");
        int num = 2;
        int[] rolls = {2,2,5,2,4};
        Scorer instance = new Scorer();
        int expResult = 6;
        instance.upper(num, rolls);
        assertEquals(expResult, instance.getScore());
    }

    /**
     * Test of straight method, of class Scorer.
     */
    @Test
    public void testStraight() {
        System.out.println("straight");
        int[] rollsShort = {6,3,4,5,1};
        Scorer instance = new Scorer();
        int expResult = 30;
        instance.straight(true, rollsShort);
        assertEquals(expResult, instance.getScore());
        int[] rollsLong = {6,3,4,5,2};
        instance.straight(false,rollsLong);
        expResult += 40;
        assertEquals(expResult, instance.getScore());
        int[] rollsNone = {2,4,3,3,6};
        instance.straight(true,rollsNone);
        assertEquals(expResult, instance.getScore());
        
    }

    /**
     * Test of fullHouse method, of class Scorer.
     */
    @Test
    public void testFullHouse() {
        System.out.println("fullHouse");
        int[] rolls1 = {2,2,5,5,5};
        int expResult = 25;
        Scorer instance = new Scorer();
        instance.fullHouse(rolls1);
        assertEquals(expResult,instance.getScore());
        expResult +=25;
        int rolls2[] = {2,2,2,5,5};
        instance.fullHouse(rolls2);
        assertEquals(expResult,instance.getScore());
        int rolls3[] = {1,2,4,2,3};
        instance.fullHouse(rolls3);
        assertEquals(expResult,instance.getScore());
    }

    /**
     * Test of chance method, of class Scorer.
     */
    @Test
    public void testChance() {
        System.out.println("chance");
        int[] rolls = {2,5,3,1,4};
        int expResult = 0;
        for(int roll : rolls){
            expResult +=roll;
        }
        Scorer instance = new Scorer();
        instance.chance(rolls);
        assertEquals(expResult,instance.getScore());
        
    }

    /**
     * Test of yahtzee method, of class Scorer.
     */
    @Test
    public void testYahtzee() {
        System.out.println("yahtzee");
        int[] rolls = {1,1,1,1,1};
        int expResult = 50;
        Scorer instance = new Scorer();
        instance.yahtzee(rolls);
        assertEquals(expResult,instance.getScore());
        
        int rolls2[] = {4,4,4,4,4};
        expResult+=100;
        instance.yahtzee(rolls2);
        assertEquals(expResult,instance.getScore());
        
        int rolls3[] = {3,5,3,3,3};
        instance.yahtzee(rolls3);
        assertEquals(expResult,instance.getScore());
    }
}
