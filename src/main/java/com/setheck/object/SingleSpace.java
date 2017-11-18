package com.setheck.object;

import org.slf4j.*;

import java.util.*;
import java.util.stream.*;

public class SingleSpace {
    private static final Logger logger = LoggerFactory.getLogger(SingleSpace.class);
    private Integer value = 0;
    private List<Integer> possibleValues = new ArrayList<>();

    public SingleSpace()
    {
        this(0);
    }

    public SingleSpace(int value)
    {
        if (0 < value && value < 10)
        {
            this.value = value;
            this.removeAllPossibleValues();
        }
        else
        {
            this.value = 0;
            IntStream.range(1,10).forEach(possibleValues::add);
        }
    }

    public boolean removePossibleValue(Integer possibleValue)
    {
        return removePossibleValues(Collections.singletonList(possibleValue));
    }

    public void removeAllPossibleValues()
    {
        this.possibleValues.clear();
    }

    public boolean removePossibleValues(List<Integer> values)
    {
        boolean result = possibleValues.removeAll(values);

        if (this.possibleValues.size() == 1)
        {
            this.value = possibleValues.get(0);
            logger.info("Value found by elimination: {}", this.value);
            possibleValues.clear();
        }
        return result;
    }

    public Integer[] getPossibleValues()
    {
        return possibleValues.toArray(new Integer[0]);
    }

    public int getValue()
    {
        return value;
    }

    public boolean hasNoValue()
    {
        return ! hasValue();
    }

    public boolean hasValue()
    {
        return value != 0;
    }

    public void setValue(Integer value)
    {
        if (this.value == 0 && isValid(value))
        {
            this.value = value;
            possibleValues.clear();
        }
    }

    private boolean isValid(Integer value)
    {
        return 0 < value && value < 10;
    }

    @Override
    public String toString()
    {
        return "SingleSpace{ " +
                "value=" + value +
                ", possibleValues=" + possibleValues +
                " }";
    }
}
