package org.example.difficultFunction.functionClasses;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


public class CustomFunction {
    private int start;
    private int end;


    public static synchronized Map<Integer, Double> computeDecimalLogs(int start, int n) {
        Map<Integer, Double> results = new HashMap<>();
        IntStream.rangeClosed(start, n)
                .forEach(i -> results.put(i, Math.log10(i)));
        return results;
    }

    public static Map<Integer, Double> calculateFactorialsInRange(int start, double end) {
        if (start < 0 || end < 0 || start > end) {
            throw new IllegalArgumentException("Начальное значение должно быть неотрицательным и меньше или равно конечному значению");
        }

        Map<Integer, Double> factorials = new HashMap<>();
        for (int i = start; i <= end; i++) {
            factorials.put(i, factorial(i));
        }
        return factorials;
    }

    private static double factorial(int num) {
        double result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}
