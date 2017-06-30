package com.everseeker;

import com.everseeker.consumer.ConsumerHandler;
import com.everseeker.consumer.ConsumerTest;
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

		ConsumerTest ctest = new ConsumerTest();
		ctest.test();
	}
}
