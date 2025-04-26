package com.harshchauhan.irctc_core.others.MultiThreading.monitorLock;

public class MonitorThread1Runnable implements Runnable {

    MonitorLockSample monitorLockSample;

    MonitorThread1Runnable(MonitorLockSample monitorLockSample) {
        this.monitorLockSample = monitorLockSample;
    }

    @Override
    public void run() {
        monitorLockSample.task1();
    }

}
