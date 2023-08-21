package com.aixchen.MallDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/*
对计算结果进行消费
thenRun(Runnable runnable):任务A执行完执行B，并且B不需要A的结果
thenAccept(Consumer action):任务A执行完执行B，B需要A的结果，但是B无返回值
thenApply(Function fn):任务A执行完执行B，B需要A的结果，同时B有返回值
 */
public class CompletableFutureAPIDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
//            return 1;
//        }).thenApply(f->{
//            return f + 1;
//        }).thenApply(f -> {
//            return f + 2;
//        }).thenAccept(r ->{
//            System.out.println(r);
//        });

        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> { }).join());
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept(r -> System.out.println(r)).join());
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply(r -> r + "\t" +"resultB").join());
    };
}
