package com.czw.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 
 * @author ZeviChen , 2017/6/2 18:15
 */
public class TestFibonacciTaskTraces {

    // it makes no sense to create more threads than available cores (no speed improvement here)
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    // create thread pool
    private final ForkJoinPool pool = new ForkJoinPool(AVAILABLE_PROCESSORS);

    @Test
    public void testFibonacciArrayTraces() {

        // more test data: http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/fibtable.html
        long results[] = { 0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L };
        for (int inputValue = 0; inputValue < results.length; inputValue++) {

            final FibonacciTaskTraces task = new FibonacciTaskTraces(inputValue, " | ");
            System.out.println("invoke Fibonacci(" + inputValue + ")  <- " + Thread.currentThread().getName());

            final long result = pool.invoke(task);
            System.out.println("result = " + result + "\n");

            Assert.assertEquals(results[inputValue], result);
        }
    }
}

class FibonacciTaskTraces extends RecursiveTask<Long> {

    private static final long serialVersionUID = 1L;

    // just needed to format debug output
    public static final String OUTPUT_PREFIX = " | ";

    private final String prefix;

    private final long inputValue;

    public FibonacciTaskTraces(long inputValue, final String prefix) {
        this.inputValue = inputValue;
        this.prefix = prefix;
    }

    @Override
    public Long compute() {

        if (inputValue == 0L) {
            slowTask();
            return 0L;
        } else if (inputValue <= 2L) {
            slowTask();
            return 1L;
        } else {
            final long firstValue = inputValue - 1L;
            System.out.println(prefix + " - Fibonacci(" + firstValue + ") <- " + Thread.currentThread().getName()
                    + " (fork) ");
            final FibonacciTaskTraces firstWorker = new FibonacciTaskTraces(firstValue, prefix + OUTPUT_PREFIX);
            firstWorker.fork();

            final long secondValue = inputValue - 2L;
            System.out.println(prefix + " - Fibonacci(" + secondValue + ") <- " + Thread.currentThread().getName());
            final FibonacciTaskTraces secondWorker = new FibonacciTaskTraces(secondValue, prefix + OUTPUT_PREFIX);

            long result = secondWorker.compute() + firstWorker.join();
            System.out.println(prefix + " - Fibonacci(" + inputValue + ") = " + result + " <- "
                    + Thread.currentThread().getName() + " (join)");
            slowTask();

            return result;
        }
    }

    /** just simulate a longer running task (with out disturbing the other threads) */
    private void slowTask() {
        for (int k = 0, i = 0; i < 1000 * 1000 * 100; i++) {
            i = i + k;
        }
    }
}
