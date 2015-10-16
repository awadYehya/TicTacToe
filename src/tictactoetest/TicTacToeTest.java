/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoetest;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author yha5009
 */
public class TicTacToeTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        printTitle();
        System.out.println("Let's play Tic Tac Toe!");
        System.out.println("By Yehya Awad - yha5009\n");
        
        /** Game Loop **/
        
        boolean keepPlaying = true;
        
        while (keepPlaying) {
            TicTacToeGame tttgame = new TicTacToeGame();
            TicTacToeAI tttAI = new TicTacToeAI(tttgame); // Initialize TicTacToeAI
            boolean isPlayerTurn = true;
            
            System.out.println("Start game!\n");
            
            while (!tttgame.isGameOver() && !tttgame.isGameDraw()) {
                tttgame.printBoard();
                if (isPlayerTurn) {
                    System.out.println("Player's turn");
                    int playerMove = getPlayerInput();
                    if (!tttgame.makeMove(playerMove, TicTacToeGame.Move.X)) {
                        System.out.println("Error: Invalid input entered. Enter a number from the available spots.");
                        continue;
                    }
                    isPlayerTurn = false;
                } else {
                    System.out.println("Computer's turn");
                    isPlayerTurn = true;
                    int compmove = tttAI.getNextMove(tttgame);
                    while (!tttgame.makeMove(compmove, TicTacToeGame.Move.O)) {
                        compmove = tttAI.getNextMove(tttgame);
                    }
                }
            }
            
            tttgame.printBoard();
            
            if (tttgame.isGameOver()) {
                    printEndGameMsg();
                if (!isPlayerTurn) {
                    printPlayerWinMsg();
                } else {
                    printComputerWinMsg();
                }
            } else {
                System.out.println("It's a DRAW!\n");
            }
            
            // Ask if the user wants to play again.
            keepPlaying = askIfNewGame();
        }
        
        printGoodbyeMsg();
    }
    
    /**
     * Requests input from the player
     * @return 
     */
    private static int getPlayerInput () {
        System.out.print("Enter the position you want to play: ");
        Scanner input = new Scanner(System.in);
        int playpos;
        try 
        {
            playpos = input.nextInt();
        } 
        catch (InputMismatchException e) 
        {
            System.out.println("Error: Invalid input entered. Enter a number from the available spots.\n");
            return getPlayerInput();
        }
        return playpos;
    }
    
    /**
     * Generates Random Move
     * @return 
     */
    public static int getComputerMove (){
        return getRandomWithRange(1, 9);
    }
    
    /**
     * Returns a random number within a range
     * @param min
     * @param max
     * @return 
     */
    public static int getRandomWithRange(int min, int max) {
       int range = (max - min) + 1;     
       return (int)(Math.random() * range) + min;
    }
    
    /**
     * Prints the title.
     */
    private static void printTitle () {
        System.out.println("╔╦╗╦ ╔═╗  ╔╦╗╔═╗╔═╗  ╔╦╗╔═╗╔═╗");
        System.out.println("  ║ ║ ║       ║  ╠═╣║       ║ ║  ║║╣ ");
        System.out.println("  ╩ ╩ ╚═╝    ╩  ╩ ╩╚═╝    ╩ ╚═╝╚═╝");
    }
    
    /**
     * Print computer win message
     */
    private static void printComputerWinMsg() {
        System.out.println("                  .----.");
        System.out.println("      .---------. | == |");
        System.out.println("      |.-\"\"\"\"\"-.| |----|");
        System.out.println("      ||       || | == |");
        System.out.println("      ||       || |----|");
        System.out.println("      |'-.....-'| |::::|");
        System.out.println("      `\"\")---(\"\"` |___.|");
        System.out.println("     /:::::::::::\" _  ");
        System.out.println("    /:::=======:::\\`\\`\\");
        System.out.println("    `\"\"\"\"\"\"\"\"\"\"\"\"\"`  '-'");
        System.out.println("   COMPUTER IS VICTORIOUS\n");
        System.out.println("Computer says: "+TicTacToeAI.getT(true));
    }
    
    /**
     * Print player win message
     */
    private static void printPlayerWinMsg() {
        System.out.println("            _.+._");
        System.out.println("          (^\\/^\\/^)");
        System.out.println("           \\@*@*@/");
        System.out.println("           {_____}");
        System.out.println("     PLAYER IS VICTORIOUS\n\n");
        System.out.println("Computer says: "+TicTacToeAI.getT(false));
    }
    
    /**
     * Print END GAME
     */
    private static void printEndGameMsg() {
        System.out.println("-------------------------------");
        System.out.println("---------- GAME OVER ----------");
        System.out.println("-------------------------------");
    }
    
    /**
     * Print Goodbye message
     */
    private static void printGoodbyeMsg() {
        System.out.println("____ ____ ____ ___  ___  _   _ ____ |");
        System.out.println("| __ |  | |  | |  \\ |__]  \\_/  |___ |");
        System.out.println("|__] |__| |__| |__/ |__]   |   |___ O");                       
    }
    
    /**
     * 
     */
    private static boolean askIfNewGame() {
        System.out.print("\n\tNew game? (y/n) ");
        Scanner input = new Scanner(System.in);
        
        char response;
        
        try {
           response = input.next().charAt(0);
        } catch (InputMismatchException e) {
           response = 'k';
        }
        
        System.out.println();
        
        switch (response) {
            case 'y':
            case 'Y':
                return true;
            case 'n':
            case 'N':
                return false;
            default:
                System.out.println("\n\tInvalid input. Enter either 'y' or 'n'");
                return askIfNewGame();
        }
    }
    
}
