package com.lga.juc.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory()
        );

        printThreadPoolInfo(executorService);


        executorService.execute(()->{
            System.out.println("first execute thread pool");
        });

        TimeUnit.SECONDS.sleep(2);
        printThreadPoolInfo(executorService);



        executorService.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {          
                e.printStackTrace();
            }
            System.out.println("second execute thread pool");
        });

        TimeUnit.SECONDS.sleep(2);
        printThreadPoolInfo(executorService);

    }

    public static void printThreadPoolInfo(ThreadPoolExecutor threadPoolExecutor) {
        System.out.println(threadPoolExecutor.getActiveCount());
        System.out.println(threadPoolExecutor.getCorePoolSize());
        System.out.println(threadPoolExecutor.getPoolSize());
        System.out.println(threadPoolExecutor.getTaskCount());
        System.out.println(threadPoolExecutor.getCompletedTaskCount());
    }
}
