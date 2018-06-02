import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.io.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

public class MyBotPlayer implements Player{
 
    private Map map;
    private Food food;
    private Circle bot; 
    private Position position;
	private MediaPlayer mp;
    
    public MyBotPlayer(Map m) {
        map = m;
        position = map.getStartPosition();
        bot = map.ball;
		
    } 

    public void secondTask(Food food){  // create my Second Task where my Bot find the road to the food without blocks in the map
        new Thread(() -> { // create Thread
            try{
                while(!(getPosition().equals(food.getPosition()))){ // give my first statement that describes do the folowings until Bot position and Food position will be equal
                    while(getPosition().getX() != food.getPosition().getX()){ // create again while operator where describes do the following until Bot's X position and Food's X position will be equal
                        Thread.sleep(100);
                        if(getPosition().getX() < food.getPosition().getX()){ // if Bot's X position less than Food's X position,then it will moveRight 
                            moveRight();
                        }
                        else if(getPosition().getX() > food.getPosition().getX()){ // if Bot's X position more than Food's X position,then it will moveLeft
                            moveLeft();
                            
                        }
                        Thread.sleep(500);
                    }
                    while(getPosition().getY() != food.getPosition().getY()){ // create again while operator where describes do the following until Bot's Y position and Food's Y position will be equal
                        if(getPosition().getY() < food.getPosition().getY()){ // if Bot's Y position less than Food's Y position,then it will movedown 
                            moveDown();
                        }
                        else if(getPosition().getY() > food.getPosition().getY()){ // if Bot's Y position more than Food's Y position,then it will moveUP
                            moveUp();
                        }
                        Thread.sleep(500); // sleep 0.5 seconds
                    }
                    
                    Thread.sleep(500);
                }
            }catch(InterruptedException ex){}
        }).start();
    }
	
	public void firstTask(Food food){//create FirstTask method where my Bot will move like "Snake" to the food, when Bot reaches the food bor will stop
		new Thread(new Runnable() { // create Thread 
            public void run() {
                try{
		            Thread.sleep(200);
					
						if(getPosition().equals(food.getPosition())){ // if Bot position and Food position will be equal, exit from the system 
							System.exit(0);
						}
						else{
							for(int i = 0; i < map.getSize()/2; i++){ // create for loop which do the followings in that case 4 times 
                        while(getPosition().getX() != (map.getSize() - 1)){ // 
							if(!getPosition().equals(food.getPosition())){
								moveRight();
							    Thread.sleep(50);
							}
                        }
						if(!getPosition().equals(food.getPosition())){
							moveDown();
                            Thread.sleep(50);
				        } 
						
                        while(getPosition().getX() != 0){
							if(!getPosition().equals(food.getPosition())){
								moveLeft();
			                    Thread.sleep(50);
							}
                        }
						if(!getPosition().equals(food.getPosition())){
							moveDown();
                            Thread.sleep(50);
						}
							}
                    }
					
                }catch(InterruptedException ex){}
            }
        }).start();
    }
	
	
	
	 public void thirdTask(Food food){ // create thirdTask method where My Bot find the road to the food passing through the blocks
        new Thread(new Runnable() {  // create Thread
            public void run(){
                try{
                    while(getPosition() != food.getPosition()){ // give a condition that do the following until 
                        ArrayList<Position> moves = BFS(food); // using BFS method i take moves of the my Bot
                        Collections.reverse(moves); // reversing the moves of the Bot
                        Thread.sleep(200);
                        for(int i = 0; i<moves.size(); i++){ // create a loop where I change the movement of my Bot like changing positions
							Position pos = moves.get(i);
                                getPosition().setX(pos.getX());
                                getPosition().setY(pos.getY());
								bot.setFill(new ImagePattern(new Image(new File("pacmanr.png").toURI().toString())));
                                bot.setCenterX(getPosition().getX()*map.getUnit() + map.getUnit()/2);
                                bot.setCenterY(getPosition().getY()*map.getUnit() + map.getUnit()/2);
                       
                            Thread.sleep(200);
                        }
                        Thread.sleep(200);
                    }
                }catch(InterruptedException ex){}
            }
        }).start();
    }
    
    public ArrayList <Position> BFS(Food food){ // create BFS (Breadth - First Search ) method
        HashMap <Position , Position> previous = new HashMap <Position , Position>(); // create HashMap and called it previous
        Queue <Position> queue = new LinkedList <Position>(); // create Queue and called it queue
        ArrayList <Position> road = new ArrayList <Position>(); // and create just ArrayList where a save the road to the Food
        previous.put(getPosition(),  null ); // inserting initial positions
        queue.add(getPosition());
        Position current = getPosition();
        while(!queue.isEmpty()){ // give a condition that do the following until queue will be empty
            current = queue.poll(); // and then removes the position
            if(current.equals(food.getPosition())){ // gives condition that if current position will be equal to food position just "break"
                break;
            }
			for(int i = 0; i< edgeCells(current).size(); i++ ){ // create a loop to open from list the edgeCells
                Position next = edgeCells(current).get(i);
                if(!previous.containsKey(next)){ // give a condition that next if the next position not in previous list ,then adds to the queue and put it to the previous
                    queue.add(next);
                    previous.put(next, current);
                }
            }
        }
        while(!current.equals(getPosition())){ // return all the road from start point to the food 
            road.add(current);
            current = previous.get(current);
        }
        return road;
    }
    
    public ArrayList <Position> edgeCells(Position botposition){ // edgeCells method find the edge cell from the giving position and then add it to array list
        ArrayList<Position> array = new ArrayList<Position>();
        if(botposition.getY() != 0 && map.getMap()[botposition.getX()][botposition.getY() - 1] != 1){
            array.add(new Position(botposition.getX(), botposition.getY() - 1));
        }
        if(botposition.getY() != map.getSize() - 1 && map.getMap()[botposition.getX()][botposition.getY() + 1] != 1){
            array.add(new Position(botposition.getX(), botposition.getY() + 1));
        }
        if(botposition.getX() != map.getSize() - 1 && map.getMap()[botposition.getX() + 1][botposition.getY()] != 1){
            array.add(new Position(botposition.getX() + 1, botposition.getY()));
        }  
        if(botposition.getX() != 0 && map.getMap()[botposition.getX() - 1][botposition.getY()] != 1){
            array.add(new Position(botposition.getX() - 1, botposition.getY()));
        }
        return array;
    }
	
	
    
    public void moveRight(){
        if(position.getX() + 1 >= map.getSize() || map.getMap()[position.getX() + 1][position.getY()] == 1){}
        else{
			bot.setFill(new ImagePattern(new Image(new File("pacmanr.png").toURI().toString()))); // fill circle in Red color
            bot.setCenterX(bot.getCenterX() + map.getUnit());  
            position.setX(position.getX() + 1);
            
        } 
    }
    //moves ball left but before checks if it is impossible
    public void moveLeft(){
        if(position.getX() <= 0 || map.getMap()[position.getX() - 1][position.getY()] == 1){}
        else{
			bot.setFill(new ImagePattern(new Image(new File("pacmanl.png").toURI().toString()))); // fill circle in Red color
            bot.setCenterX(bot.getCenterX() - map.getUnit());
            position.setX(position.getX() - 1);
        }
    }
    //moves ball up but before checks if it is impossible
    public void moveUp(){
        if(position.getY() <= 0 || map.getMap()[position.getX()][position.getY() - 1] == 1){}
        else{
			bot.setFill(new ImagePattern(new Image(new File("pacman1.png").toURI().toString()))); // fill circle in Red color
            bot.setCenterY(bot.getCenterY() - map.getUnit());
            position.setY(position.getY() - 1);
        }
    }
    //moves ball down but before checks if it is impossible
    public void moveDown(){
        if(position.getY() + 1 >= map.getSize() || map.getMap()[position.getX()][position.getY() + 1] == 1){}
        else{
			bot.setFill(new ImagePattern(new Image(new File("pacmand.png").toURI().toString()))); // fill circle in Red color
            bot.setCenterY(bot.getCenterY() + map.getUnit());
            position.setY(position.getY() + 1);
        }
    }
    public Position getPosition(){
        return position;
    }
}