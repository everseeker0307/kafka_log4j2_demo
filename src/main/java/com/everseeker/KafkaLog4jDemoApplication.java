package com.everseeker;

import com.everseeker.consumer.ConsumerHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaLog4jDemoApplication {

	private static final Logger logger = LogManager.getLogger(KafkaLog4jDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaLog4jDemoApplication.class, args);

		for (int i = 100; i < 110; i++) {
			logger.info(i);
		}

		String brokerList = "192.168.99.100:9092, 192.168.99.101:9092, 192.168.99.102:9092";
		String groupId = "group1";
		String topic = "test";
		ConsumerHandler<String, String> consumerHandler = new ConsumerHandler<>(brokerList, groupId, topic);
		consumerHandler.execute(3);
	}
}
