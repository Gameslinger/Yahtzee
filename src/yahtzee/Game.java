/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee;

import java.util.ArrayList;
import java.util.List;
import yahtzee.gamedynamics.Player;

/**
 *
 * @author Gabriel.Maxfield
 */
public class Game {
    
    public Game(){
        
    }
    List<Player> players = new ArrayList<>();
    public void addPlayer(String name){//TODO Sort players!!!
        players.add(new Player(name));
    }
    public void play(){
        for(int i = 0; i < 13; i++){
            for(Player player : players){
                player.doTurn();
            }
        }
        Player highestScorer = players.get(0);
        for(Player plr : players){//TODO: What to do in tie?
            if(plr.getScore() > highestScorer.getScore()){
                highestScorer = plr;
            }
        }
        System.out.println(highestScorer.getName()+" wins with"+highestScorer.getScore()+"!");
    }
}
