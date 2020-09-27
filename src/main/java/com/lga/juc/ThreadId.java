package main.java.com.lga.juc;

import java.util.concurrent.atomic.AtomicLong;

public class ThreadId {
    private static final AtomicLong nextId = new AtomicLong(0);
    private static final ThreadLocal<Long> tl = ThreadLocal.withInitial(
            () -> nextId.getAndIncrement()
    );

    static long get() {
        return tl.get();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main thread:"+ThreadId.get());
        MyThread myThread1 = new MyThread("t1");
        MyThread myThread2 = new MyThread("t2");
        Thread thread1 = new Thread(myThread1);
        Thread thread2 = new Thread(myThread2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("main thread:"+ThreadId.get());

    }

    static class MyThread implements Runnable {
        String name;

        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            int n = 10;
            while (n-- > 0) {
                System.out.println(name + "--"+n+"--" + ThreadId.get());

            }
        }
    }
}
