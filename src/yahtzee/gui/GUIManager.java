/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee.gui;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import yahtzee.Game;
import yahtzee.ScreenManager;
import yahtzee.gamedynamics.Player;
import yahtzee.gamedynamics.Scorer.EScore;

/**
 *
 * @author Gabriel.Maxfield
 */
public class GUIManager {
    Label nameLabel;
    Label scoreLabel;
    Button score, reroll;
    ComboBox scoreSelect;
    DiceCanvas diceCanvas;
    Player player;
    Game game;
    ScreenManager screenM;
    
    int offsetX = 10, offsetY = 10, size = 50;
    
    public GUIManager(ScreenManager screenM, Label nameLabel, Label scoreLabel, ComboBox scoreSelect, Button score, Button reroll, DiceCanvas diceCanvas, Game game) {
        this.nameLabel = nameLabel;
        this.scoreLabel = scoreLabel;
        this.scoreSelect = scoreSelect;
        this.score = score;
        this.reroll = reroll;
        this.diceCanvas = diceCanvas;
        this.game = game;
        this.screenM = screenM;
        score.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                EScore score = (EScore) scoreSelect.getValue();
                if(score!=null){
                    if(player.score(score, diceCanvas.getRolls())){
                    setPlayer(game.nextPlayer());
                    if(game.getTurn() == 14){
                        screenM.endGame(game.getScores());
                    }
                }
            }
            }
        });
        
        reroll.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                diceCanvas.reroll();
            }
            
        });
        
    }
    
    public void setPlayer(Player player){
        this.player = player;
        this.nameLabel.setText(player.getName());
        this.scoreLabel.setText("Score: "+player.getScore());
        scoreSelect.getItems().clear();
        scoreSelect.getItems().addAll(player.getScoringOptions());
        diceCanvas.fullRoll();
    }
    
    
}
