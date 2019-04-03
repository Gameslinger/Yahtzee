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
    boolean used[] = new boolean[5];
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
        for(int i = 0; i < 5; i++){
            rollCount[i] = 0;
            used[i] = false;
        }
    }
    
    public void reroll(List<Integer> places) {
//        for(int i = 0; i < 5; i++){
//           used[i] = true;
//        }
        if(places.isEmpty() || timesRerolled > 3) return;
        for(int place : places){
            if(place > 4 || place < 0)continue;
            //if(rollCount[place] == timesRerolled){
                //used[place] = false;
                rolls[place] = roll();
                rollCount[place]++;
           // }
        }
        
        timesRerolled++;
        if(timesRerolled == 3){
        for(int i = 0; i < 5; i++){
           used[i] = true;
        }
        }
    }
    public boolean[] getUsed(){
        return used;
    }
    public int[] getRolls() {
        return rolls;
    }

    public int[] getRollCount() {
        return rollCount;
    }

}
