package com.lga.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(()->1+2);
        ExecutorService es = Executors.newCachedThreadPool();
        es.submit(futureTask);
        Integer ans = (Integer) futureTask.get();
        System.out.println("ans = " + ans);
    }
}
