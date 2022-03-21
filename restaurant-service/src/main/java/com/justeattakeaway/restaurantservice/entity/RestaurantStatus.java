package com.justeattakeaway.restaurantservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

import static javax.persistence.TemporalType.TIMESTAMP;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class RestaurantStatus {
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private boolean isOpen;
	@ManyToOne(fetch = FetchType.LAZY)
	private Restaurant restaurant;
	
	@CreatedDate
	@Temporal(TIMESTAMP)
	@JsonIgnore
	protected Date createdDate;
	
	public RestaurantStatus(boolean isOpen,Date createdDate,Restaurant restaurant) {
		this.isOpen=isOpen;
		this.createdDate=createdDate;
		this.restaurant=restaurant;
	}
}
