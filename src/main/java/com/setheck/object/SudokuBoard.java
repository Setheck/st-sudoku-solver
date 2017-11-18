package com.setheck.object;

import org.apache.commons.csv.*;
import org.slf4j.*;

import java.util.*;
import java.util.stream.*;

public class SudokuBoard extends HashMap<String, SingleSpace> {

    private static final Logger logger = LoggerFactory.getLogger(SudokuBoard.class);
    private static final String KEY_FORMAT = "%d,%d";
    private Map<String, SingleSpace> board = new HashMap<>();

    public void initialize()
    {
        for (int i = 1; i < 10; i++)
        {
            for (int j = 1; j < 10; j++)
            {
                //board.put(getSpaceKey(i, j), new SingleSpace());
            }
        }
    }
}
