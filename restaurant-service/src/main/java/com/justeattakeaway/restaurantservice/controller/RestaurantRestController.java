package com.justeattakeaway.restaurantservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.justeattakeaway.restaurantservice.entity.Restaurant;
import com.justeattakeaway.restaurantservice.repo.RestaurantRepository;

@RestController
@RequestMapping("/v1")
public class RestaurantRestController {
	
	@Autowired
	RestaurantRepository res;

	@GetMapping("/restaurant")
	public ResponseEntity<?> getRestaurantDetails() {
		return null;// paymentService.retrievePayment();
	}
	
	@PostMapping("/restaurants")
	public  ResponseEntity<?> saveRestaurantDetails(@RequestBody Restaurant restaurant) {		
		return new ResponseEntity<>(res.save(restaurant),HttpStatus.CREATED);
		
	}
	
}
