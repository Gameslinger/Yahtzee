/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee.gui;

import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import yahtzee.gamedynamics.Roll;

/**
 *
 * @author Gabriel.Maxfield
 */
public class DiceCanvas{

        private static final int DOT_SCALER = 4;//.25 of dice size
        private static final int SPACING = 10;//.1 of dice size
        private Canvas canvas;
        private boolean[] selectedDice = new boolean[5];
        private Roll roll = new Roll();
        
        int startX, startY, size;
        public DiceCanvas(Canvas canvas, int startX, int startY, int size) {
            this.canvas = canvas;
            this.startX = startX;
            this.startY = startY;
            this.size = size;
            
            canvas.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    selectDieAt((int)event.getX(),(int)event.getY());
                }
            
            });
        }

        private void drawDie(int roll, boolean used, boolean selected, int startX, int startY, int size) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            //gc.setFill(Color.WHITE);
            //gc.fill();
            //Draw background
            if (used) {//Grey out die if used...
                gc.setFill(Color.GREY);
            } else if(selected) {
                gc.setFill(Color.RED);
            }else{
                gc.setFill(Color.WHITE);
            }
            gc.fillRect(startX, startY, size, size);
            gc.setFill(Color.BLACK);
            gc.strokeRoundRect(startX, startY, size, size, size / 6, size / 6);
            switch (roll) {
                case 1:
                    drawDot(startX + size / 2, startY + size / 2, size / DOT_SCALER);
                    break;
                case 2:
                    drawDot(startX + size * 3 / 4, startY + size * 3 / 4, size / DOT_SCALER);
                    drawDot(startX + size / 4, startY + size / 4, size / DOT_SCALER);
                    break;
                case 3:
                    drawDot(startX + size / 2, startY + size / 2, size / DOT_SCALER);
                    drawDot(startX + size * 3 / 4, startY + size * 3 / 4, size / DOT_SCALER);
                    drawDot(startX + size / 4, startY + size / 4, size / DOT_SCALER);
                    break;
                case 4:
                    drawDot(startX + size * 3 / 4, startY + size * 3 / 4, size / DOT_SCALER);
                    drawDot(startX + size / 4, startY + size / 4, size / DOT_SCALER);
                    drawDot(startX + size * 3 / 4, startY + size / 4, size / DOT_SCALER);
                    drawDot(startX + size / 4, startY + size * 3 / 4, size / DOT_SCALER);
                    break;
                case 5:
                    drawDot(startX + size / 2, startY + size / 2, size / DOT_SCALER);
                    drawDot(startX + size * 3 / 4, startY + size * 3 / 4, size / DOT_SCALER);
                    drawDot(startX + size / 4, startY + size / 4, size / DOT_SCALER);
                    drawDot(startX + size * 3 / 4, startY + size / 4, size / DOT_SCALER);
                    drawDot(startX + size / 4, startY + size * 3 / 4, size / DOT_SCALER);
                    break;
                case 6:
                    drawDot(startX + size * 3 / 4, startY + size * 3 / 4, size / DOT_SCALER);
                    drawDot(startX + size / 4, startY + size / 4, size / DOT_SCALER);
                    drawDot(startX + size * 3 / 4, startY + size / 4, size / DOT_SCALER);
                    drawDot(startX + size / 4, startY + size * 3 / 4, size / DOT_SCALER);

                    drawDot(startX + size / 4, startY + size / 2, size / DOT_SCALER);
                    drawDot(startX + size * 3 / 4, startY + size / 2, size / DOT_SCALER);
            }
        }

        private void drawDot(int startX, int startY, int size) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.fillOval(startX - size / 2, startY - size / 2, size, size);
        }

        public void drawDice(int rolls[], boolean used[]) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.WHITE);
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            
            int spacing = size / SPACING;
            for (int die = 0; die < 5; die++) {
                drawDie(rolls[die], used[die], selectedDice[die], startX + (size + spacing) * die, startY, size);
            }
        }
        
        public void selectDieAt(int x, int y){
            int spacing = size / SPACING;
            for(int die = 1; die<6; die++){
                if(startX + (size + spacing) * die > x && x < (startX + (size + spacing) * die)+size && y > startY && y < startY+size){
                    if(roll.getUsed()[die-1]) return;//Why offset by one?
                    selectedDice[die-1] = !selectedDice[die-1];
                    drawDice(roll.getRolls(), roll.getUsed());
                    return;
                }
            }
        }
        
        public List<Integer> getSelecedDice(){
            List<Integer> places = new ArrayList<>();
            for(int i = 0; i < 5; i++){
                if(selectedDice[i]){
                    places.add(i+1);
                }
            }
            drawDice(roll.getRolls(), roll.getUsed());
            return places;
        }
        
        public void fullRoll(){
            roll.fullRoll();
            for(int i = 0; i < 5; i++){
                selectedDice[i] = false;
            }
            drawDice(roll.getRolls(), roll.getUsed());
        }
        
        public void reroll(){
            List<Integer> places = new ArrayList<>();
            for(int i = 0; i < 5; i++){
                if(selectedDice[i]) places.add(i);
                selectedDice[i] = false;
            }
            roll.reroll(places);
            drawDice(roll.getRolls(), roll.getUsed());
        }

        public int[] getRolls(){
            return roll.getRolls();
        }
}
