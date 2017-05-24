package com.zk.java8.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;

/**
 * Created by zk_chs on 16/8/12.
 * 这个类是关于java8的CompletableFuture的
 * http://www.importnew.com/10815.html
 */
public class ConcurrentOfCompletableFuture {

    private CompletableFuture<String> ask (){
        return new CompletableFuture<>();
    }

    private String blockingOrNonBlocking (Supplier<CompletableFuture<String>> supplier, boolean blockingOrNot) throws ExecutionException, InterruptedException {
        if (blockingOrNot){
            System.out.println("this method will be blocking!");
            return null;
        } else {
            System.out.println("this method will not be blocking!");
            supplier.get().complete("998");
            return supplier.get().exceptionally(ex -> "We have an exception: " + ex.getMessage()).get();
        }
    }

    private Integer stringToInteger (Supplier<CompletableFuture<String>> supplier, boolean blockingOrNot) throws ExecutionException, InterruptedException {
        if (blockingOrNot){
            System.out.println("this method will be blocking!");
            return null;
        } else {
            System.out.println("this method will not be blocking!");
            supplier.get().complete("not blocking!");
            return supplier.get().thenApply(Integer::parseInt).exceptionally(ex -> {
                System.out.println("We have an exception: " + ex.getMessage());
                return -1;
            }).get();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new ConcurrentOfCompletableFuture().blockingOrNonBlocking(() -> CompletableFuture.supplyAsync(() -> "this method will not be blocking!", ForkJoinPool.commonPool()), false);
        new ConcurrentOfCompletableFuture().stringToInteger(() -> CompletableFuture.supplyAsync(() -> "998"), false);
        CompletableFuture.supplyAsync(() -> "998").thenRun(() -> System.out.println("complete"));
    }

}
