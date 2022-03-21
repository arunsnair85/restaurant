package com.justeattakeaway.openclose.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.justeattakeaway.openclose.model.RestaurantStatusDTO;

@Component
public class KafkaSender {
	
	private final Logger LOG = LoggerFactory.getLogger(KafkaSender.class);
	
	private KafkaTemplate<String, RestaurantStatusDTO> statusKafkaTemplate;
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	KafkaSender(KafkaTemplate<String, String> kafkaTemplate,
			KafkaTemplate<String, RestaurantStatusDTO> statusKafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
		this.statusKafkaTemplate = statusKafkaTemplate;
	}
	
	public void sendCustomMessage(RestaurantStatusDTO restaurantDTO, String topicName) {
		LOG.info("Sending Json Serializer : {}", restaurantDTO);
		LOG.info("--------------------------------");
		statusKafkaTemplate.send(topicName, restaurantDTO);
	}
	
	void sendMessageWithCallback(String message, String topicName) {
		LOG.info("Sending : {}", message);
		LOG.info("---------------------------------");

		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				LOG.info("Success Callback: [{}] delivered with offset -{}", message,
						result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				LOG.warn("Failure Callback: Unable to deliver message [{}]. {}", message, ex.getMessage());
			}
		});
	}
}
