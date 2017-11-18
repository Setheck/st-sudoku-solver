package com.setheck;

import com.setheck.nio.*;
import com.setheck.object.*;
import org.slf4j.*;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main( String[] args ) throws Exception
    {
        Map<String, SingleSpace> board = SudokuBoardUtils.loadBoard(PuzzleReader.readFile("very-hard-puzzle.csv"));
        SudokuSolver.solveBoard(board);
        if (SudokuSolver.isSolved(board))
        {
            SudokuBoardUtils.printBoard(board, false);
        }
    }
}
