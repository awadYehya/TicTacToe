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
public class TicTacToeAI {
    /**
     * Current grid
     */
    private TicTacToeGame currGame;
    private static String [] computerWTaunts = new String[5];
    private static String [] computerLTaunts = new String[5];
    /**
     * Constructor
     * @param TTTGame 
     */
    public TicTacToeAI(TicTacToeGame TTTGame) {
        this.currGame = TTTGame;
        computerWTaunts[0] = "You left yourself wide open Human!";
        computerWTaunts[1] = "Try again.";
        computerWTaunts[2] = "Nice try.";
        computerWTaunts[3] = "Now that you have lost you must assign my programmer an A!";
        computerWTaunts[4] = "I'm just too good.";
        computerLTaunts[0] = "Nooooooooooooo!!";
        computerLTaunts[1] = "Uhm, that was totally a bug.";
        computerLTaunts[2] = "I'll get you next time!";
        computerLTaunts[3] = "I admit defeat. Well done, Human.";
        computerLTaunts[4] = "01001001 00100000 01101000 01100001 01110100 01100101 00100000 01111001 01101111 01110101";
    }
    
    /**
     * Updates the game
     * @param TTTGame 
     */
    private void updateGame(TicTacToeGame TTTGame) {
        this.currGame = TTTGame;
    }
    
    public int getNextMove(TicTacToeGame TTTGame) {
        updateGame(TTTGame);
        return bestMove();
    }
    
    /**
     * Gets a message
     * @param win
     * @return 
     */
    public static String getT(boolean win) {
        if (win) {
            return computerWTaunts[TicTacToeTest.getRandomWithRange(0, 4)];
        } else {
            return computerLTaunts[TicTacToeTest.getRandomWithRange(0, 4)];
        }
    }
    
    /**
     * Returns best move position
     * @return 
     */
    private int bestMove() {
        /* ATTACK MOVES */
        
        // Check rows for good moves
        for (int i = 0; i < 3; i++) {
            int playPos = checkRowForPlay(i, TicTacToeGame.Move.O);
            if (playPos > 0) {
                return playPos;
            }
        }
        
        // Check columns for good moves
        for (int i = 0; i < 3; i++) {
            int playPos = checkColForPlay(i, TicTacToeGame.Move.O);
            if (playPos > 0) {
                return playPos;
            }
        }
        
        // Check diagonal for good play
        if (checkDiagsForPlay(TicTacToeGame.Move.O) > 0) {
            return checkDiagsForPlay(TicTacToeGame.Move.O);
        }
        
        /* DEFENSE MOVES */
        
        // Check rows for good moves
        for (int i = 0; i < 3; i++) {
            int playPos = checkRowForPlay(i, TicTacToeGame.Move.X);
            if (playPos > 0) {
                return playPos;
            }
        }
        
        // Check columns for good moves
        for (int i = 0; i < 3; i++) {
            int playPos = checkColForPlay(i, TicTacToeGame.Move.X);
            if (playPos > 0) {
                return playPos;
            }
        }
        
        // Check diagonal for good play
        if (checkDiagsForPlay(TicTacToeGame.Move.X) > 0) {
            return checkDiagsForPlay(TicTacToeGame.Move.X);
        }
        
        // Will get a random number
        return TicTacToeTest.getComputerMove();
    }
    
    /**
     * Checks if the row has 2 plays of same move
     * @param rownum
     * @param move
     * @return the position to play, -1 if no play
     */
    private int checkRowForPlay(int rownum, TicTacToeGame.Move move) {
        //currGame.printBoard();
        int [][] gameGrid = currGame.getGrid();
        int moveCount = 0; // number of X's or O's in row depending on what var move is
        int playsCount = 0; // total number of plays on row (3 if full row)
        int emptyPos = 0; // 1-9 position that is empty
        
        // Count move, plays,
        for (int i = 0; i < 3; i++) {
            int curVal = gameGrid[rownum][i];
            if (curVal == move.getValue()) {
                moveCount += 1;
                playsCount += 1;
            } else if (curVal > 0) {
                emptyPos = curVal;
            } else {
                playsCount += 1;
            }
        }
        
        // Make decision
        if (moveCount == 2 && playsCount == 2) {
            return emptyPos;
        } else {
            return -1; // don't play here
        }
    }
    
    /**
     * Checks if the column has 2 plays of the same type, and returns where to play on the column
     * @param colnum
     * @param move
     * @return position to play, or -1 if you shouldn't play on this column
     */
    private int checkColForPlay(int colnum, TicTacToeGame.Move move) {
        int [][] gameGrid = currGame.getGrid();
        int moveCount = 0; // number of X's or O's in row depending on what var move is
        int playsCount = 0; // total number of plays on row (3 if full row)
        int emptyPos = 0; // 1-9 position that is empty
        
        // Count move, plays,
        for (int i = 0; i < 3; i++) {
            int curVal = gameGrid[i][colnum];
            if (curVal == move.getValue()) {
                moveCount += 1;
                playsCount += 1;
            } else if (curVal > 0) {
                emptyPos = curVal;
            } else {
                playsCount += 1;
            }
        }
        
        // Make decision
        if (moveCount == 2 && playsCount == 2) {
            return emptyPos;
        } else {
            return -1; // don't play here
        }
    }
    
    /**
     * Checks if the column has 2 plays of the same type, and returns where to play on the column
     * @param colnum
     * @param move
     * @return position to play, or -1 if you shouldn't play on this column
     */
    private int checkDiagsForPlay(TicTacToeGame.Move move) {
        int [][] gameGrid = currGame.getGrid();
        int moveCount = 0; // number of X's or O's in diags depending on what var move is
        int playsCount = 0; // total number of plays on diags (3 if full row)
        int emptyPos = 0; // 1-9 position that is empty
        
        // Count move, plays, on positive diag
        for (int i = 0; i < 3; i++) {
            int curVal = gameGrid[i][i];
            if (curVal == move.getValue()) {
                moveCount += 1;
                playsCount += 1;
            } else if (curVal > 0) {
                emptyPos = curVal;
            } else {
                playsCount += 1;
            }
        }
        
        // Make decision
        if (moveCount == 2 && playsCount == 2) {
            return emptyPos;
        } else {
            moveCount = 0;
            playsCount = 0;
            emptyPos = 0;
        }
        
         // Count move, plays, on negative diag
        for (int i = 0; i < 3; i++) {
            int curVal = gameGrid[i][2-i];
            if (curVal == move.getValue()) {
                moveCount += 1;
                playsCount += 1;
            } else if (curVal > 0) {
                emptyPos = curVal;
            } else {
                playsCount += 1;
            }
        }
        
        // Make decision
        if (moveCount == 2 && playsCount == 2) {
            return emptyPos;
        } else {
            return -1; // don't play here
        }
    }
}
