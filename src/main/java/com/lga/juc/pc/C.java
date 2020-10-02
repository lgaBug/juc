package com.lga.juc.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 执行顺序 A->B->C
 */
public class C {

    public static void main(String[] args) {

        Data3 data3 = new Data3();
        new Thread(()->{
            int n = 10;
            while (n-->0)
            data3.printA();
            },"A").start();
        new Thread(()->{
            int n = 10;
            while (n-->0)
            data3.printB();},"B").start();
        new Thread(()->{
            int n = 10;
            while (n-->0)
            data3.printC();},"C").start();
    }



}

class Data3 {

    private Lock lock = new ReentrantLock();
    private int number = 1;
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printA() {

        lock.lock();
        try {
            //判断、执行、通知
            while (number !=1) condition1.await();
            System.out.println(Thread.currentThread().getName() + "---AAAAA");
            number = 2;
            condition2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            //判断、执行、通知
            while (number !=2) condition2.await();
            System.out.println(Thread.currentThread().getName() + "---BBB");
            number = 3;
            condition3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            //判断、执行、通知
            while (number !=3) condition3.await();
            System.out.println(Thread.currentThread().getName() + "---CCCC");
            number = 1;
            condition1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
