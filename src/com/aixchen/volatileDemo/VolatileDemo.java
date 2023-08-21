package com.aixchen.volatileDemo;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class VolatileDemo {

    /**
     *     volatile 线程在读取的时候，每次读取都会去内存中读取共享变量的最新值，然后将其复制到工作内存当中、
     *     线程修改了工作内存中的变量副本，修改之后会立马刷新到主内存中
     *     volatile 变量不适合参与到依赖当前值的运算 如i++和++i；
     *     通常volatile 变量适合用做保存某个状态的boolean值和int值
     *
     *     对于volatile变量，JVM只是保证了从主内存加载到工作内存的值是相同的，但如果第二个线程在第一个线程读取旧值
     *     和写回新值之间读取i的值，那么就会导致线程安全问题
     */
    //static boolean flag = true;
    static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t -----come in");
            while (flag){

            }
            System.out.println(Thread.currentThread().getName() + "\t ----flag 被修改为false");
        },"a").start();

        try { TimeUnit.SECONDS.sleep(2); }catch (InterruptedException e){e.printStackTrace();}

        flag = false;
        System.out.println(Thread.currentThread().getName() + "\t" + flag);

    }
}
