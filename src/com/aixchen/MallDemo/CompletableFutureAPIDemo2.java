package com.aixchen.MallDemo;

import java.util.concurrent.*;


/**
 * thenApply 与 handle
 * 都是用于对计算结果值进行处理的
 *thenApply 出现了异常就直接抛出
 * handle 出现异常还会进行下一步
 */
public class CompletableFutureAPIDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
//
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
//            System.out.println("111");
//            return 1;
//        },executorService).thenApply(f->{
//            int i = 10/0;
//            System.out.println("222");
//            return f + 2;
//        }).thenApply(f ->{
//            System.out.println("333");
//            return f + 3;
//        }).thenApply(f -> {
//            System.out.println("444");
//            return f + 4;
//        }).whenComplete((v,e)->{
//            if(e == null){
//                System.out.println("计算结果为：" + v);
//            }
//        }).exceptionally(e ->{
//            e.printStackTrace();
//            return null;
//        });
//
//        System.out.println(Thread.currentThread().getName() + "------主线程忙别的任务");
//        executorService.shutdown();

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("111");
            return 1;
        },executorService).handle((f,e)->{
            int i = 10/0;
            System.out.println("222");
            return f + 2;
        }).handle((f,e)->{
            System.out.println("333");
            return f + 3;
        }).handle((f,e)->{
            System.out.println("444");
            return f + 4;
        }).whenComplete((v,e)->{
            if(e == null){
                System.out.println("计算结果为：" + v);
            }
        }).exceptionally(e ->{
            e.printStackTrace();
            return null;
        });

        System.out.println(Thread.currentThread().getName() + "------主线程忙别的任务");
        executorService.shutdown();
    }
}
