package com.czw.concurrent;

import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * java中提供的新的异步回调的合成和组合事件处理方式
 * <p>
 * 场景:从不同的db中异步获取数据,并最终汇集.基于thenComposeAsync
 *
 * @author ZeviChen , 2017/6/5 10:48
 */
public class TestCompletableFuture {

    @Test
    @Ignore
    public void test1() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<String> resultCompletableFuture = CompletableFuture.supplyAsync(() -> {

            System.out.println(Thread.currentThread().getName() + " running");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName() + " done";
        }, executor);
        System.out.println("main thread Get:" + resultCompletableFuture.get());
    }

    /**
     * 结果完成时的处理
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    @Ignore
    public void testWhenComplete() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<String> resultCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }, executor);
        System.out.println(resultCompletableFuture.whenCompleteAsync((t, e) ->
                System.out.println(Thread.currentThread().getName() + " result:" + t)));
        System.out.println("whenComplete done.");
    }

    /**
     * 纯消费(执行action)
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    @Ignore
    public void testThenAccept() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<String> resultCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }, executor);
        resultCompletableFuture.thenAcceptAsync(System.out::println);
        System.out.println("thenAccept done.");
    }

    /**
     * thenRun不使用CompletableFuture的返回结果,另外的Runnable在future result后执行
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    @Ignore
    public void testThenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<Void> f = future.thenRun(() -> System.out.println("finished"));
        System.out.println(f.get());

    }

    /**
     * 组合多个Future的结果
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    @Ignore
    public void testThenAcceptBoth() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<Void> f = future.thenAcceptBoth(CompletableFuture.completedFuture(10), (x, y) -> System.out.println(x * y));
        System.out.println(f.get());
    }

    /**
     * runAsync不返回执行结果,如果没有executor线程池,默认使用ForkJoinPool.commonPool().
     * Async后缀是异步方法
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    @Ignore
    public void test4() throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(() -> work("a1")).runAfterEither(
                CompletableFuture.runAsync(() -> work("b1")), () -> work("c1")).get();
    }

    Random rdm = new Random();

    public Void work(String name) {
        System.out.println(name + " starts at " + LocalTime.now());
        try {
            TimeUnit.SECONDS.sleep(rdm.nextInt(8));
        } catch (InterruptedException e) {
        }
        System.out.println(name + " ends at " + LocalTime.now());
        return null;
    }

    CompletableFuture<String> future = new CompletableFuture<>();

    @Test
    @Ignore
    public void test5() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> future1());
        executor.execute(() -> future2());
    }

    public void future1() {
        System.out.println("get doing.");
        try {
            System.out.println("Get:" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("get done.");
    }

    public void future2() {
        System.out.println("complete doing.");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        future.complete("done.");
        System.out.println("complete done.");
    }


    @Test
    @Ignore
    public void test6() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newFixedThreadPool(5);
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            return "zero";
        }, executor);

        CompletableFuture<Integer> f2 = f1.thenApply(t -> {
            System.out.println(2);
            return Integer.valueOf(t.length());
        });

        CompletableFuture<Double> f3 = f2.thenApply(r -> r * 2.0);
        System.out.println(f3.get());
    }

    /**
     * 异步对数据进行转换和后续处理
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    @Ignore
    public void testThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<Double> f = CompletableFuture.supplyAsync(() -> "4")
                .thenApply(Integer::parseInt)
                .thenApply(r -> r * r * Math.PI);
        System.out.println("PI * 4 ^ 2 = " + f.get());
    }


    /**
     * tenComposeAsync链式操作返回CompletableFuture
     * <p>
     * 组合
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    @Ignore
    public void testThenComposeAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> f = CompletableFuture.supplyAsync(() -> longTask(1000000))
                .thenComposeAsync(TestCompletableFuture::getResultFuture);
        Long result = f.get();
        System.out.println(result);
    }

    public Optional<List<Integer>> longTask(Integer i) {
        if (i > 0) {
            List<Integer> list = new ArrayList<>();
            for (int pc = 0; pc < i; pc++)
                list.add(pc);
            return Optional.of(list);
        } else
            return Optional.empty();
    }

    public static CompletableFuture<Long> getResultFuture(Optional<List<Integer>> op) {
        return CompletableFuture.supplyAsync(() -> {
            if (op.isPresent())
                return op.get().stream()
                        .map(Integer::toUnsignedLong)
                        .reduce(0L, (x, y) -> x + y);
            else
                return -1L;
        });
    }

    @Test
    @Ignore
    public void testThenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "abc");
        CompletableFuture<String> f = future.thenCombine(future2, (x, y) -> y + "-" + x);
        System.out.println(f.get()); //abc-100
    }


    @Test
//    @Ignore
    public void testAllofAndAnyOf() {
        CompletableFuture[] futures = {CompletableFuture.supplyAsync(TestCompletableFuture::randomDelay),
                CompletableFuture.supplyAsync(TestCompletableFuture::randomDelay),
                CompletableFuture.supplyAsync(TestCompletableFuture::randomDelay)};

//        CompletableFuture.allOf(futures).join();
        CompletableFuture.anyOf(futures).join();

        System.out.println("all timeout process end");
    }

    private static final Random random = new Random();

    public static String randomDelay() {
        int delay = 500 + random.nextInt(2000);
        try {
            //How to use String.format. Ref:http://blog.csdn.net/lonely_fireworks/article/details/7962171/
            System.out.println(String.format("%s sleep in %d", Thread.currentThread().getName(), delay));
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%s sleep in %s", Thread.currentThread().getName(), "end"));
        return Thread.currentThread().getName() + " return";
    }

    /**
     * acceptEither方法是当任意一个CompletionStage完成的时候，action这个消费者就会被执行。这个方法返回CompletableFuture<Void>
     * applyToEither方法是当任意一个CompletionStage完成的时候，fn会被执行，它的返回值会当作新的CompletableFuture<U>的计算结果
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    @Ignore
    public void testThenEither() throws ExecutionException, InterruptedException {
        Random rand = new Random();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {

                TimeUnit.MILLISECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        });
        CompletableFuture<String> f = future.applyToEither(future2, i -> i.toString());
        System.out.println(f.get());
    }

}

