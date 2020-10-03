package com.lga.juc.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockQueueTest {

    public static void main(String[] args) throws InterruptedException {
        test4();
    }

    //会抛出异常api
    public static void test1() {
        BlockingQueue bq = new ArrayBlockingQueue<>(3);
        System.out.println(bq.add("a"));
        System.out.println(bq.add("b"));
        System.out.println(bq.add("c"));
        // Queue full
        //System.out.println(bq.add("d"));


        System.out.println(bq.remove());
        System.out.println(bq.remove());
        System.out.println(bq.remove());
        //java.util.NoSuchElementException
        //System.out.println(bq.remove());
    }

    //不抛出异常
    public static void test2() {
        BlockingQueue bq = new ArrayBlockingQueue<>(3);
        System.out.println(bq.offer("a"));
        System.out.println(bq.offer("b"));
        System.out.println(bq.offer("c"));
//        System.out.println(bq.offer("d"));


        System.out.println(bq.poll());
        System.out.println(bq.poll());
        System.out.println(bq.poll());
        System.out.println(bq.poll());
    }

    //等待阻塞
    public static void test3() throws InterruptedException {
        BlockingQueue bq = new ArrayBlockingQueue<>(3);
        bq.put("a");
        bq.put("b");
        bq.put("c");
//        bq.put("d");
        System.out.println(bq.take());
        System.out.println(bq.take());
        System.out.println(bq.take());
        System.out.println(bq.take());

    }


    //超时等待
    public static void test4() throws InterruptedException {
        BlockingQueue bq = new ArrayBlockingQueue<>(3);
        bq.offer("a");
        bq.offer("b");
        bq.offer("c");
        bq.offer("d",2, TimeUnit.SECONDS);

        System.out.println(bq.poll(2, TimeUnit.SECONDS));
        System.out.println(bq.poll(2, TimeUnit.SECONDS));
        System.out.println(bq.poll(2, TimeUnit.SECONDS));
        System.out.println(bq.poll(2, TimeUnit.SECONDS));

    }


}
