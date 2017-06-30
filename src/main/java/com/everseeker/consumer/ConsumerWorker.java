package com.everseeker.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.AbstractMap;

/**
 * Created by everseeker on 2017/6/29.
 */
public abstract class ConsumerWorker<K, V> implements Runnable {
    private ConsumerRecord<K, V> consumerRecord;

    public ConsumerWorker(ConsumerRecord<K, V> record) {
        this.consumerRecord = record;
    }

    public abstract void run();
}
