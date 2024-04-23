import java.util.Scanner;

import static java.lang.StringTemplate.STR;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var scanner = new Scanner(System.in);

        System.out.println("Початок інтервалу: ");
        var start = scanner.nextDouble();

        System.out.println("Кінець інтервалу: ");
        var end = scanner.nextDouble();

        System.out.println("Кількість кроків: ");
        var n = scanner.nextInt();

        System.out.println("Кількість потоків: ");
        var threadCount = scanner.nextInt();

        var threads = new MultithreadedIntegralCalculator[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new MultithreadedIntegralCalculator(threadCount, i, start, end, n, new Function());

            threads[i].start();
        }

        var start1 = System.nanoTime();
        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

        double sum = 0;
        for (int i = 0; i < threadCount; i++) {
            sum += threads[i].getSum();
        }
        var end1 = System.nanoTime();

        System.out.println(sum);
        System.out.println(STR."Багаточний код зайняв \{end1 - start1}ns на виконання");

        var integralCalculator = new SinglethreadedIntegralCalculator(start, end, n, new Function());

        var start2 = System.nanoTime();
        var sum2 = integralCalculator.getSum();
        var end2 = System.nanoTime();

        System.out.println(sum2);
        System.out.println(STR."Однопоточний код зайняв \{end2 - start2}ns на виконання");
    }
}
