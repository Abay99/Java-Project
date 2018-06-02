`import javafx.scene.shape.Circle;

public class MyPlayer implements Player{ // create MyPlayer class and implements Player interface
  
    private Circle ball; // initialize ball using Circle
    private Map map; // initialize Map class
    private Position position; // initialize Position class
    
    public MyPlayer(Map m){ // create MyPlayer class with "map" array
        map = m; 
        position = map.getStartPosition();
        ball = map.ball;
    }
    //moves ball right but before checks if it is impossible
    public void moveRight(){
        if(position.getX() + 1 >= map.getSize() || map.getMap()[position.getX() + 1][position.getY()] == 1){}
        else{
            ball.setCenterX(ball.getCenterX() + map.getUnit());  
            position.setX(position.getX() + 1);
        } 
    }
    //moves ball left but before checks if it is impossible
    public void moveLeft(){
        if(position.getX() <= 0 || map.getMap()[position.getX() - 1][position.getY()] == 1){}
        else{
            ball.setCenterX(ball.getCenterX() - map.getUnit());
            position.setX(position.getX() - 1);
        }
    }
    //moves ball up but before checks if it is impossible
    public void moveUp(){
        if(position.getY() <= 0 || map.getMap()[position.getX()][position.getY() - 1] == 1){}
        else{
            ball.setCenterY(ball.getCenterY() - map.getUnit());
            position.setY(position.getY() - 1);
        }
    }
    //moves ball down but before checks if it is impossible
    public void moveDown(){
        if(position.getY() + 1 >= map.getSize() || map.getMap()[position.getX()][position.getY() + 1] == 1){}
        else{
            ball.setCenterY(ball.getCenterY() + map.getUnit());
            position.setY(position.getY() + 1);
        }
    }
    public Position getPosition(){
        return position;
    }
}
