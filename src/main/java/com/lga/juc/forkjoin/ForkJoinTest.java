package com.lga.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(test1()); //7843
//        System.out.println(test2());  //8460

    }

    public static Long test1() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinDemo01 forkJoinDemo01 = new ForkJoinDemo01(0L,20_0000_0000L);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinDemo01);
        Long result = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("exec time:" + (end - start));
        return result;
    }

    public static Long test2() {
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (long i = 0L; i <= 20_0000_0000L; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("exec time:" + (end - start));
        return sum;
    }
}
