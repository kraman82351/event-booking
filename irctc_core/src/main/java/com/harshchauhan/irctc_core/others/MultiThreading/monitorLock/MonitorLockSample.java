package com.harshchauhan.irctc_core.others.MultiThreading.monitorLock;

public class MonitorLockSample {

    public synchronized void task1() {

        try {
            System.out.println("Inside Task 1");

            Thread.sleep(10000);

            System.out.println("Task 1 completed");
        } catch (Exception e) {
            System.out.println("Task 1 Error");
        }
    }

    public void task2() {
        System.out.println("Task 2, outside synchronized");

        synchronized (this) {
            System.out.println("Task 2, inside synchronized");
        }
    }

    public void task3() {
        System.out.println("Task 3");
    }

}
