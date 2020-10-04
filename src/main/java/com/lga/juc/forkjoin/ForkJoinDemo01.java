package com.lga.juc.forkjoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo01 extends RecursiveTask<Long> {

    private Long start;
    private Long end;
    private Long temp = 2000L;


    public ForkJoinDemo01(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start < temp) {
            Long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            Long middle = (end +  start) / 2;
            ForkJoinDemo01 task1 = new ForkJoinDemo01(start, middle);
            task1.fork();
            ForkJoinDemo01 task2 = new ForkJoinDemo01(middle+1, end);
            task2.fork();
            return task1.join() + task2.join();
        }
    }
}
