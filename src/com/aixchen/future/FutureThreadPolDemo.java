package com.aixchen.future;

import org.omg.CORBA.SystemException;

import java.util.concurrent.*;

public class FutureThreadPolDemo {
//    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//
//        try { TimeUnit.MILLISECONDS.sleep(500); }catch (InterruptedException e){ e.printStackTrace(); }
//        try { TimeUnit.MILLISECONDS.sleep(300); }catch (InterruptedException e){ e.printStackTrace(); }
//        try { TimeUnit.MILLISECONDS.sleep(300); }catch (InterruptedException e){ e.printStackTrace(); }
//        long end = System.currentTimeMillis();
//
//        System.out.println("耗时：" + (end -start));
//    }

    /*
    get()容易刀子和阻塞，一般放在程序最后面、
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        FutureTask<String> futureTask1 = new FutureTask<String>(()->{
            try { TimeUnit.MILLISECONDS.sleep(500); }catch (InterruptedException e){ e.printStackTrace(); }
            return "task1 over...";
        });
        threadPool.submit(futureTask1);

        FutureTask<String> futureTask2 = new FutureTask<String>(()->{
            try { TimeUnit.MILLISECONDS.sleep(300); }catch (InterruptedException e){ e.printStackTrace(); }
            return "task2 over...";
        });
        threadPool.submit(futureTask2);

        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());

        try { TimeUnit.MILLISECONDS.sleep(300); }catch (InterruptedException e){ e.printStackTrace(); }

        long end = System.currentTimeMillis();
        System.out.println("耗时---》" + (end - start));

        threadPool.shutdown();
    }

}
