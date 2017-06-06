package com.czw.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Ref:https://www.javacodegeeks.com/2012/04/fork-and-join-in-java-7-jsr-166.html
 * <p>
 * 池中的线程尝试去执行其他线程创建的子任务,提高了线程的利用率
 *
 * @author ZeviChen , 2017/6/2 18:03
 */
public class TestFibonacciTask {
    // it makes no sense to create more threads than available cores (no speed improvement here)
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private final ForkJoinPool pool = new ForkJoinPool(AVAILABLE_PROCESSORS);

    @Test
    public void testFibonacciArray() {

        // more test data: http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/fibtable.html
        long results[] = {0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L, 144L, 233L, 377L, 610L, 987L, 1597L,
                2584L, 4181L, 6765L};
        for (int inputValue = 0; inputValue < results.length; inputValue++) {

            final FibonacciTask task = new FibonacciTask(inputValue);
            System.out.print("Fibonacci(" + inputValue + ") = ");

            final long result = pool.invoke(task);
            System.out.println(result);

            Assert.assertEquals(results[inputValue], result);
        }
    }

}


class FibonacciTask extends RecursiveTask<Long> {

    private static final long serialVersionUID = 1L;

    private final long inputValue;

    public FibonacciTask(long inputValue) {
        this.inputValue = inputValue;
    }

    @Override
    public Long compute() {

        if (inputValue == 0L) {
            return 0L;
        } else if (inputValue <= 2L) {
            return 1L;
        } else {
            final FibonacciTask firstWorker = new FibonacciTask(inputValue - 1L);
            firstWorker.fork();

            final FibonacciTask secondWorker = new FibonacciTask(inputValue - 2L);
            return secondWorker.compute() + firstWorker.join();
        }
    }
}
