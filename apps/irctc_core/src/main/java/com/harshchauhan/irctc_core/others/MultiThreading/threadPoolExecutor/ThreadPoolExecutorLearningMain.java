package com.harshchauhan.irctc_core.others.MultiThreading.threadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorLearningMain {

    public static void main(String args[]) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(2), new CustomThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        threadPoolExecutor.allowCoreThreadTimeOut(true);

        for (int i = 1; i <= 8; i++) {
            int finalI = i;
            threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                }
                System.out.println("Task id :: " + finalI + " , process by :: " + Thread.currentThread().getName());
            });
        }

        threadPoolExecutor.shutdown();
    }
}

class CustomThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setPriority(Thread.NORM_PRIORITY);
        thread.setDaemon(false);
        return thread;
    }
}
