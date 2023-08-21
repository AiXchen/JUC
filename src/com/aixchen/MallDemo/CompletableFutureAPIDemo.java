package com.aixchen.MallDemo;

import java.sql.Time;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "xchen";
        });
//        System.out.println(future.get());
//        System.out.println(future.get(2,TimeUnit.SECONDS));
//        System.out.println(future.join());

        /*getNow():没有计算完成的情况下就给一个替代的结果：xxx；计算完就给xchen*/
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        //System.out.println(future.getNow("xxx"));

        /*当get 方法被阻塞的时候，complete方法就是结束阻塞并直接获取complete（）中的值*/
        System.out.println(future.complete("xxxchen") + "\t" + future.join());

    }
}
