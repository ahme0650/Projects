// Written by Hamza Ahmed, ahme0650
package CSCI1933P1;
import java.awt.Color;

public class Circle {

    private double radius;
    private double xPosition;
    private double yPosition;
    private Color color;

    // Constructor
    public Circle(double xPosition, double yPosition, double radius) {
        this.radius = radius;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = Color.BLACK;
    }
    //Calculates the perimeter of a circle
    public double calculatePerimeter(){
        return 2 * (Math.PI * radius);
    }

    //Calculates the area of circle
    public double calculateArea(){
        return Math.PI * Math.pow(radius, 2);
    }

    //Getters and Setters
    public void setColor(Color color){
        this.color = color;
    }

    public void setPos(double x, double y){
        this.xPosition = x;
        this.yPosition = y;
    }

    public void setRadius(double radius){
        this.radius = radius;
    }

    public Color getColor(){
        return color;
    }

    public double getXPos(){
        return xPosition;
    }

    public double getYPos() {
        return yPosition;
    }

    public double getRadius(){
        return radius;
    }
}
