/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import yahtzee.gui.DiceCanvas;
import yahtzee.gui.DiceDrawer;

/**
 *
 * @author Gabriel.Maxfield
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Label currentPlayer = new Label();
        Label score = new Label();
        ComboBox scoreSelect = new ComboBox();
        Button scoreButton = new Button("Score");
        HBox scorerBox = new HBox();
        scorerBox.getChildren().add(scoreSelect);
        scorerBox.getChildren().add(scoreButton);
        DiceCanvas diceCanvas = new DiceCanvas(300,200);
        VBox vbox = new VBox();
        vbox.getChildren().add(currentPlayer);
        vbox.getChildren().add(score);
        vbox.getChildren().add(scorerBox);
        vbox.getChildren().add(diceCanvas);
        
        StackPane root = new StackPane();
        root.getChildren().add(vbox);
        
        Scene scene = new Scene(root, 500, 500);
        
        primaryStage.setTitle("Yahtzee!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        //Game game = new Game();
        //game.addPlayer("Gabe");
        //game.addPlayer("Not Gabe");
        //game.play();
    }
    
}
