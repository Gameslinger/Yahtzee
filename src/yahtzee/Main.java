/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import yahtzee.gui.DiceCanvas;
import yahtzee.gui.GUIManager;

/**
 *
 * @author Gabriel.Maxfield
 */
public class Main extends Application implements ScreenManager{
    Stage stage;
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        Map<String, Integer> scores = new HashMap<>();
        putStartScreen();
        primaryStage.setTitle("Yahtzee!");
        primaryStage.show();
    }
    private void putGameScene(List<String> names){//TODO: add leaderboard?
        Label currentPlayer = new Label();
        Label score = new Label();
        Label rerollLabel = new Label("Which dice do you want to reroll:");
        ComboBox scoreSelect = new ComboBox();
        Button scoreButton = new Button("Score");
        Button rerollButton = new Button("Reroll");
        
        HBox scorerBox = new HBox();
        scorerBox.getChildren().add(scoreSelect);
        scorerBox.getChildren().add(scoreButton);
        Canvas canvas = new Canvas(280,50);
        DiceCanvas dice = new DiceCanvas(canvas,5,0,50);
        VBox vbox = new VBox();
        vbox.getChildren().add(currentPlayer);
        vbox.getChildren().add(score);
        vbox.getChildren().add(scorerBox);
        vbox.getChildren().add(rerollLabel);
        vbox.getChildren().add(canvas);
        vbox.getChildren().add(rerollButton);
        StackPane root = new StackPane();
        root.getChildren().add(vbox);
        Game game = new Game();
        for(String name : names){
            game.addPlayer(name);
        }
        GUIManager guiM = new GUIManager(this, currentPlayer, score,scoreSelect,scoreButton,rerollButton,dice,game);
        guiM.setPlayer(game.nextPlayer());
        stage.setScene(new Scene(root));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void endGame(Map<String, Integer> scores) {
        putScoreScene(scores);
    }
    
    private void putScoreScene(Map<String, Integer> scores) {
        ListView listV = new ListView();
        Button playAgain = new Button("Play again");
        VBox root = new VBox();
        playAgain.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                putStartScreen();//Giant Recursive loop... not dangerous?
            }
        });
        listV.getItems().addAll(scores.keySet().stream().sorted((score,score2)->scores.get(score2)-scores.get(score)).map((score)->score+": "+scores.get(score)).collect(Collectors.toList()));
        root.getChildren().add(listV);
        root.getChildren().add(playAgain);
        Scene scoreScene = new Scene(root);
        stage.setScene(scoreScene);
    }

    private void putStartScreen() {
        Label welcomeLabel = new Label("Welcome to Gabe's Yahtzee!");
        TextField input = new TextField("Nickname");
        Button addPlayerButton = new Button("Add");
        Button startButton = new Button("Start");
        ListView playerList = new ListView();
        addPlayerButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(!playerList.getItems().contains(input.getText()))
              playerList.getItems().add(input.getText());
           }
        
        });
        playerList.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
              playerList.getItems().remove(playerList.getSelectionModel().getSelectedItem());
            }
        
        });
        startButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(playerList.getItems().size() > 1)
                    putGameScene(playerList.getItems());
           }
        
        });
        VBox root = new VBox();
        HBox inputBox = new HBox();
        inputBox.getChildren().add(input);
        inputBox.getChildren().add(addPlayerButton);
        inputBox.getChildren().add(startButton);
        root.getChildren().add(welcomeLabel);
        root.getChildren().add(inputBox);
        root.getChildren().add(playerList);
        stage.setScene(new Scene(root));
    }
    
}
