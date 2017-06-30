package com.everseeker.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.AbstractMap;
import java.util.HashMap;

/**
 * Created by everseeker on 2017/6/29.
 */
public abstract class ConsumerWorker<K, V> implements Runnable {
    private ConsumerRecord<K, V> consumerRecord;

    public ConsumerWorker(ConsumerRecord<K, V> record) {
        this.consumerRecord = record;
    }

    @Override
    public void run() {
        System.out.println("key=" + consumerRecord.key() + ", value=" + consumerRecord.value());
    }
}
