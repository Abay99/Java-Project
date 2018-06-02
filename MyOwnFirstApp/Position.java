public class Position {

    private int x, y; // initialize x , y 
    
    public Position(int a,int b){ // create Position with a and b variables
        x = a;
        y = b;
    }
    public int getX(){ // method that return X
        return x;
    }
    public int getY(){ // method that return Y
        return y;
    }
    public void setX(int a){ // method that return X
        x = a;
    }
    public void setY(int b){ // method that return Y
        y = b;
    }
    public boolean equals(Position a){ //method that return true or false if the given coordinates is equal
        if(x == a.getX() && y == a.getY()){
            return true;
        }
        else
            return false;
    }
    
} 