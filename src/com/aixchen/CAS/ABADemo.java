package com.aixchen.CAS;

import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;
import sun.awt.windows.ThemeReader;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
    public static void main(String[] args) {
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t 首次版本号为： " + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(100,101,
                    atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t 第一次修改之后的版本号为： " + atomicStampedReference.getStamp() + "\t 值为: " + atomicStampedReference.getReference());

            atomicStampedReference.compareAndSet(101,100,
                    atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t 第二次修改之后的版本号为： " + atomicStampedReference.getStamp() + "\t 值为: " + atomicStampedReference.getReference());
        },"a").start();

        try { TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){ e.printStackTrace(); }

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t 线程二首次进入");
            atomicStampedReference.compareAndSet(100,2024,
                    atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t 线程二第一次修改之后的版本号为： " + atomicStampedReference.getStamp() + "\t 值为: " + atomicStampedReference.getReference());
        },"b").start();
    }
}
