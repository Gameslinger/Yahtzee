/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee.gamedynamics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gabriel.Maxfield
 */
public class Scorer {//TODO: Add 35 points for upper completion?

    private int score = 0;
    private int yahtzeeCount = 0;
    private int upperCount = 0;
    Map<EScore, Boolean> usedMap = new EnumMap<>(EScore.class);
    
    public Scorer(){
        for(EScore score : EScore.values()){
            usedMap.put(score, Boolean.FALSE);
        }
    }
    public List<EScore> getUnused() {
        List<EScore> unused = new ArrayList<>();
        for(EScore score : usedMap.keySet()){
            if(!usedMap.get(score)) unused.add(score);
        }
        return unused;
    }

    public enum EScore {
        UPPER_1("Upper 1"), UPPER_2("Upper 2"), UPPER_3("Upper 3"), UPPER_4("Upper 4"), UPPER_5("Upper 5"), UPPER_6("Upper 6"),
        THREE_OF_KIND("Three of a Kind"), FOUR_OF_KIND("Four of a Kind"), FULL_HOUSE("Full House"), SMALL_STRAIGHT("Small Straight"),
        LONG_STRAIGHT("Long Straight"), CHANCE("Chance"), YAHTZEE("Yahtzee");

        final private String title;

        public String getTitle() {
            return this.title;
        }

        private EScore(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return this.title;
        }
        
    }

    public boolean score(EScore score, int[] rolls) {
        if(usedMap.get(score)) return false;
        usedMap.put(score, Boolean.TRUE);
        switch (score) {
            case UPPER_1:
                upper(1,rolls);
                return true;
            case UPPER_2:
                upper(2,rolls);
                return true;
            case UPPER_3:
                upper(3,rolls);
                return true;
            case UPPER_4:
                upper(4,rolls);
                return true;
            case UPPER_5:
                upper(5,rolls);
                return true;
            case UPPER_6:
                upper(6,rolls);
                return true;
            case THREE_OF_KIND:
                xOfKind(3,rolls);
                return true;
            case FOUR_OF_KIND:
                xOfKind(4,rolls);
                return true;
            case FULL_HOUSE:
                fullHouse(rolls);
                return true;
            case SMALL_STRAIGHT:
                straight(true, rolls);
                return true;
            case LONG_STRAIGHT:
                straight(false, rolls);
                return true;
            case CHANCE:
                chance(rolls);
                return true;
            case YAHTZEE:
                usedMap.put(score, Boolean.FALSE);
                return yahtzee(rolls);
        }
        return false;
    }

    public int getScore() {
        return score;
    }

    private void addScore(int total) {
        this.score += total;
    }
    public int totalScore(){
        if(upperCount >= 63) score+=35;
        return score;
    }

    public void upper(int num, int rolls[]) {
        if (num > 6 && num < 1) {
            return;//Should not be happening
        }
        int total = 0;
        for (int roll : rolls) {
            if (roll == num) {
                total += roll;
            }
        }
        //usedUpper[num - 1] = false;
        this.upperCount+=total;
        this.addScore(total);
    }

    public void straight(boolean small, int rolls[]) {
        int longestStraight = 0;
        int currentStraight = 1;
        Arrays.sort(rolls);
        for (int i = 1; i < rolls.length; i++) {
       //     System.out.print(rolls[i] + " Vs " + rolls[i - 1] + ": ");
            if (rolls[i] - 1 == rolls[i - 1]) {
                currentStraight++;
         //       System.out.println("Continue Straight, " + currentStraight);
            } else if (rolls[i] == rolls[i - 1]) {
        //        System.out.println("Dupe Cont Straight, " + currentStraight);
            } else {
         //       System.out.println("Straight end, " + currentStraight);
                if (currentStraight > longestStraight) {
         //           System.out.println("Assigning Straight");
                    longestStraight = currentStraight;
                    currentStraight = 1;
                }
            }

        }
        if (longestStraight < currentStraight) {
         //   System.out.println("Assigning Straight");
            longestStraight = currentStraight;
        }
        //System.out.println("Longest straight: " + longestStraight);
        if (longestStraight >= 4 && small) {
            this.addScore(30);
        } else if (longestStraight == 5 && !small) {
            this.addScore(40);
        }
    }

    public void fullHouse(int rolls[]) {
        Arrays.sort(rolls);
        int prevStraight = 0;
        int currentStraight = 0;
        if ((rolls[0] == rolls[1]
                && (rolls[2] == rolls[3] && rolls[2] == rolls[4]))
                || (rolls[0] == rolls[1] && rolls[0] == rolls[2] && (rolls[3] == rolls[4]))) {
            this.addScore(25);
        }
    }

    public void chance(int rolls[]) {
        int total = 0;
        for (int roll : rolls) {
            total += roll;
        }
        this.addScore(total);
    }

    public boolean yahtzee(int rolls[]) {
        for (int roll : rolls) {
            if (rolls[0] != roll) {
                return false;
            }
        }
        yahtzeeCount++;
        if (yahtzeeCount == 1) {
            this.addScore(50);
        } else {
            this.addScore(100);
        }
        return true;
    }
    
    public void xOfKind(int minCount, int rolls[]){
        Arrays.sort(rolls);
        int times = 0;
        int mostTimes = 0;
        int current = rolls[0];
        for(int roll : rolls){
        //    System.out.print(current+" vs "+roll+": ");
            if(current == roll){
       //         System.out.println("Match");
                times++;
            }else{
        //        System.out.println("Does not match");
                if(mostTimes < times){
                    mostTimes = times;
                }
                times = 1;
                current = roll;
            }
        }
        if(mostTimes < times){
                    mostTimes = times;
                }
       // System.out.println("Longest of kind is: "+mostTimes);
        if(mostTimes >= minCount){//Add up totals
            chance(rolls);
        }
    }
}
