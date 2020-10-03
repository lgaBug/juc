package com.lga.juc.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyCache {

    private volatile Map<Integer, Integer> cache = new HashMap<>();
    ReadWriteLock rw = new ReentrantReadWriteLock();

    public void put(Integer key, Integer val) {
        rw.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始写key" + key);
            TimeUnit.SECONDS.sleep(3);
            cache.put(key, val);
            System.out.println(Thread.currentThread().getName() + "结束写key" + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rw.writeLock().unlock();
        }

    }

    public Integer get(Integer key) {
        rw.readLock().lock();
        Integer result = null;
        try {
            System.out.println(Thread.currentThread().getName() + "开始读key:" + key);
            result = cache.get(key);
            if (result == null) {
                System.out.println("key:" + key + "不存在");
            } else {
                System.out.println(Thread.currentThread().getName() + "结束读val:" + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rw.readLock().unlock();
        }
        return result;
    }

}
