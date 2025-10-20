// Written by Hamza Ahmed, ahme0650
package CSCI1933P1;
import java.awt.Color;
public class FractalDrawer {
    private double totalArea = 0;  // member variable for tracking the total area


    public FractalDrawer() {
    }  // contructor

    //Generates Random Colors
    public Color getRGB() {
        int R = (int) (Math.random() * 256);
        int G = (int) (Math.random() * 256);
        int B = (int) (Math.random() * 256);

        Color randColor = new Color(R, G, B);
        return randColor;
    }


    // We create a new Canvas object then determine which shapes to draw a
    // fractal by getting user input, we then print the total area after the fractal is drawn,
    // and we return the area of the fractal
    public double drawFractal(String type) {
        Canvas drawing = new Canvas(1000, 1000);
        if (type.equals("Circle")) {
            drawCircleFractal(100, 400, 400, getRGB(), drawing, 0);
            System.out.println("Total Area Is: " + totalArea);
        } else if (type.equals("Triangle")) {
            drawTriangleFractal(200, 200, 300, 500, getRGB(), drawing, 0);
            System.out.println("Total Area Is: " + totalArea);
        } else if (type.equals("Rectangle")) {
            drawRectangleFractal(175, 175, 350, 350, getRGB(), drawing, 0);
            System.out.println("Total Area Is: " + totalArea);
        }

        // TODO: Return total area
        return totalArea;
    }


    // Draws The Triangle Fractal Using Recursion !
    public void drawTriangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level) {
        if (level == 7) {
            return;
        }
        // Draw !

        Triangle drawTri = new Triangle(x, y, width, height);
        drawTri.setColor(c);
        can.drawShape(drawTri);

        totalArea += drawTri.calculateArea();

        drawTriangleFractal(width / 2, height / 2, x - (width / 2), y, getRGB(), can, level + 1);
        drawTriangleFractal(width / 2, height / 2, (x +(width)), y, getRGB(), can, level + 1);
        drawTriangleFractal(width / 2, height / 2, (x +(width/4)), y+(height/2), getRGB(), can, level + 1);

    }



    // Draws The Circle Fractal Using Recursion !
    public void drawCircleFractal(double radius, double x, double y, Color c, Canvas can, int level) {
        if (level == 7) {
            return;
        }
        // Draw !

        Circle c1 = new Circle(x, y, radius);
        c1.setColor(c);
        can.drawShape(c1);

        totalArea += c1.calculateArea();

        drawCircleFractal(radius/2, x-radius, y-radius, getRGB(), can, level + 1);
        drawCircleFractal(radius / 2, x - radius, y + radius, getRGB(), can, level + 1);
        drawCircleFractal(radius / 2, x + radius, y - (radius), getRGB(), can, level + 1);
        drawCircleFractal(radius / 2, x + radius, y + radius, getRGB(), can, level + 1);


    }




    // Draws The Rectangle Fractal Using Recursion !
   public void drawRectangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level) {
       if (level == 7){
           return;
       }
       // Draw !

           Rectangle r1 = new Rectangle(x, y, width, height);
           r1.setColor(c);
           can.drawShape(r1);

           totalArea += r1.calculateArea();

           drawRectangleFractal(width/2, height/2, x -(width/2), y-(height/2), getRGB(), can, level + 1);
           drawRectangleFractal(width/2, height/2, x +(width), y-(height/2), getRGB(), can, level + 1);
           drawRectangleFractal(width/2, height/2, x -(width/2), y+(height), getRGB(), can, level + 1);
           drawRectangleFractal(width/2, height/2, x +(width), y+(height), getRGB(), can, level + 1);


   }



}
