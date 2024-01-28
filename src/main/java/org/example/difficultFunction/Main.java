package org.example.difficultFunction;

import org.example.difficultFunction.functionClasses.CustomFunction;

import java.util.*;

//2 Напишите программу, которая вычисляет какую-либо сложную функцию для каждого
// целого числа от 1 до N, N – входной параметр (большое число, например, 10 000 000)
// N – ввод с консоли. Результат выводится на экран. Поскольку N – большое,
// необходимо разбить вычисления на несколько частей и каждую часть вычислить
// в отдельном потоке параллельно. Для каждой части нужно создать объект Task,
// внутри которого запомнить данные для начала вычислений, а так же сохранить
// результат после завершения вычислений. Каждый поток работает со своим объектом Task.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Pls input number of elements for compute log(n): ");
        int nums = sc.nextInt();
        long startTime = System.currentTimeMillis();
      //  CustomFunction.computeDecimalLogs(1, nums);
        CustomFunction.calculateFactorialsInRange(1, nums);
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        System.out.println(executeTime);
        //    System.out.println(CustomFunction.computeDecimalLogs(1, nums));

        // I had OutOfMemory exception on 30M elements
        /*------------------------------------------------------*/
        int numThreads = Runtime.getRuntime().availableProcessors();
        System.out.println(Arrays.toString(divideNToThreads(nums, numThreads)));

        int[] arrOfParts = divideNToThreads(nums, numThreads);
        double onePart = (double) nums / numThreads;
        Map<Integer, Double> result = new HashMap<>();
        List<Thread> threads = new ArrayList<>();

        startTime = System.currentTimeMillis();
        for (int el : arrOfParts) {
            Thread thread = new Thread(() -> {
           //   Map<Integer, Double> part = CustomFunction.computeDecimalLogs(el - (int) onePart + 1, el);
               Map<Integer, Double> part = CustomFunction.calculateFactorialsInRange(el - (int) onePart + 1, el);

                result.putAll(part);

            });
            thread.start();
            threads.add(thread);
        }
        for (Thread th : threads) {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        endTime = System.currentTimeMillis();
        executeTime = endTime - startTime;
        System.out.println(executeTime);
// The time obtained with multithreading was longer - about twice.
// With trying compute factorial method I had a big difference in time for multithreading
        //     System.out.println(result);
    }

    public static int[] divideNToThreads(int n, int numThreads) {
        int arrOfParts[] = new int[numThreads];
        double onePart = n / numThreads;
        for (int i = 0; i < arrOfParts.length; i++) {
            if (i == 0) arrOfParts[i] = (int) onePart;
            else if (i == (arrOfParts.length - 1)) {
                arrOfParts[i] = n;
            } else arrOfParts[i] = (int) onePart + arrOfParts[i - 1];


        }
        return arrOfParts;
    }
}

