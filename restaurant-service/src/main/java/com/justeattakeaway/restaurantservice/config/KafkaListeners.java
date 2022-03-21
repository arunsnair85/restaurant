package com.justeattakeaway.restaurantservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.justeattakeaway.restaurantservice.model.RestaurantStatusDTO;
import com.justeattakeaway.restaurantservice.service.RestaurantService;

@Component
class KafkaListeners {

	private final Logger LOG = LoggerFactory.getLogger(KafkaListeners.class);

	@Autowired
	RestaurantService restaurantService;

	@KafkaListener(id = "1", topics = "restaurant-status", groupId = "reflectoring-user-mc", containerFactory = "kafkaJsonListenerContainerFactory")
	void listenerWithMessageConverter(RestaurantStatusDTO restaurantStatusDTO) {
		LOG.info("MessageConverterUserListener [{}]", restaurantStatusDTO);
		restaurantService.persistStatus(restaurantStatusDTO);
	}
}
