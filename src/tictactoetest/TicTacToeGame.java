/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoetest;

/**
 *
 * @author yha5009
 */
public class TicTacToeGame {

    ///* Private *///
    
    private int [][]grid = new int[3][3];
    
    /**
     * Checks if a diagonal has been completed
     * @return 
     */
    private boolean isDiagWin () {
        boolean leftWin = grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2];
        boolean rightWin = grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0];
        return leftWin || rightWin;
    }
    
    /**
     * checks if a row/col has been completed
     * @param lineID
     * @return 
     */
    private boolean isRowColWin (int lineID) {
        boolean rowWin = grid[lineID][0] == grid[lineID][1] && grid[lineID][1] == grid[lineID][2];
        boolean colWin = grid[0][lineID] == grid[1][lineID] && grid[1][lineID] == grid[2][lineID];
        return rowWin || colWin;
    }
    
    /**
     * Checks if it is a valid position
     * @param col
     * @param row
     * @return 
     */
    private boolean isValidPos (int col, int row) {
        // Checks for valid input
        if (col > 2 || col < 0 || row < 0 || row > 2 ) {
            return false;
        }

        return (grid[row][col] > 0);
    }
    
    ///* Public *///
    
    /**
     * Constructor
     */
    public TicTacToeGame() {
        int i;
        int loops = 3;
        int position = 1;
        for (i = 0; i < loops ; i++)
        {
            for (int j = 0; j < loops; j++) {
                grid[i][j] = position;
                position += 1;
            }
        }
    }
    
    /** Checks if it's an X
     * 
     * @param arg
     * @return 
     */
    public static boolean isX(int arg) {
        return arg == Move.X.getValue();
    }
    
    public static boolean isX(Move move) {
        return isX(move.getValue());
    }
    
    /**
     * Checks if it is an O
     * @param arg
     * @return 
     */
    public static boolean isO(int arg) {
        return arg == Move.O.getValue();
    }
    
    public static boolean isO(Move move) {
        return isO(move.getValue());
    }
    
    /**
     * Enumerator for the type of move
     */
    public enum Move{
        /* Enum for move type */
        X(-1),
        O(-2);
        private final int value;
        Move(final int newValue) {
            value = newValue;
        }
        public int getValue() { return value; }
    }
    
    /**
     * Makes a move based on input, uses position instead of col and row
     * @param pos
     * @param moveType
     * @return 
     */
    public boolean makeMove (int pos, Move moveType) {
        pos--;
        int row = pos/3;
        int col = (pos % 3);
        return makeMove(col, row, moveType);
    }
    
    /**
     * Makes a move based on input
     * @param col
     * @param row
     * @param moveType
     * @return 
     */
    public boolean makeMove (int col, int row, Move moveType) {
        if (isValidPos(col, row)){
            grid[row][col] = moveType.getValue();
            return true;
        }
        return false;
    }
    
    /**
     * Checks if the game is over
     * @return 
     */
    public boolean isGameOver () {
        // test row/col wins
        for (int i = 0; i < 3; i++) {
            if (isRowColWin(i)) return true;
        }
        return isDiagWin();
    }
    
    /**
     * Checks if game is a draw
     * @return 
     */
    public boolean isGameDraw () {
        
        boolean allMovesTaken = true;
        
        if (!isGameOver()) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j] > 0) {
                        allMovesTaken = false;
                    }
                }
            }
        }
        return allMovesTaken;
    }
    
    /**
     * Prints board to console
     */
    public void printBoard () {
        /* Will print the board to the console. */
        int i;
        int loops = 3;
        for (i = 0; i < loops ; i++)
        {
            System.out.print("\n\t");
            System.out.print(" |");
            for (int j = 0; j < loops; j++) {
                int pieceVal = grid[i][j];
                if (isX(pieceVal)) {
                    System.out.print(" X |");
                } else if (isO(pieceVal)) {
                    System.out.print(" O |");
                } else {
                    System.out.printf(" %d |",pieceVal);
                }
            }
        }
        System.out.print("\n\n"); // \n
    }
    
    /**
     * Returns the grid
     * @return 
     */
    public int[][] getGrid () {
        return grid;
    }

}
