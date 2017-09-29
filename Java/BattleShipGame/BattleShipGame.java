/**
 * Created by Arsen on 9/29/17.
 */

import java.util.*;


public class BattleShipGame {
    /* This is the ”main” class,
    containing the main method and an instance variable of type Ocean */


    /* In this class you will set up the game; accept ”shots” from the user;
    display the results; print final scores; and ask the user if he/she wants to play again.
     All input/output is done here
    (although some of it is done by calling a print() method in the Ocean class.)
     */

    private double numGuess = 0;
    private static boolean isGameOver;

    public static void main(String[] args){
        setup();
    }

    private static void setup(){
            /* Initial Game Set UP */
            Scanner s = new Scanner(System.in);
            System.out.println("Welcome to battleship!");
            Ocean ocean = new Ocean();
            ocean.placeAllShipsRandomly();
            while(!ocean.isGameOver()){
                System.out.println("Fire at the ocean, input row:");
                int row = s.nextInt();
                System.out.println("Fire at the ocean, input column:");
                int col = s.nextInt();
                if(ocean.shootAt(row, col)){
                    System.out.println("You hit a ship at (" + row + " , " + col + ")!");
                }else{
                    System.out.println("You missed.");
                }
                ocean.print();
            }
            System.out.println("Game is over");
            System.out.println("Your final score is " + ocean.getShotsFired());
        }

    private static void displayresult(String location){

    }

    private static void printscore(){

    }

    /* Note that you want to accept 5 shots from the user.
    So you need to ensure that you have a well defined format for this. For example you can provide an instruction to the user as follows
    The input format should look like this: 1, 1; 0, 3; 7, 3; 9, 11; 12, 17   */


}
