package APFRQ.Unit3;

public class Unit3frq2 {

    public static void drawLine (int x, int y, int x2, int y2) {
        int answer = x + y + x2 + y2;
    }

    public static void drawSquare(int x, int y, int len){
        if(len > y && y <= (10-x)){ //way to assign length value
            len = y; //Checks if len is larger than it could fit on the y axis
        }
        if(len > (10-x) && (10-x) <= y){
            len = x; //Checks if len is larger than it could fit on the x axis
        }
        //method for drawing the square
        drawLine(x,y,(x+len),y);
        drawLine(x,y,x,y-len);
        drawLine(x,y-len,x+len,y-len);
        drawLine(x+len,y,x+len,y-len);
        System.out.println("The area is: " + (len * len));
        //area printing logic
        System.out.println("The side length is: " + len);
        //side length printing logic
    }

    public static void main(String args[]){
        drawSquare(1,2,4); //Method call to draw the square
    }
// our implementation worked fine, but we made one mistake which was not very consequential so We are willing to give ourselves a 4/5
}