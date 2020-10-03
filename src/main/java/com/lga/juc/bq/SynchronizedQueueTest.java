package com.lga.juc.bq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronizedQueueTest {

    public static void main(String[] args) {

        BlockingQueue<String> bq = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                bq.put("a");
                System.out.println("put element:a");
                bq.put("b");
                System.out.println("put element:b");
                bq.put("c");
                System.out.println("put element:c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("take element:"+bq.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println("take element:"+bq.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println("take element:"+bq.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();



    }
}
