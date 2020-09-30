package com.lga.juc;

import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {

    private String name;

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(()->{
            //System.out.println(name+"--"+Thread.currentThread().getName());
        });
    }

    public NamedThreadFactory(String name) {
        this.name = name;
    }
}
