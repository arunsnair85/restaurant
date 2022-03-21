package com.justeattakeaway.openclose.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.justeattakeaway.openclose.entity.RestaurantState;

public interface RestaurantStateRepo extends JpaRepository<RestaurantState, Long> {

	RestaurantState findByRestaurantId(String restaurantId);
}
