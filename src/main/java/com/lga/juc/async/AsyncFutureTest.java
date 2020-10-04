package com.lga.juc.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class AsyncFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test2();
    }

    /**
     * 没有返回值
     */
    public static void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步执行。。。");
        });

        System.out.println("main ....");
        completableFuture.get();
    }

    public static void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步执行。。。");
//            int i = 10 / 0;

            return 200L;
        });

        System.out.println("main ....");

        System.out.println(completableFuture.whenComplete((t, u) -> {
            System.out.println("执行成功:" + t);
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 500L;
        }).get());
    }


}
