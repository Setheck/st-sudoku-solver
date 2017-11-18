package com.setheck.sudoku;

import com.setheck.object.*;
import org.apache.commons.csv.*;
import org.slf4j.*;

import java.util.*;
import java.util.stream.*;

public class SudokuBoardUtils {
    private static final Logger logger = LoggerFactory.getLogger(SudokuBoardUtils.class);


    public static SingleSpace[][] loadBoard(List<CSVRecord> parsedBoard)
    {
        SingleSpace[][] board = new SingleSpace[9][9];
        int y = 0;
        for (CSVRecord record : parsedBoard)
        {
            int x = 0;
            for (String string : record)
            {
                try {
                    int value = Integer.parseInt(string);
                    logger.info("Setting {},{} with {}", x, y, value);
                    board[x][y] = new SingleSpace(value);
                }
                catch(Exception ignored)
                {
                    //nope
                }
                x++;
            }
            y++;
        }
        return board;
    }

    public static Map<String,SingleSpace> cloneBoard(Map<String, SingleSpace> sourceBoard)
    {
        Map<String,SingleSpace> clonedBoard = new HashMap<>();
        sourceBoard.forEach((s,v) -> clonedBoard.put(s, new SingleSpace(v.getValue())));
        return clonedBoard;
    }

    public void printRandomInfo(SingleSpace[][] board, int count)
    {
        final Random random = new Random();
        IntStream.range(0, count)
                .forEach(i ->
                {
                    int x = (Math.abs(random.nextInt()) % 8) + 1;
                    int y = (Math.abs(random.nextInt()) % 8) + 1;
                    SingleSpace space = board[x][x];
                    logger.info("Ramdom pick of {},{} is {}", x, y, space);
                });
    }

    public static boolean copyValues(SingleSpace[][] source, SingleSpace[][] target)
    {
        boolean copied = false;
        for (int x = 1; x < 10; x++)
        {
            for (int y = 1; y < 10; y ++)
            {
                SingleSpace sourceSpace = source[x][y];
                if (sourceSpace.hasValue())
                {
                    SingleSpace targetSpace = target[x][y];
                    targetSpace.setValue(sourceSpace.getValue());
                    copied = true;
                }
            }
        }
        return copied;
    }

    public static void printBoard(SingleSpace[][] board, boolean withPossible)
    {
        for (int x = 0; x < board.length; x++)
        {
            StringBuilder builder = new StringBuilder(10 * 2);
            for (int y = 0; y < board[x].length; y++)
            {
                logger.debug("Getting at {},{}", x, y);
                SingleSpace temp = board[y][x];
                builder.append(temp.getValue());
                if (withPossible)
                {
                    builder.append(Arrays.asList(temp.getPossibleValues()).toString());
                }
                builder.append(",");
            }
            logger.info(builder.toString());
        }
    }

    public static List<Integer> possibleValues()
    {
        return IntStream.range(1,10)
                .boxed()
                .collect(Collectors.toList());
    }

    public static boolean validateBoard()
    {
        return false;
    }
}
