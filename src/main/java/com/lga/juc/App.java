package com.lga.juc;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {

        TimeUnit.SECONDS.sleep(20);
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
