package com.harshchauhan.irctc_core.others.MultiThreading.producerConsumerLearning;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadingProducerConsumerSharedResource {
    private Queue<Integer> queue;
    private Integer queueSize;

    public ThreadingProducerConsumerSharedResource(int queueSize) {
        this.queueSize = queueSize;
        queue = new LinkedList<>();
    }


    public synchronized Integer consume() throws InterruptedException {

        while (queue.isEmpty()) {
            System.out.println("Buffer is empty, Consumer is waiting for producer");
            wait();
        }

        int item = queue.poll();
        System.out.println("Consumed : " + item);

        notify();
        return item;
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == queueSize) {
            System.out.println("Queue is full, Producer is waiting for consumer");
            wait();
        }

        queue.add(item);
        System.out.println("Produced item : " + item);

        notify();
    }

}
