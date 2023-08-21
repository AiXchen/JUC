package com.aixchen.completableFuture;

import java.util.concurrent.*;

public class CompletableFutureDemo2 {
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            int result = ThreadLocalRandom.current().nextInt(10);
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            System.out.println("一秒后的结果为---->" + result);
//            return result;
//        });
//        System.out.println("main线程忙别的事");
//        System.out.println(future.get());
//    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("一秒后的结果为---->" + result);
            return result;
        },executorService).whenComplete((v,e)->{
                if(e == null){
                    System.out.println("计算完成 + " + v);
                }
    }).exceptionally(e->{
        e.printStackTrace();
            System.out.println("出现异常情况");
            return null;
        });

        System.out.println("main线程去忙别的事情");
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
