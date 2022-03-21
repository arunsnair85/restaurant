package com.justeattakeaway.openclose.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Value("${com.justeat.kafka.topic-1}")
	private String topic;
	
	@Bean
	NewTopic topic() {
		return TopicBuilder.name(topic).build();
	}
}
