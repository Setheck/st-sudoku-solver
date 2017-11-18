package com.setheck.nio;

import org.apache.commons.csv.*;

import java.io.*;
import java.util.*;


public class PuzzleReader {

    public static List<CSVRecord> readFile(String file) throws Exception
    {
        InputStream is = ClassLoader.getSystemResourceAsStream(file);
        Reader reader = new InputStreamReader(is);
        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);
        return parser.getRecords();
    }
}
