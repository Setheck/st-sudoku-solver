package com.setheck;

import com.setheck.object.*;
import org.apache.commons.csv.*;
import org.slf4j.*;

import java.util.*;
import java.util.stream.*;

public class SudokuBoardUtils {
    private static final Logger logger = LoggerFactory.getLogger(SudokuBoardUtils.class);
    private static final String KEY_FORMAT = "%d,%d";

    public static String getSpaceKey(int x, int y)
    {
        return String.format(KEY_FORMAT, x, y);
    }

    public static int getXfromKey(final String spaceKey)
    {
        String[] xySpaces = spaceKey.split(",");
        return Integer.parseInt(xySpaces[0]);
    }

    public static int getYfromKey(final String spaceKey)
    {
        String[] xySpaces = spaceKey.split(",");
        return Integer.parseInt(xySpaces[1]);
    }

    public static Map<String, SingleSpace> loadBoard(List<CSVRecord> parsedBoard)
    {
        Map<String, SingleSpace> board = new HashMap<>();
        int y = 0;
        for (CSVRecord record : parsedBoard)
        {
            y++;
            int x = 0;
            for (String string : record)
            {
                x++;
                try {
                    int value = Integer.parseInt(string);
                    logger.info("Setting {},{} with {}", x, y, value);
                    board.put(getSpaceKey(x, y), new SingleSpace(value));
                }
                catch(Exception ignored)
                {
                    //nope
                }
            }
        }
        return board;
    }

    public static Map<String,SingleSpace> cloneBoard(Map<String, SingleSpace> sourceBoard)
    {
        Map<String,SingleSpace> clonedBoard = new HashMap<>();
        sourceBoard.forEach((s,v) -> clonedBoard.put(s, new SingleSpace(v.getValue())));
        return clonedBoard;
    }

    public void printRandomInfo(Map<String, SingleSpace> board, int count)
    {
        final Random random = new Random();
        IntStream.range(0, count)
                .forEach(i ->
                {
                    int x = (Math.abs(random.nextInt()) % 8) + 1;
                    int y = (Math.abs(random.nextInt()) % 8) + 1;
                    SingleSpace space = board.get(getSpaceKey(x, y));
                    logger.info("Ramdom pick of {},{} is {}", x, y, space);
                });
    }

    public static boolean copyValues(Map<String, SingleSpace> source, Map<String,SingleSpace> target)
    {
        boolean copied = false;
        for (int x = 1; x < 10; x++)
        {
            for (int y = 1; y < 10; y ++)
            {
                String spaceKey = getSpaceKey(x, y);
                SingleSpace sourceSpace = source.get(spaceKey);
                if (sourceSpace.hasValue())
                {
                    SingleSpace targetSpace = target.get(spaceKey);
                    targetSpace.setValue(sourceSpace.getValue());
                    copied = true;
                }
            }
        }
        return copied;
    }

    public static void printBoard(Map<String, SingleSpace> board, boolean withPossible)
    {
        for (int y = 1; y < 10; y++)
        {
            StringBuilder builder = new StringBuilder(10 * 2);
            for (int x = 1; x < 10; x++)
            {
                logger.debug("Getting at {},{}", x ,y);
                SingleSpace temp = board.get(getSpaceKey(x, y));
                builder.append(temp.getValue());
                if (withPossible)
                {
                    builder.append(Arrays.asList(temp.getPossibleValues()).toString());
                }
                builder.append(",");
            }
            logger.info(builder.append("\n").toString());
        }
    }

    public static boolean validateBoard()
    {
        return false;
    }
}
