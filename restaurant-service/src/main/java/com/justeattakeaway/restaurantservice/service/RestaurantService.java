package com.justeattakeaway.restaurantservice.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justeattakeaway.restaurantservice.entity.Restaurant;
import com.justeattakeaway.restaurantservice.entity.RestaurantStatus;
import com.justeattakeaway.restaurantservice.model.RestaurantStatusDTO;
import com.justeattakeaway.restaurantservice.repo.RestaurantRepository;
import com.justeattakeaway.restaurantservice.repo.RestaurantStatusRepository;

@Service
public class RestaurantService {

	@Autowired
	RestaurantStatusRepository restaurantStatusRepository;

	@Autowired
	RestaurantRepository restaurantRepository;

	@Transactional
	public Optional<?> persistStatus(RestaurantStatusDTO restaurantStatusDTO) {
		// TODO Auto-generated method stub
		Restaurant rest = new Restaurant();
		rest.setRestaurantId(restaurantStatusDTO.restaurantId());
		Optional<Restaurant> restOptional = restaurantRepository.findByRestaurantId(restaurantStatusDTO.restaurantId());
		if (restOptional.isEmpty()) {
			return Optional.empty();
		}
		List<RestaurantStatus> r = restaurantStatusRepository.findByRestaurantAndCreatedDateAfter(restOptional.get(),
				restaurantStatusDTO.createdDate());
		if (r.isEmpty()) {
			RestaurantStatus rs = restaurantStatusRepository.save(new RestaurantStatus(restaurantStatusDTO.isOpen(),
					restaurantStatusDTO.createdDate(), restOptional.get()));
			return Optional.of(rs);
		}
		return Optional.empty();
	}

}
