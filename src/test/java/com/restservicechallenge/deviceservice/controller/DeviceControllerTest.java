package com.restservicechallenge.deviceservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.restservicechallenge.deviceservice.api.DeviceController;

@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getDevice() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/getDevice/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();
	
		assertEquals("Hello World", result.getResponse().getContentAsString());
	}

}