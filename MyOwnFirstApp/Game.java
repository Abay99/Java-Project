import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.*;
import javafx.*;
import java.io.File;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javafx.geometry.Pos;
import javafx.scene.media.MediaPlayer;

import javafx.scene.media.Media;

public class Game extends Application {
    Map map; // initialize a Map class
    Food food; // initialize a Food class
    MyBotPlayer player; // initialize a MyPlayer class
    public void start(Stage primaryStage) throws FileNotFoundException {
        
        map = new Map("map2.txt"); // create  a Map class
        
        player = new MyBotPlayer(map); // create a MyPlayer class
        
        food = new Food(map, player); // create a Food class
       
		map.setOnKeyPressed( e-> {
            switch(e.getCode()){
                //case F : player.firstTask(food); break;
                //case S : player.secondTask(food); break;
				case T : player.thirdTask(food); break;
            }
        });
		BorderPane bp = new BorderPane();
		Label w = new Label("Welcome!");
		w.setFont(new Font("Arial",32));
		Button start = new Button("Start");
		Button exit = new Button("Exit");
		VBox v = new VBox();
		v.setAlignment(Pos.CENTER);
		v.getChildren().addAll(w,start,exit);
		bp.setCenter(v);
        Scene scene2 = new Scene(bp,400,400);
        Scene scene = new Scene(map);
        start.setOnAction(e->{
			primaryStage.setScene(scene);
			MediaPlayer mp= new MediaPlayer(new Media(new File("C:\\Users\\aiman\\Desktop\\Bittersweet.mp3").toURI().toString()));
			mp.play();
		});
		exit.setOnAction(e->{
			System.exit(0);
		});
        primaryStage.setTitle("Eaten");
        primaryStage.setScene(scene2);
        primaryStage.show();
        map.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
