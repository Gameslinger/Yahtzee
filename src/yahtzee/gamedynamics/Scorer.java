/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee.gamedynamics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Gabriel.Maxfield
 */
public class Scorer {//TODO: Add 35 points for upper completion?

    private int score = 0;
    private int yahtzeeCount = 0;
    //private boolean usedUpper[] = new boolean[6];

    public Scorer() {
        // for (int i = 0; i < usedUpper.length; i++) {
        //     usedUpper[i] = false;
        // }
    }

    public List<EScore> getUnused() {
        return EScore.getUnused();
    }

    public enum EScore {
        UPPER_1("Upper 1"), UPPER_2("Upper 2"), UPPER_3("Upper 3"), UPPER_4("Upper 4"), UPPER_5("Upper 5"), UPPER_6("Upper 6"),
        THREE_OF_KIND("Three of a Kind"), FOUR_OF_KIND("Four of a Kind"), FULL_HOUSE("Full House"), SMALL_STRAIGHT("Small Straight"),
        LONG_STRAIGHT("Long Straight"), CHANCE("Chance");

        private boolean used;
        final private String title;

        public boolean isUsed() {
            return this.used;
        }

        public void setUsed() {
            this.used = true;
        }

        public String getTitle() {
            return this.title;
        }

        private EScore(String title) {
            this.title = title;
            this.used = false;
        }
        public static List<EScore> getUnused(){
            List<EScore> unused = new ArrayList<>();
            for(EScore score : EScore.values()){
                if(!score.isUsed()) unused.add(score);
            }
            return unused;
        }
    }

    public boolean score(EScore score, int[] rolls) {
        switch (score) {
            case UPPER_1:
                if(EScore.UPPER_1.isUsed()) return false;
                EScore.UPPER_1.setUsed();
                upper(1,rolls);
                return true;
            case UPPER_2:
                if(EScore.UPPER_2.isUsed()) return false;
                EScore.UPPER_2.setUsed();
                upper(2,rolls);
                return true;
            case UPPER_3:
                if(EScore.UPPER_3.isUsed()) return false;
                EScore.UPPER_3.setUsed();
                upper(3,rolls);
                return true;
            case UPPER_4:
                if(EScore.UPPER_4.isUsed()) return false;
                EScore.UPPER_4.setUsed();
                upper(4,rolls);
                return true;
            case UPPER_5:
                if(EScore.UPPER_5.isUsed()) return false;
                EScore.UPPER_5.setUsed();
                upper(5,rolls);
                return true;
            case UPPER_6:
                if(EScore.UPPER_6.isUsed()) return false;
                EScore.UPPER_6.setUsed();
                upper(6,rolls);
                return true;
            case THREE_OF_KIND:
                if(EScore.THREE_OF_KIND.isUsed()) return false;
                EScore.THREE_OF_KIND.setUsed();
                xOfKind(3,rolls);
                return true;
            case FOUR_OF_KIND:
                if(EScore.FOUR_OF_KIND.isUsed()) return false;
                EScore.FOUR_OF_KIND.setUsed();
                xOfKind(4,rolls);
                return true;
            case FULL_HOUSE:
                if(EScore.FULL_HOUSE.isUsed()) return false;
                EScore.FULL_HOUSE.setUsed();
                fullHouse(rolls);
                return true;
            case SMALL_STRAIGHT:
                if(EScore.SMALL_STRAIGHT.isUsed()) return false;
                EScore.SMALL_STRAIGHT.setUsed();
                straight(true, rolls);
                return true;
            case LONG_STRAIGHT:
                if(EScore.LONG_STRAIGHT.isUsed()) return false;
                EScore.LONG_STRAIGHT.setUsed();
                straight(false, rolls);
                return true;
            case CHANCE:
                if(EScore.CHANCE.isUsed()) return false;
                EScore.CHANCE.setUsed();
                chance(rolls);
                return true;
        }
        return false;
    }

    public int getScore() {
        return score;
    }

    // public boolean[] getUsedUpper() {
    //     return usedUpper;
    // }
    private void addScore(int total) {
        this.score += total;
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

    public void yahtzee(int rolls[]) {
        for (int roll : rolls) {
            if (rolls[0] != roll) {
                return;
            }
        }
        yahtzeeCount++;
        if (yahtzeeCount == 1) {
            this.addScore(50);
        } else {
            this.addScore(100);
        }
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
