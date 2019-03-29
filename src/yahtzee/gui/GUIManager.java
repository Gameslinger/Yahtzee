/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee.gui;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import yahtzee.gamedynamics.Player;

/**
 *
 * @author Gabriel.Maxfield
 */
public class GUIManager {
    Label nameLabel;
    Label scoreLabel;
    Button score;
    ComboBox scoreSelect;
    DiceCanvas diceCanvas;

    public GUIManager(Label nameLabel, Label scoreLabel, ComboBox scoreSelect, Button score, DiceCanvas diceCanvas) {
        this.nameLabel = nameLabel;
        this.scoreLabel = scoreLabel;
        this.scoreSelect = scoreSelect;
        this.score = score;
        this.diceCanvas = diceCanvas;
    }
    
    public void setPlayer(Player player){
        this.nameLabel.setText(player.getName());
        this.scoreLabel.setText(""+player.getScore());
    }
}
