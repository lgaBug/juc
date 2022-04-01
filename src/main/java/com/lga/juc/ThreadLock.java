package com.lga.juc;

import java.util.concurrent.*;

public class ThreadLock {


    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,20 ,60 ,TimeUnit.SECONDS,new LinkedBlockingDeque<>());

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(8);

        for (int i = 0; i < 8; i++) {

            threadPoolExecutor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "，父线程任务开始执行");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                CountDownLatch sublatch = new CountDownLatch(1);
                threadPoolExecutor.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + "，子线程任务开始执行");
                    sublatch.countDown();
                });

                try {
                    sublatch.await();
                    System.out.println("子线程执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();

            });

        }
        latch.await();
        threadPoolExecutor.shutdown();
    }

}