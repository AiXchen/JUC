package com.aixchen.Interrupt;

import org.omg.PortableServer.THREAD_POLICY_ID;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class InterruptDemo {
    static volatile boolean isStop = false;

    public static void main(String[] args) {
        //实例方法interrupt()仅仅是设置线程的标志位为true，不会停止线程
        //中断不活动的线程不会产生任何影响

        //如果该线程阻塞的调用wait（），join（），sleep（）等方法，那么它的中断标识位将会被清除，并报出IntereuptedException
        Thread a = new Thread(()->{
            while(true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName() + "\t 程序停止");
                    break;
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();//??? 中断标识位被清除，需要重新设置为true，程序才会停止
                }
                System.out.println("t1 ----hello");
            }
        },"a");
        a.start();

        new Thread(()->{
            a.interrupt();
        },"b").start();
    }

    private static void m2() {
        Thread a = new Thread(()->{
            while (true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName() + "\t isInterrupted()被修改为true，程序停止");
                    break;
                }
                System.out.println("a----isInterrupted api");
            }
        },"a");
        a.start();

        new Thread(()->{
            a.interrupt();
        },"b").start();
    }

    private static void m1() {
        new Thread(()->{
            while(true){
                if(isStop){
                    System.out.println(Thread.currentThread().getName() + "\t 程序停止");
                    break;
                }
                System.out.println("t1 ----hello");
            }
        },"a").start();

        new Thread(()->{
            isStop = true;
        },"b").start();
    }
}
