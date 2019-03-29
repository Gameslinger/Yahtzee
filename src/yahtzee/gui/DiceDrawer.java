/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author Gabriel.Maxfield
 */
public abstract class DiceDrawer {
    private static final int DOT_SCALER = 4;//.25 of dice size
    private static final int SPACING = 10;//.1 of dice size
    
    public static void drawDie(Canvas canvas, int roll, boolean used, int startX, int startY, int size){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //gc.setFill(Color.WHITE);
        //gc.fill();
        //Draw background
        if(used){//Grey out die if used...
            gc.setFill(Color.GREY);
            gc.fillRect(startX, startY, size, size);
        }
        gc.setFill(Color.BLACK);
        gc.strokeRoundRect(startX, startY, size, size,size/6,size/6);
        switch(roll){
            case 1:
                drawDot(canvas, startX+size/2, startY+size/2,size/DOT_SCALER);
                break;
            case 2:
                drawDot(canvas,startX+size*3/4, startY+size*3/4, size/DOT_SCALER);
                drawDot(canvas,startX+size/4, startY+size/4, size/DOT_SCALER);
                break;
            case 3:
                drawDot(canvas, startX+size/2, startY+size/2,size/DOT_SCALER);
                drawDot(canvas,startX+size*3/4, startY+size*3/4, size/DOT_SCALER);
                drawDot(canvas,startX+size/4, startY+size/4, size/DOT_SCALER);
                break;
            case 4:
                drawDot(canvas,startX+size*3/4, startY+size*3/4, size/DOT_SCALER);
                drawDot(canvas,startX+size/4, startY+size/4, size/DOT_SCALER);
                drawDot(canvas,startX+size*3/4, startY+size/4, size/DOT_SCALER);
                drawDot(canvas,startX+size/4, startY+size*3/4, size/DOT_SCALER);
                break;
            case 5:
                drawDot(canvas, startX+size/2, startY+size/2,size/DOT_SCALER);
                drawDot(canvas,startX+size*3/4, startY+size*3/4, size/DOT_SCALER);
                drawDot(canvas,startX+size/4, startY+size/4, size/DOT_SCALER);
                drawDot(canvas,startX+size*3/4, startY+size/4, size/DOT_SCALER);
                drawDot(canvas,startX+size/4, startY+size*3/4, size/DOT_SCALER);
                break;
            case 6:
                drawDot(canvas,startX+size*3/4, startY+size*3/4, size/DOT_SCALER);
                drawDot(canvas,startX+size/4, startY+size/4, size/DOT_SCALER);
                drawDot(canvas,startX+size*3/4, startY+size/4, size/DOT_SCALER);
                drawDot(canvas,startX+size/4, startY+size*3/4, size/DOT_SCALER);
                
                drawDot(canvas,startX+size/4, startY+size/2, size/DOT_SCALER);
                drawDot(canvas,startX+size*3/4, startY+size/2, size/DOT_SCALER);
               }
    }
    
    private static void drawDot(Canvas canvas, int startX, int startY, int size){
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.fillOval(startX-size/2, startY-size/2, size, size);
    }
    
    public static void drawDice(Canvas canvas, int rolls[], boolean used[], int startX, int startY, int size){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int spacing = size/SPACING;
        for(int die = 0; die < 5; die++){
            drawDie(canvas, rolls[die], used[die], startX+(size+spacing)*die, startY, size);
        }
    }
}
