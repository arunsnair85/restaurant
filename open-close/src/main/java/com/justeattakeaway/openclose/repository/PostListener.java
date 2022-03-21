package com.justeattakeaway.openclose.repository;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.justeattakeaway.openclose.configuration.KafkaSender;
import com.justeattakeaway.openclose.entity.RestaurantState;
import com.justeattakeaway.openclose.model.RestaurantStatusDTO;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor
public class PostListener {

	@Value("${com.justeat.kafka.topic-1}")
	private String topic1;

	@Autowired
	private KafkaSender kafkaSender;

	@PrePersist 
	public void onPrePrist(final RestaurantState toSave) {
		toSave.setVersion(1L);
	}

	@PostPersist 
	@PostUpdate 
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void afterPresist(final RestaurantState saved) {
		System.out.println("Saved " + saved.getRestaurantId());
		kafkaSender.sendCustomMessage(
				new RestaurantStatusDTO(saved.getRestaurantId(), saved.isOpen(), saved.getCreatedDate()), topic1);
	}

	@PreUpdate
	public void onUpdate(final RestaurantState toUpdate) {
		toUpdate.setVersion(toUpdate.getVersion() + 1);
	}

}
