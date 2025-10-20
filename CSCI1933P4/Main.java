//Import Section
import java.util.Random;
import java.util.Scanner;


public class Main{

    public static void main(String[] args){
        //Where we play MineSweeper !
        System.out.println("Welcome To MineSweeper!");
        Scanner s = new Scanner(System.in);
        System.out.println("\nPlease Choose A Difficulty: Easy, Medium, or Hard.\n");
        String user = s.nextLine();
        Minefield minefield;
        if (user.equals("Easy")) {
            //Easy Mode
            System.out.println("If You Would Like To Play In Debug Mode Type 'debug' Else Press Any Key");
            String debug = s.nextLine();
            minefield = new Minefield(5, 5, 5);
            System.out.println("Where Would You Like To Start: Format --> [x y] & Range --> [0, 4]");
            user = s.nextLine();
            args = user.split(" ");
            minefield.createMines(Integer.parseInt(args[0]), Integer.parseInt(args[1]),5 );
            minefield.evaluateField();
            minefield.revealStartingArea(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            while (!minefield.gameOver()){
                //Debug Mode
                if (debug.equals("debug")){
                    minefield.debug();
                    System.out.println(minefield);
                }else {
                    System.out.println(minefield);
                }
                System.out.println("Enter A Coordinate And If You Wish To Place A Flag Input 'true' else 'false' [x y (true or false)] (Flags Remaining: " + Minefield.getFlags() + ")");
                user = s.nextLine();
                args = user.split(" ");
                minefield.guess(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Boolean.parseBoolean(args[2]));

            }if (debug.equals("debug")){
                minefield.debug();
                System.out.println(minefield);
            }else {
                System.out.println(minefield);
            }
        }
        else if (user.equals("Medium")){
            //Medium Mode
            System.out.println("If You Would Like To Play In Debug Mode Type 'debug' Else Press Any Key");
            String debug = s.nextLine();
            minefield = new Minefield(9, 9, 12);
            System.out.println("Where Would You Like To Start: Format --> [x y] & Range --> [0,8]");
            user = s.nextLine();
            args = user.split(" ");
            minefield.createMines(Integer.parseInt(args[0]), Integer.parseInt(args[1]),12 );
            minefield.evaluateField();
            minefield.revealStartingArea(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            while (!minefield.gameOver()){
                //Debug Mode
                if (debug.equals("debug")){
                    minefield.debug();
                    System.out.println(minefield);
                }else {
                    System.out.println(minefield);
                }
                System.out.println("Enter A Coordinate And If You Wish To Place A Flag Input 'true' else 'false' [x y (true or false)] (Flags Remaining: " + Minefield.getFlags() + ")");
                user = s.nextLine();
                args = user.split(" ");
                minefield.guess(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Boolean.parseBoolean(args[2]));
            }if (debug.equals("debug")){
                minefield.debug();
                System.out.println(minefield);
            }else {
                System.out.println(minefield);
            }
        }
        else if (user.equals("Hard")){
            //Hard Mode

            System.out.println("If You Would Like To Play In Debug Mode Type 'debug' Else Press Any Key");
            String debug = s.nextLine();
            minefield = new Minefield(20, 20, 40);
            System.out.println("Where Would You Like To Start: Format --> [x y] & Range --> [0,19]");
            user = s.nextLine();
            args = user.split(" ");
            minefield.createMines(Integer.parseInt(args[0]), Integer.parseInt(args[1]),40 );
            minefield.evaluateField();
            minefield.revealStartingArea(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            while (!minefield.gameOver()){
                //Debug Mode
                if (debug.equals("debug")){
                    minefield.debug();
                    System.out.println(minefield);
                }else {
                    System.out.println(minefield);
                }
                System.out.println("Enter A Coordinate And If You Wish To Place A Flag Input 'true' else 'false' [x y (true or false)] (Flags Remaining: " + Minefield.getFlags() + ")");
                user = s.nextLine();
                args = user.split(" ");
                minefield.guess(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Boolean.parseBoolean(args[2]));

            }if (debug.equals("debug")){
                minefield.debug();
                System.out.println(minefield);
            }else {
                System.out.println(minefield);
            }
        }

    }
    
    
}
