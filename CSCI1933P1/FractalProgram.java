// Written by Hamza Ahmed, ahme0650

package CSCI1933P1;
import java.util.Scanner;

public class FractalProgram {
    // Prints corresponding Fractal via user input
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Choose A Fractal To Draw\nCircle, Triangle, or a Rectangle: ");
        String shape = s.nextLine();
        FractalDrawer f1 = new FractalDrawer();
        f1.drawFractal(shape);

    }
}

