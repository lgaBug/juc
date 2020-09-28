package com.lga.juc.pc;

public class A {

    public static void main(String[] args) {

        Data data = new Data();
        new Thread(() -> { for (int i = 0; i < 10; i++) try { data.increament(); } catch (InterruptedException e) { e.printStackTrace(); } },"A").start();
        new Thread(() -> { for (int i = 0; i < 10; i++) try { data.decreament(); } catch (InterruptedException e) { e.printStackTrace(); } }, "B").start();
        new Thread(() -> { for (int i = 0; i < 10; i++) try { data.increament(); } catch (InterruptedException e) { e.printStackTrace(); } },"C").start();
        new Thread(() -> { for (int i = 0; i < 10; i++) try { data.decreament(); } catch (InterruptedException e) { e.printStackTrace(); } }, "D").start();
    }


}

class Data {
    private int count = 0;
    public synchronized void increament() throws InterruptedException {
        while (count != 0) {
            this.wait();
        }
        count++;
        System.out.println(Thread.currentThread().getName()+ " --- count:"+count);
        this.notifyAll();
    }
    public synchronized void decreament() throws InterruptedException {
        while (count == 0) {
            this.wait();
        }
        count--;
        System.out.println(Thread.currentThread().getName()+ " --- count:"+count);
        this.notifyAll();
    }
}
