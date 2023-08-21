package com.aixchen.MallDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureAPIDemo5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            return "playerA";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playerB";
        });

        CompletableFuture<String> apply = future1.applyToEither(future2, f -> {
            System.out.println(f + " is winner!");
            return "is winner!";
        });

//        CompletableFuture<Integer> thenCombine = future1.thenCombine(future2, (x, y) -> {
//            System.out.println("开始合并");
//            return x + y;
//        });
        System.out.println(apply.join());
    }
}
