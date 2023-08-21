package com.aixchen.completableFuture;

import java.util.concurrent.*;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);


        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            try{ TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){e.printStackTrace();}
            return "hello future";
        },executorService);
        System.out.println(future.get());

//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
//            System.out.println(Thread.currentThread().getName());
//            try{ TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){e.printStackTrace();}
//        },executorService);
//        System.out.println(completableFuture.get());
        executorService.shutdown();
    }


}
