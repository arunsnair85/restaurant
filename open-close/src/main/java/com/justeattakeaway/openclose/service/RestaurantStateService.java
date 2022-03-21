package com.justeattakeaway.openclose.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.justeattakeaway.openclose.entity.RestaurantState;
import com.justeattakeaway.openclose.model.StateDTO;
import com.justeattakeaway.openclose.repository.RestaurantStateRepo;

@Service
public class RestaurantStateService {

	private RestaurantStateRepo restaurantStateRepo;

	RestaurantStateService(RestaurantStateRepo restaurantStateRepo) {
		this.restaurantStateRepo = restaurantStateRepo;
	}

	@Transactional
	public Optional<RestaurantState> process(StateDTO stateDTO,String restaurantId) {		
		return Optional.of(restaurantStateRepo.save(new RestaurantState(stateDTO.isOpen(),restaurantId)));
		
	}
}
