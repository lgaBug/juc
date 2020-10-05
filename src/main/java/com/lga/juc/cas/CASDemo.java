package com.lga.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CASDemo {


    public static void main(String[] args){

        AtomicStampedReference<Integer> atomicReference = new AtomicStampedReference<>(2020,1);

        new Thread(() -> {
            int stamp = atomicReference.getStamp();
            System.out.println("a1 stamp = " + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(2020, 2021, stamp, stamp + 1));
            System.out.println("a2 stamp = " + atomicReference.getStamp());

            stamp = atomicReference.getStamp();

            System.out.println(atomicReference.compareAndSet(2021, 2020, stamp, stamp + 1));
            System.out.println("a3 stamp = " + atomicReference.getStamp());

        },"a").start();

        new Thread(() -> {
            int stamp = atomicReference.getStamp();
            System.out.println("b1 stamp = " + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicReference.compareAndSet(2020, 2021, stamp, stamp + 1));
            System.out.println("b2 stamp = " + atomicReference.getStamp());

        },"b").start();

    }


}
