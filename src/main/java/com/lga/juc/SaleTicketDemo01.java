package com.lga.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketDemo01 {

    /**
     * 线程是一个单独资源类，没有任何附属的操作
     */
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> { for (int i = 0; i < 50; i++) ticket.sale(); }, "t1").start();
        new Thread(() -> { for (int i = 0; i < 20; i++) ticket.sale(); }, "t2").start();
        new Thread(() -> { for (int i = 0; i < 30; i++) ticket.sale(); }, "t3").start();
    }
}

class Ticket {
    private int count = 100;
    private Lock lock = new ReentrantLock();

//    public synchronized void sale() {
//        if (count > 0) {
//            System.out.println(Thread.currentThread().getName() + "卖了第" + count-- + "张票!还剩余" + count + "张票");
//        }
//    }

    public void sale() {
        try {
            lock.lock();
            if (count > 0) {
                System.out.println(Thread.currentThread().getName() + "卖了第" + count-- + "张票!还剩余" + count + "张票");
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

}
