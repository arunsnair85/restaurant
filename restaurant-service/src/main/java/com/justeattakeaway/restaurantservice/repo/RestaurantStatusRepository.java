package com.justeattakeaway.restaurantservice.repo;

import org.springframework.stereotype.Repository;

import com.justeattakeaway.restaurantservice.entity.Restaurant;
import com.justeattakeaway.restaurantservice.entity.RestaurantStatus;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RestaurantStatusRepository  extends JpaRepository<RestaurantStatus, Long>  {
	List<RestaurantStatus> findByRestaurantAndCreatedDateAfter(Restaurant rest,Date date);
}
