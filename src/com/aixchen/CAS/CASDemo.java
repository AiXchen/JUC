package com.aixchen.CAS;

import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/*
    手写自旋锁
    缺点：循环时间长开销很大；会引起ABA问题
 */
public class CASDemo {

    AtomicReference<Thread> atomicReference= new AtomicReference<>();
    public void lock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t -------come in");
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }
    public void unLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName() + "\t -------over");
    }

    public static void main(String[] args) {
        CASDemo casDemo = new CASDemo();
        new Thread(()->{
            casDemo.lock();
            try { TimeUnit.SECONDS.sleep(5); }catch (InterruptedException e){e.printStackTrace();}
            casDemo.unLock();
        },"a").start();

        try { TimeUnit.MILLISECONDS.sleep(500); }catch (InterruptedException e){ e.printStackTrace(); }
        new Thread(()->{
            casDemo.lock();
            casDemo.unLock();
        },"b").start();
    }
}
