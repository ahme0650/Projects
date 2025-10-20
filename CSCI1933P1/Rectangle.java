// Written by Hamza Ahmed, ahme0650
package CSCI1933P1;
import java.awt.Color;

public class Rectangle {
    private double xPos;
    private double yPos;
    private double width;
    private double height;
    private Color color;

    //Constructor
    public Rectangle(double xPos, double yPos, double width, double height){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.color = color.BLACK;
    }

    // Calculates the perimeter of a rectangle
    public double calculatePerimeter(){
        return 2 * (height + width);
    }

    //Calculates the area of a rectangle
    public double calculateArea(){
        return height * width;
    }

    // Getters and Setters
    public void setColor(Color color){
        this.color = color;
    }

    public void setPos(double xPos, double yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public void setWidth(double width){
        this.width = width;
    }

    public Color getColor(){
        return color;
    }

    public double getXPos(){
        return xPos;
    }

    public double getYPos(){
        return yPos;
    }

    public double getHeight(){
        return height;
    }

    public double getWidth(){
        return width;
    }

}
