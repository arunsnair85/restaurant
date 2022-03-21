package com.justeattakeaway.openclose;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justeattakeaway.openclose.entity.RestaurantState;
import com.justeattakeaway.openclose.model.StateDTO;
import com.justeattakeaway.openclose.repository.RestaurantStateRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class OpenCloseApplicationTests {

	ObjectMapper om = new ObjectMapper();

	@Autowired
	MockMvc mockMvc;

	@Autowired
	RestaurantStateRepo resStateRepo;

	@Before
	public void setup() throws Exception {
		resStateRepo.deleteAll();
	}

	@Test
	public void testValidRestaurant() throws Exception {
		StateDTO stateDTO = new StateDTO(true);
		RestaurantState restaurantState = om.readValue(mockMvc
				.perform(post("/v1/restaurant/" + "3" + "/state").contentType("application/json")
						.content(om.writeValueAsString(stateDTO)))
				.andDo(print()).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(),
				RestaurantState.class);
		assertEquals(resStateRepo.findByRestaurantId("3").getRestaurantId(), restaurantState.getRestaurantId());
		assertEquals(stateDTO.isOpen(), resStateRepo.findByRestaurantId("3").isOpen());
	}
	

}
