package com.aixchen.MallDemo;

import java.util.concurrent.*;

/*
调用thenRun（）方法时，如果传入了自定义线程池，那么全部线程都是用自定义线程池
调用thenRunAsync（）方法时，如果传入了自定义线程池，除了第一个线程使用的是自定义线程池，别的线程都是默认线程池
 */
public class CompletableFutureAPIDemo4 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        try{
            CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                try {TimeUnit.MILLISECONDS.sleep(20);}catch (InterruptedException e){e.printStackTrace();}
                System.out.println("一号任务" + "\t" + Thread.currentThread().getName());
                return "abc";
            },threadPool).thenRun(() -> {
                try {TimeUnit.MILLISECONDS.sleep(20);}catch (InterruptedException e){e.printStackTrace();}
                System.out.println("二号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try {TimeUnit.MILLISECONDS.sleep(10);}catch (InterruptedException e){e.printStackTrace();}
                System.out.println("三号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try {TimeUnit.MILLISECONDS.sleep(10);}catch (InterruptedException e){e.printStackTrace();}
                System.out.println("四号任务" + "\t" + Thread.currentThread().getName());
            });
            System.out.println(future.get(2,TimeUnit.SECONDS));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }
}
