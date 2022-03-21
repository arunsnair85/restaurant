package com.justeattakeaway.restaurantservice.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;

@Entity
@Data
@Table(name = "Restaurant", uniqueConstraints = { @UniqueConstraint(columnNames = "restaurant_id") })
public class Restaurant {
	

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@Column(name = "restaurant_id")
	private String restaurantId;
	private String restaurantName;
	private Long version;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	protected Date createdDate;
	
	@OneToMany(
	        mappedBy = "restaurant",
	        orphanRemoval = true
	    )
	    private List<RestaurantStatus> restaurantStatus = new ArrayList<>();
}
