/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import yahtzee.gamedynamics.Player;
/**
 *
 * @author Gabriel.Maxfield
 */
public class Game {
    List<Player> players = new ArrayList<>();
    int index = 0;
    int turn = 1;
    public Player nextPlayer(){
        Player player = players.get(index);
        index++;
        if(index == players.size()-1) turn++;
        if(index >= players.size()){
            index = 0;
        }
    return player;
    }
    
    public void add(Player player){
        players.add(player);
    }
    
    public void addPlayer(String name){//TODO Sort players!!!
        players.add(new Player(name));
    }
    
    public int getTurn(){
        return turn;
    }

    public Map getScores() {
        Map<String,Integer> scoreMap = new HashMap<>();
        for(Player player : players){
            scoreMap.put(player.getName(), player.totalScore());
        }
        return scoreMap;
    }
}
