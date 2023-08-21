package com.aixchen.lock;

import java.util.concurrent.locks.Lock;

/**
 * 可重入锁是指在同一个线程在外层方法获取锁的时候，再进入该线程的内层方法会自动获取锁（前提，锁对象得是同一个对象），不会因为之前
 * 释放了就被阻塞。
 * ReentrantLock 和 Synchronized 都是可重入锁，可重入锁的一个优点是可以一定程度上避免死锁。
 *
 * 当执行monitorenter时，如果目标锁对象的计数器为0，那么说明它没有被其他线程所持有，Java虚拟机会将该锁对象的持有线程设置为当前线程
 * 并且将计数器加1.
 * 在目标锁对象的计数器不为0的情况下，如果锁对象持有线程是当前线程，那么java虚拟机会将计数器+1，否则需要等待，直至持有线程释放锁对象。
 * 当执行monitorexit时，Java虚拟机需将锁对象减1，当计数器为0代表锁已经被释放。
 */
public class ReentrantLock {
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName() + "\t -----come in ");
        m2();
        System.out.println(Thread.currentThread().getName() + "\t -----end ");
    }

    public synchronized void m2(){
        System.out.println(Thread.currentThread().getName() + "\t -----come in ");
        m3();
    }

    public synchronized void m3(){
        System.out.println(Thread.currentThread().getName() + "\t -----come in ");
    }

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        new Thread(()->{reentrantLock.m1();}).start();
    }

    private static void test1() {
        Object o = new Object();
        synchronized (o){
            new Thread(()->{ System.out.println("外层调用"); }).start();
            synchronized (o){
                new Thread(()->{ System.out.println("中层调用"); }).start();
            } synchronized (o){
                new Thread(()->{ System.out.println("内层调用"); }).start();
            }

        }
    }
}
