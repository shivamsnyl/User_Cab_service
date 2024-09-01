package com.company.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
	
	@Bean
	NewTopic configureTopic() {
		return new NewTopic("kafka-topic", 5, (short) 1);
	}
	
	@Bean
	NewTopic configureTopicAll() {
		return new NewTopic("kafka-topic-all", 5, (short) 1);
	}

}
