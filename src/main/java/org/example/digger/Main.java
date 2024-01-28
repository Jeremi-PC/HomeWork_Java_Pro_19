package org.example.digger;
// 1 уровень сложности: 1 Создайте класс Траншея. У траншеи есть целевая
// длина и текущая длина. Создайте класс Землекоп, объекты которого копают
// траншею (увеличивают текущую длину), пока не будет достигнута целевая длина.
// Каждый землекоп может прокопать 1 м траншеи, а затем он отдыхает 10 секунд.
// В программе создайте траншею и двух землекопов. Измерьте, за какое время
// траншею прокопает один землекоп и за какое время с такой же траншеей управятся двое.

//2 Напишите программу, которая вычисляет какую-либо сложную функцию для каждого
// целого числа от 1 до N, N – входной параметр (большое число, например, 10 000 000)
// N – ввод с консоли. Результат выводится на экран. Поскольку N – большое,
// необходимо разбить вычисления на несколько частей и каждую часть вычислить
// в отдельном потоке параллельно. Для каждой части нужно создать объект Task,
// внутри которого запомнить данные для начала вычислений, а так же сохранить
// результат после завершения вычислений. Каждый поток работает со своим объектом Task.

// Примеры функций см. в презентации в дискорд.
public class Main {
    public static void main(String[] args) {

        Trench trench = new Trench(5);
        Trench trench1 = new Trench(5);
        Runnable digging = () -> {
            String name = Thread.currentThread().getName();
            while (trench.dig() > 0) {
                System.out.println(name + " dug 1 meter");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread digger1 = new Thread(digging, "Digger1");
        Thread digger2 = new Thread(digging, "Digger2");

        Thread digger3 = new Thread(() -> {
            String name = Thread.currentThread().getName();
            while (trench1.dig() > 0) {
                System.out.println(name + " dug 1 meter");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "digger3");

        long startTime = System.currentTimeMillis();
        digger1.start();
        digger2.start();
        try {
            digger1.join();
            digger2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println(executionTime);

        startTime = System.currentTimeMillis();
        digger3.start();
        try {
            digger3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        System.out.println(executionTime);
    }
}