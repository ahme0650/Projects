// Written by Hamza Ahmed, ahme0650
package CSCI1933P1;
import java.awt.Color;

public class Triangle {
    private double xPos;
    private double yPos;
    private double width;
    private double height;
    private Color color;

    // Constructor
    public Triangle(double xPos, double yPos, double width, double height){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.color = Color.BLACK;

    }

    //Calculates the perimeter of a triangle
    public double calculatePerimeter(){
        double side = Math.sqrt(Math.pow(width/2, 2)+ Math.pow(height,2));
        return width + (2 * side);
    }

    //Calculates the area of a Triangle
    public double calculateArea(){
        return 0.5 * (width * height);
    }

    //Getters and Setters
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
