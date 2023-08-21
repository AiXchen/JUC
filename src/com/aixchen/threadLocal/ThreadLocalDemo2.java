package com.aixchen.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyDate{
    ThreadLocal<Integer> threadLocalfield = ThreadLocal.withInitial(() -> 0);
    public void add(){
        threadLocalfield.set(1 + threadLocalfield.get());
    }
}

public class ThreadLocalDemo2 {
    public static void main(String[] args) {
        MyDate myDate = new MyDate();
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            for (int i = 0; i < 10; i++) {
                executorService.submit(()->{
                    try {
                        Integer before = myDate.threadLocalfield.get();
                        myDate.add();
                        Integer after = myDate.threadLocalfield.get();
                        System.out.println(Thread.currentThread().getName() + "\t before :" + before + "\t after: " + after);
                    }finally {
                        myDate.threadLocalfield.remove();
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
