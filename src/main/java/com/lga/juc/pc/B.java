package com.lga.juc.pc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class B {
    public static void main(String[] args) {
        Data1 data = new Data1();
        new Thread(() -> {
            for (int i = 0; i < 10; i++)
                try {
                    data.increament();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++)
                try {
                    data.decreament();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++)
                try {
                    data.increament();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++)
                try {
                    data.decreament();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }, "D").start();
    }

}

class Data1 {
    Lock lock = new ReentrantLock();
    Condition notZero = lock.newCondition();
    Condition notOne = lock.newCondition();

    private int count = 0;

    public void increament() throws InterruptedException {
        lock.lock();
        try {
            while (count != 0) {
                notZero.await();
            }
            count++;
            System.out.println(Thread.currentThread().getName() + " --- count:" + count);
            TimeUnit.SECONDS.sleep(5);
            notOne.signalAll();

        } finally {
            lock.unlock();
        }
    }

    public void decreament() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notOne.await();
            }
            count--;
            System.out.println(Thread.currentThread().getName() + " --- count:" + count);
//            TimeUnit.SECONDS.sleep(2);
            notZero.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
