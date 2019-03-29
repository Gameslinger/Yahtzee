/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee.gamedynamics;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Gabriel.Maxfield
 */
public class Roll {
    final Random random = new Random();
    int rolls[] = new int[5];
    int rollCount[] = new int[5];
    int timesRerolled = 0;
    public Roll() {
        fullRoll();
    }

    private int roll() {
        return random.nextInt(6) + 1;
    }
    
    public void fullRoll(){
        for (int i = 0; i < 5; i++) {
            rolls[i] = roll();
        }
        timesRerolled = 0;
        rollCount = new int[]{0, 0, 0, 0, 0};
    }
    
    public boolean reroll(List<Integer> places) {
        if(places.isEmpty()) return true;
        timesRerolled++;
        if(timesRerolled == 4) return false; //Can only roll 3 times
        for(int pos : places){
            if(pos > 5 || pos < 0) return false;
            if(rollCount[pos-1]!=timesRerolled-1) return false;//Cannot reroll dice if they weren't part of past roll.
        }
        
        for (int pos : places) {
            rolls[pos-1] = roll();
            rollCount[pos-1]++;
        }
        return true;
    }
    public boolean[] getRollable(){
        boolean rollable[] = new boolean[5];
        int currentRoll = 0;
        for(int count : rollCount){
            if(currentRoll < count) currentRoll = count;
        }
        for(int dice = 0; dice < 5; dice++){
            if(rollCount[dice] != currentRoll) rollable[dice] = false;
            else rollable[dice] = true;
        }
        return rollable;
    }
    public int[] getRolls() {
        return rolls;
    }

    public int[] getRollCount() {
        return rollCount;
    }

}
