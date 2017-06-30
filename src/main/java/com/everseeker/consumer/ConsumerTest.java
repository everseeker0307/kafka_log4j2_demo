package com.everseeker.consumer;

/**
 * Created by everseeker on 2017/6/30.
 */
public class ConsumerTest {
    private final String brokerList = "192.168.99.100:9092, 192.168.99.101:9092, 192.168.99.102:9092";
    private final String groupId = "group";
    private final String topic1 = "topic1";
    private final String topic2 = "topic2";

    public void test() {
        ConsumerHandler<String, String> consumerHandler = new ConsumerHandler<>(brokerList, groupId, topic1);
        consumerHandler.execute(3);
    }
}
