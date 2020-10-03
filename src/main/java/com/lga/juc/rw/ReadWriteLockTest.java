package com.lga.juc.rw;

public class ReadWriteLockTest {

    public static void main(String[] args) {


        MyCache myCache = new MyCache();
        for (int i = 1; i < 30; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.get(0);
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 1; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp, temp + 100);
            },String.valueOf(i)).start();
        }


    }


}
