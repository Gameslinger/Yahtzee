/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee.gamedynamics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import yahtzee.gamedynamics.Scorer.EScore;

/**
 *
 * @author Gabriel.Maxfield
 */
public class Player {

    final static Scanner input = new Scanner(System.in);//Should this be moved?
    final Scorer scorer;
    final String name;

    public Player(String name) {
        this.scorer = new Scorer();
        this.name = name;
    }

/*    public void doTurn() {
//        //Roll
//        roll.fullRoll();
//        //Which dice to re-roll
//        System.out.println("\n"+this.name + "'s turn:");
//        List<Integer> rerolls = new ArrayList<>();
//
//        do {
//            System.out.print("You rolled: ");
//            for (int roll : roll.getRolls()) {
//                System.out.print(roll + " ");
//            }
//            System.out.println("\nWhat would you like to reroll?(By place or leave empty)");
//            rerolls.clear();
//            String rerollStr = input.nextLine();
//            if (rerollStr.trim().isEmpty()) {
//                break;
//            }
//            for (String numStr : rerollStr.split(" ")) {
//                try {
//                    rerolls.add(Integer.parseInt(numStr));
//                } catch (NumberFormatException ex) {
//                    //Improper input
//                }
//            }
//            if(!roll.reroll(rerolls)){
//                System.out.println("Invalid roll");
//            }
//        } while (true);
//
//        //Choose catagory
//        System.out.println("Available catagories: ");
//        List<EScore> unused = scorer.getUnused();
//        for (EScore cat : unused) {
//            System.out.print(cat.getTitle() + ", ");
//        }
//        boolean selected = false;
//        do {
//            System.out.println("What catagory do you want to score in?");
//            String catagory = input.nextLine();
//            for (EScore cat : unused) {
//                if (cat.getTitle().equalsIgnoreCase(catagory)) {
//                    scorer.score(cat, roll.getRolls());
//                    selected = true;
//                    break;
//                }
//            }
//        } while (!selected);
//        System.out.println("You now have a score of " + this.scorer.getScore());
    }
*/
    public int getScore() {
        return scorer.getScore();
    }

    public String getName() {
        return name;
    }

//    public void reroll(List<Integer> places) {
//        roll.reroll(places);
//    }
    
    public List<EScore> getScoringOptions(){
        return this.scorer.getUnused();//.stream().map((x)->x.toString()).collect(Collectors.toList());
    }

    public void score(EScore score,int[] rolls){
        this.scorer.score(score, rolls);
    }
    public int totalScore(){
        return this.scorer.totalScore();
    }
}
