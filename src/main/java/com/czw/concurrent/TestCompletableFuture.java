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
 *
 * 场景:从不同的db中异步获取数据,并最终汇集.基于thenComposeAsync
 *
 * @author ZeviChen , 2017/6/5 10:48
 */
public class TestCompletableFuture {

    @Test
    @Ignore
    public void test1() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<String> resultCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {

                System.out.println(Thread.currentThread().getName() + " running");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Thread.currentThread().getName() + " done";
            }
        }, executor);
        System.out.println("main thread Get:" + resultCompletableFuture.get());
    }

    @Test
    @Ignore
    public void test2() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<String> resultCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "hello";
            }
        }, executor);
        System.out.println(resultCompletableFuture.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String t) {
                System.out.println(Thread.currentThread().getName() + " result:" + t);
            }
        }));
        System.out.println(123);
    }

    @Test
    @Ignore
    public void test3() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<String> resultCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "hello";
            }
        }, executor);
        resultCompletableFuture.thenAcceptAsync(new Consumer<String>() {
            @Override
            public void accept(String t) {
                System.out.println(t);
                System.out.println(Thread.currentThread().getName());
            }
        }, executor);
        System.out.println(123);
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

    Random random = new Random();

    public Void work(String name) {
        System.out.println(name + " starts at " + LocalTime.now());
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(8));
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

        CompletableFuture<Integer> f2 = f1.thenApply(new Function<String, Integer>() {
            @Override
            public Integer apply(String t) {
                System.out.println(2);
                return Integer.valueOf(t.length());
            }
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
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testThenComposeAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> f = CompletableFuture.supplyAsync(() -> longTask(1000000))
                .thenComposeAsync(TestCompletableFuture::getResultFuture);
        Long result = f.get();
        System.out.println(result);
    }
    public Optional<List<Integer>> longTask(Integer i) {
        if (i > 0) {
            List<Integer> list = new ArrayList<>();
            for(int pc = 0; pc < i; pc++)
                list.add(pc);
            return Optional.of(list);
        }
        else
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
}

