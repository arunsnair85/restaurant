package com.justeattakeaway.openclose.model;

import java.util.Date;

public record RestaurantStatusDTO(String restaurantId, boolean isOpen,Date createdDate) {

}
