package com.setheck;

import com.setheck.nio.*;
import com.setheck.object.*;
import com.setheck.sudoku.*;
import org.slf4j.*;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main( String[] args ) throws Exception
    {
        SingleSpace[][] board = SudokuBoardUtils.loadBoard(PuzzleReader.readFile("very-hard-puzzle.csv"));
        SudokuBoardUtils.printBoard(board, true);
        SudokuSolver.solveBoard(board);
        if (SudokuSolver.isSolved(board))
        {
            SudokuBoardUtils.printBoard(board, false);
        }
    }
}
