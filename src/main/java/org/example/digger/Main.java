package org.example.digger;
//1 уровень сложности: 1 Создайте класс Траншея. У траншеи есть целевая
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

        Digger digger1 = new Digger(new Trench(), "Digger1");
        Digger digger2 = new Digger(new Trench(), "Digger2");
        Digger digger3 = new Digger(new Trench(), "Digger2");
        Thread thread1 = new Thread(digger1);
        Thread thread2 = new Thread(digger2);
        Thread thread3 = new Thread(digger3);

        long startTime = System.nanoTime();
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        System.out.println("Время выполнения: " + duration + " мс");
        /*---------------*/
        Trench.givenLength = 10; // удваиваем длину траншеи т.к. копает один
        startTime = System.nanoTime();
        thread3.start();
        try {
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1_000_000;
        System.out.println("Время выполнения: " + duration + " мс");
    }
}