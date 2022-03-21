package com.justeattakeaway.restaurantservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.justeattakeaway.restaurantservice.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

	Optional<Restaurant> findByRestaurantId(String restaurantId);

}
