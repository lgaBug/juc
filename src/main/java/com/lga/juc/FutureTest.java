package com.lga.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(1);

        //创建result 对象r
        StringBuilder r =  new StringBuilder("complete");
        Future<StringBuilder> future = executor.submit(new Task(r), r);
        StringBuilder s = future.get();
        System.out.println("s = " + s.toString());
    }

    private static class Task implements Runnable{
        private StringBuilder  r;
        @Override
        public void run() {
            System.out.println("start run ....");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r = r.append("...lga");
            System.out.println("end run....");
        }

        public Task(StringBuilder  r) {
            this.r = r;
        }
    }
}
