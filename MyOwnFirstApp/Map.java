import java.io.*;
import java.util.Scanner;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class Map extends Pane{
    
    Rectangle rec; // create Rectangle
    Circle ball;   // create ball or player using Circle
    private int unit; // create unit 
    private int size; // create size
    private int[][] map; // create "map" array
    private Position start; // create position
    
    public Map(String mapName) throws FileNotFoundException {
        Scanner in = new Scanner(new File(mapName));//importing Scanner, I am openning the file
        
        size = Integer.parseInt(in.next()); // it is the size of columns / rows
        unit = 30;                          //the length of the one cell (in pixels)
        map = new int[size][size];          //creates two-dimensional array;
        for(int row = 0; row < map.length; row++){ // looping by the row
            for(int col = 0; col < map[row].length; col++){// looping by the column
                map[col][row] = Integer.parseInt(in.next()); // adds the value of row and column in the "map" array 
                
                Rectangle rec = new Rectangle(col*unit,row*unit,unit,unit); // creates a Rectangle
                rec.setStroke(Color.BLACK);//gave the stroke color
                rec.setFill(Color.WHITE);// fill the rectangle with initially white color
                
                if(map[col][row] == 1){ // make a condition that if the value from the "map" array is equal to 1, then fill the rectangle in Black color
                    rec.setFill(Color.BLACK);
                }
                else if(map[col][row] == 2){ // // make a condition that if the value from the "map" array is equal to 2, then gave a start position 
                    start = new Position(row,col);
                }
                getChildren().add(rec); // add rectangle in Pane class which I extends above
            }
        } 
		
        ball = new Circle(start.getX()*getUnit() + getUnit()/2,start.getY()*getUnit() + getUnit()/2, getUnit()/2); // create a ball or player using Circle
        
        getChildren().add(ball); // then adds it in Pane class
    }
    public int getUnit(){ // method that return unit
        return unit;
    }
    public int getSize(){ // method that return size
        return size;
    }
    public int[][] getMap(){ // method that return "map" array 
        return map;
    }
    public Position getStartPosition(){ // method that return getStartPosition
        return start;
    }
}    
