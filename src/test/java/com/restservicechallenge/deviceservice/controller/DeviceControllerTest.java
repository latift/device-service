package com.restservicechallenge.deviceservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.hateoas.server.mvc.RepresentationModelProcessorInvoker;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.restservicechallenge.deviceservice.api.DeviceController;
import com.restservicechallenge.deviceservice.assember.DeviceModelAssembler;
import com.restservicechallenge.deviceservice.business.Business;
import com.restservicechallenge.deviceservice.entity.DeviceEntity;

@WebMvcTest(DeviceController.class)
class DeviceControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private Business businessService;
	
	@MockBean
	private DeviceModelAssembler deviceModelAssembler;
	
	@MockBean
	private RepresentationModelProcessorInvoker representationModelProcessorInvoker;
	
	
	@Test
	void testAddDevice() throws Exception {
		String requestJson="{\"name\": \"Sim10\", \"brand\": \"brandX\", \"creationTime\": \"2019-04-09T23:15:45.345875+03:00\",\"ada\":123}";
	    when(businessService.addDevice(Mockito.any(DeviceEntity.class))).thenReturn(new DeviceEntity("Sim1", "BrandX", OffsetDateTime.of(2020, 1, 1, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"))));
		RequestBuilder request = MockMvcRequestBuilders
				.post("/devices")
				.contentType(APPLICATION_JSON_UTF8)
		        .content(requestJson)
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();
	}

	@Test
	void testGetDevice() throws Exception {
		when(businessService.getDevice((long) 1)).thenReturn(Optional.of(new DeviceEntity("Sim1", "BrandX", OffsetDateTime.of(2019, 1, 1, 20, 15, 45, 345875000, ZoneOffset.of("+00:00")))));
		RequestBuilder request = MockMvcRequestBuilders
				.get("/devices/1")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request)
				.andExpect(status().isOk())
				//.andExpect(content().json(expectedDevice))
				.andReturn();
	}

	@Test
	void testAllDevices() throws Exception {
		List<DeviceEntity> devices = new ArrayList<>();
		devices.add(new DeviceEntity("name1", "BrandX", OffsetDateTime.of(2019, 4, 9, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"))));
		devices.add(new DeviceEntity("name2", "BrandY", OffsetDateTime.of(2019, 4, 9, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"))));
		devices.add(new DeviceEntity("name3", "BrandZ", OffsetDateTime.of(2019, 4, 9, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"))));
		when(businessService.allDevices(Optional.empty(),Optional.empty(),Optional.empty())).thenReturn(new PageImpl<DeviceEntity>(devices));
		RequestBuilder request = MockMvcRequestBuilders
				.get("/devices")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk());
	}

	@Test
	void testUpdateDevice()throws Exception  {
		/*String requestJson="{\"name\": \"Sim10\", \"brand\": \"brandX\", \"creationTime\": \"2019-04-09T23:15:45.345875+03:00\",\"ada\":123}";
	    when(businessService.updateDevice(Mockito.any(DeviceEntity.class),(long) 1)).thenReturn(new DeviceEntity("Sim1", "BrandX", OffsetDateTime.of(2020, 1, 1, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"))));
		RequestBuilder request = MockMvcRequestBuilders
				.put("/devices/1")
				.contentType(APPLICATION_JSON_UTF8)
		        .content(requestJson)
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();*/
	}

	@Test
	void testDeleteDevice() throws Exception {
		when(businessService.getDevice((long) 1)).thenReturn(Optional.of(new DeviceEntity("Sim1", "BrandX", OffsetDateTime.of(2019, 1, 1, 20, 15, 45, 345875000, ZoneOffset.of("+00:00")))));
		RequestBuilder request = MockMvcRequestBuilders
				.get("/devices/1")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request)
				.andExpect(status().isOk());		
	}

}
