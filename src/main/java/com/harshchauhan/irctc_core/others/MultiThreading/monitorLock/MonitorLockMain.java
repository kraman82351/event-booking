package com.harshchauhan.irctc_core.others.MultiThreading.monitorLock;

public class MonitorLockMain {

    public static void main(String args[]) {
        MonitorLockSample monitorLockSample = new MonitorLockSample();

        Thread t1 = new Thread(new MonitorThread1Runnable(monitorLockSample));
//        Thread t1 = new Thread(() -> {
//            monitorLockSample.task1();
//        });
        Thread t2 = new Thread(() -> {
            monitorLockSample.task2();
        });
        Thread t3 = new Thread(() -> {
            monitorLockSample.task3();
        });


        t1.start();
        t2.start();
        t3.start();
    }
}
