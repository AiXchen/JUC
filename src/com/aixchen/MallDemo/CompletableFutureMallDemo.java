package com.aixchen.MallDemo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CompletableFutureMallDemo {

    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("taobao")
    );

    public static List<String> getPrice(List<NetMall> list,String productName){
        return list
                .stream()
                .map(netMall -> String.format(productName + " in %s price is %.2f",
                        netMall.getNetMallName(),
                        netMall.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    public static List<String> getPriceByCompletableFuture(List<NetMall> list,String productName) {
        return list.stream()
                .map(netMall ->
                        CompletableFuture.supplyAsync(() -> String.format(productName + " in %s price is %.2f",
                                netMall.getNetMallName(),
                                netMall.calcPrice(productName))))
                .collect(Collectors.toList())
                .stream()
                .map( s -> s.join())
                .collect(Collectors.toList());

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        List<String> lsit = getPrice(list, "mysql");
        for (String s : lsit) {
            System.out.println(s);
        }

        long end = System.currentTimeMillis();
        System.out.println("耗时为：" + (end - start));

        System.out.println("------------------------------");

        long start2 = System.currentTimeMillis();

        List<String> lsit2 = getPriceByCompletableFuture(list, "mysql");
        for (String s : lsit2) {
            System.out.println(s);
        }

        long end2 = System.currentTimeMillis();
        System.out.println("耗时为：" + (end2 - start2));
    }
}


class NetMall{

    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public String getNetMallName() {
        return netMallName;
    }

    public void setNetMallName(String netMallName) {
        this.netMallName = netMallName;
    }

    public double calcPrice(String productName){
        try{ TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e) {e.printStackTrace();}
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }

}
