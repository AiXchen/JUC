package com.aixchen.lockSuppport;

import com.aixchen.lock.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class LocakSupportDemo {

    public static void main(String[] args) {
        /**
         * park（）方法和unpark（）方法实现阻塞线程和解除线程阻塞的过程
         * LockSupport和每个使用它的线程都有一个许可（permit）关联
         * 线程阻塞（park）需要消耗凭证（permit）,unpark方法是给线程添加一个凭证，凭证最多只有一个
         */
       Thread a = new Thread(()->{
           try {TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e){e.printStackTrace();}
           System.out.println(Thread.currentThread().getName() + "\t -----come in");
           LockSupport.park();
           System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
       },"a");
       a.start();

       try {TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e){e.printStackTrace();}

       new Thread(()->{
           LockSupport.unpark(a);
           System.out.println(Thread.currentThread().getName() + "\t -----发出通知");
       },"b").start();

    }

    private static void awaitAndSingle() {
        Lock lock = (Lock) new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t -------come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t -------被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"a").start();

        try { TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e){e.printStackTrace();}

        new Thread(()->{
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t 发出通知");
            }finally {
                lock.unlock();
            }
        },"b").start();
    }

    private static void waitNotifyDemo() {
        //wait()和notify()方法必须要在同步代码块或者方法里，并且要成对出现。必须先wait（）再notify（）
        Object o = new Object();
        new Thread(()->{
            synchronized (o){
                System.out.println(Thread.currentThread().getName() + "\t ------come in");
                try {
                    o.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t-----被唤醒");
            }
        },"a").start();

        try { TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){e.printStackTrace();}

        new Thread(()->{
            synchronized (o){
                o.notify();
                System.out.println(Thread.currentThread().getName() + "\t-----发出通知");
            }
        },"b").start();
    }
}
