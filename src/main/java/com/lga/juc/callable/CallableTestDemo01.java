package com.lga.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTestDemo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyThread myThread = new MyThread();
        FutureTask futureTask = new FutureTask(myThread);
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();

        String o = (String)futureTask.get();
        System.out.println("o = " + o);
    }


}

class MyThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() +"--call method");
        return "1234556";
    }
}
