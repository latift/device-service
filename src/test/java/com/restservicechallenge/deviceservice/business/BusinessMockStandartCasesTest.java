package com.restservicechallenge.deviceservice.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.restservicechallenge.deviceservice.data.DeviceRepository;
import com.restservicechallenge.deviceservice.entity.DeviceEntity;

@ExtendWith(MockitoExtension.class)
public class BusinessMockStandartCasesTest {

	@InjectMocks
	BusinessImpl business;
	
	@Mock
	DeviceRepository deviceRepositoryMock;

	@Test
	void testAddDeviceSuccess() {
		when(deviceRepositoryMock.save(Mockito.any(DeviceEntity.class))).thenAnswer(i -> i.getArguments()[0]);
		DeviceEntity addedDevice = business.addDevice(new DeviceEntity("Sim1", "Ericcson", OffsetDateTime.of(2020, 1, 1, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"))));
		assertEquals(addedDevice, new DeviceEntity("Sim1", "Ericcson", OffsetDateTime.of(2020, 1, 1, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"))));
	}
	
	@Test
	void testGetDeviceItemById() {
		when(deviceRepositoryMock.findById((long) 1)).thenReturn(Optional.of(new DeviceEntity("Sim1", "Ericcson", OffsetDateTime.of(2019, 4, 9, 20, 15, 45, 345875000, ZoneOffset.of("+00:00")))));
		assertEquals(new DeviceEntity("Sim1", "Ericcson", OffsetDateTime.of(2019, 4, 9, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"))), business.getDevice((long) 1).get());
	}
	

	@Test
	void testListAllDevicesFirstPage() {
		List<DeviceEntity> devices = new ArrayList<>();
		devices.add(new DeviceEntity("name1", "BrandX", OffsetDateTime.of(2019, 4, 9, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"))));
		devices.add(new DeviceEntity("name2", "BrandY", OffsetDateTime.of(2019, 4, 9, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"))));
		devices.add(new DeviceEntity("name3", "BrandZ", OffsetDateTime.of(2019, 4, 9, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"))));
		Pageable requestedPage=PageRequest.of(0,5,Sort.Direction.ASC, "id");
		when(deviceRepositoryMock.findAll(requestedPage)).thenReturn(new PageImpl<DeviceEntity>(devices));
		assertEquals(3, business.allDevices(Optional.empty(), Optional.empty(), Optional.empty()).getSize());
	}


	@Test
	void testUpdateDevice() {
		when(deviceRepositoryMock.save(Mockito.any(DeviceEntity.class))).thenAnswer(i -> i.getArguments()[0]);
		DeviceEntity updatedDevice = business.updateDevice(new DeviceEntity("Sim1_Updated_Name", "Ericcson_Updated_Brand", OffsetDateTime.of(2020, 1, 1, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"))),(long) 1);
		assertEquals(updatedDevice.getName(), "Sim1_Updated_Name");	
		assertEquals(updatedDevice.getBrand(), "Ericcson_Updated_Brand");
	}
	

	@Test
	void testDeleteDevice() {
		//fail("Not yet implemented");
	}

	
	@Test
	void testListBrandDevice() {
		List<DeviceEntity> devices = new ArrayList<>();
		devices.add(new DeviceEntity("name1", "BrandY", OffsetDateTime.of(2019, 4, 9, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"))));
		devices.add(new DeviceEntity("name2", "BrandY", OffsetDateTime.of(2019, 4, 9, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"))));
		devices.add(new DeviceEntity("name3", "BrandY", OffsetDateTime.of(2019, 4, 9, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"))));
		when(deviceRepositoryMock.findAllByBrand("BrandY",PageRequest.of(0,5,Sort.Direction.ASC, "id"))).thenReturn(new PageImpl<DeviceEntity>(devices));
		assertEquals("name1", business.allDevices(Optional.of(0), Optional.of("id"), Optional.of("BrandY")).get().findFirst().get().getName());
	}
	
}
