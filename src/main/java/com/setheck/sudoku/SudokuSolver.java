package com.setheck.sudoku;

import com.setheck.object.*;

import java.util.*;
import java.util.stream.*;

public class SudokuSolver {

    private final static int BOARD_SIZE = 9;

    /*
    Important note, SingleSpace[y][x] is the model of the board.
     */
    public static boolean solveBoard(SingleSpace[][] board)
    {
        return false;
    }

    public static boolean isSolved(SingleSpace[][] board)
    {
        Optional<SingleSpace> spacesWithoutValues = Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(SingleSpace::hasValue)
                .findFirst();

        return spacesWithoutValues.isPresent();
    }

    /**
     * Attempt to solve a row
     * @param board the sudoku board.
     * @param x row number
     * @return boolean if the row was solved.
     */
    private boolean solveRow(SingleSpace[][] board, int x)
    {
        List<Integer> possibleValues = new ArrayList<>();
        for (int col = 0; col < BOARD_SIZE; col++)
        {
            SingleSpace space = board[col][x];
            if (space.hasValue())
            {
                possibleValues.add(space.getValue());
            }
        }

        if (possibleValues.isEmpty())
        {
            return true;
        }

        for (int col = 0; col < BOARD_SIZE; col++)
        {
            SingleSpace space = board[col][x];
            if (space.hasNoValue())
            {
                space.removePossibleValues(possibleValues);
            }
        }
        return false;
    }

    /**
     * Attempt to solve a column
     * @param board the sudoku board.
     * @param y column number
     * @return boolean if the column was solved.
     */
    private boolean solveColumn(SingleSpace[][] board, int y)
    {
        List<Integer> possibleValues = new ArrayList<>();
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            SingleSpace space = board[y][row];
            if (space.hasValue())
            {
                possibleValues.add(space.getValue());
            }
        }

        if (possibleValues.isEmpty())
        {
            return true;
        }

        for (int row = 0; row < BOARD_SIZE; row++)
        {
            SingleSpace space = board[y][row];
            if (space.hasNoValue())
            {
                space.removePossibleValues(possibleValues);
            }
        }
        return false;
    }

    private boolean solveSubsquare(SingleSpace[][] board, int xStart, int yStart)
    {
        //TODO: validation
        List<Integer> possibleValues = new ArrayList<>();
        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 3; x++)
            {
                SingleSpace space = board[yStart + y][xStart + x];
                if (space.hasValue())
                {
                    possibleValues.add(space.getValue());
                }
            }
        }

        if (possibleValues.isEmpty())
        {
            return true;
        }

        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 3; x++)
            {
                SingleSpace space = board[yStart + y][xStart + x];
                if (space.hasNoValue())
                {
                    space.removePossibleValues(possibleValues);
                }
            }
        }
        return false;
    }
}
