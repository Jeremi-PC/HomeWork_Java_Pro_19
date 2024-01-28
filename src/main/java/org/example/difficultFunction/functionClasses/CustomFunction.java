package org.example.difficultFunction.functionClasses;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


public class CustomFunction{
    private int start;
    private int end;


    public static synchronized Map<Integer, Double> computeDecimalLogs(int start, int n) {
        Map<Integer, Double> results = new HashMap<>();
        IntStream.rangeClosed(start, n)
                .forEach(i -> results.put(i, Math.log10(i)));
        return results;
    }
}
