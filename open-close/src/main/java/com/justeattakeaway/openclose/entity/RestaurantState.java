package com.justeattakeaway.openclose.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.justeattakeaway.openclose.repository.PostListener;

import lombok.Data;

@Entity
@Data
@EntityListeners(PostListener.class)
public class RestaurantState {

	public RestaurantState() {
		
	}
	public RestaurantState(boolean isOpen, String restaurantId) {
		this.isOpen = isOpen;
		this.restaurantId = restaurantId;
	}

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private boolean isOpen;
	@Column(name = "restaurant_id")
	private String restaurantId;
	@JsonIgnore
	private Long version;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	protected Date createdDate;
}
