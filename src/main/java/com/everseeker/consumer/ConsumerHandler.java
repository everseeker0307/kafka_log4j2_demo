package com.everseeker.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by everseeker on 2017/6/29.
 */
public class ConsumerHandler<K, V> {
    private final KafkaConsumer<K, V> consumer;
    private ExecutorService executors;

    /**
     * 初始化KafkaConsumer
     * @param brokerList
     * @param groupId
     * @param topic
     */
    public ConsumerHandler(String brokerList, String groupId, String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", brokerList);
        props.put("group.id", groupId);
        // consumer自动commit，每隔1秒钟commit一次
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<K, V>(props);
        consumer.subscribe(Arrays.asList(topic));
    }

    public void execute(int threadNum) {
        executors = new ThreadPoolExecutor(threadNum, threadNum, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.CallerRunsPolicy());

        while (true) {
            ConsumerRecords<K, V> records = consumer.poll(5);
            for (final ConsumerRecord record : records) {
                executors.submit(new ConsumerWorker(record) {
                    @Override
                    public void run() {
                        System.out.println("key=" + record.key() + ", value=" + record.value());
                        System.out.println("partition=" + record.partition() + ", offset=" + record.offset());
                    }
                });
            }
        }
    }
}
