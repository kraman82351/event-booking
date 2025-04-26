package com.harshchauhan.irctc_core.others.MultiThreading.producerConsumerLearning;

public class ThreadingProducerConsumerMain {
    public static void main(String args[]) {

        ThreadingProducerConsumerSharedResource sharedResource = new ThreadingProducerConsumerSharedResource(4);


        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    sharedResource.produce(i);
                }
            } catch (Exception e) {
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    sharedResource.consume();
                }
            } catch (Exception e) {
            }
        });

        producerThread.start();
        consumerThread.start();
    }

}
