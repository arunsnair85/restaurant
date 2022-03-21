package com.justeattakeaway.openclose.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.justeattakeaway.openclose.entity.RestaurantState;
import com.justeattakeaway.openclose.model.ErrorDTO;
import com.justeattakeaway.openclose.model.StateDTO;
import com.justeattakeaway.openclose.service.RestaurantStateService;

@RestController
@RequestMapping("/v1/restaurant")
@Validated
public class RestaurantStateController {

	private final RestaurantStateService restaurantStateService;

	public RestaurantStateController(RestaurantStateService restaurantStateService) {
		this.restaurantStateService = restaurantStateService;
	}

	// TODO
	/**
	 * 1.validation on the id with redudancy 2.
	 * 
	 * @param stateDTO
	 * @return
	 */
	@PostMapping("/{restaurantId}/state")
	public ResponseEntity<?> restaurantState(@PathVariable("restaurantId") String restaurantId,
			@RequestBody @Valid StateDTO stateDTO) {
		Optional<RestaurantState> restaurantState = restaurantStateService.process(stateDTO, restaurantId);
		return restaurantState.isPresent() ? new ResponseEntity<>(restaurantState.get(), HttpStatus.CREATED)
				: new ResponseEntity<>(new ErrorDTO("Process failed"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
