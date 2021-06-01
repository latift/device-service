package com.restservicechallenge.deviceservice.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.restservicechallenge.deviceservice.data.DeviceRepository;
import com.restservicechallenge.deviceservice.entity.DeviceEntity;

@ExtendWith(MockitoExtension.class)
public class BusinessMockEdgeCasesTest {

	@InjectMocks
	BusinessImpl business;
	
	@Mock
	DeviceRepository deviceRepositoryMock;

	
	@Test
	void testAlreadyThereDeviceAdd() {
		//fail("Not yet implemented");
	}


	@Test
	void testGetDeviceItemDoesntExists() {
		when(deviceRepositoryMock.findById((long) 1)).thenReturn(Optional.empty());
		assertEquals(true, business.getDevice((long) 1).isEmpty());
	}

	
	@Test
	void testAllDevicesNonexistingPage() {
		when(deviceRepositoryMock.findAllByBrand("BrandX",PageRequest.of(5,5,Sort.Direction.ASC, "id"))).thenReturn(new PageImpl<DeviceEntity>(new ArrayList<DeviceEntity>()));
		assertEquals(0, business.allDevices(Optional.of(5),Optional.of("id"),Optional.of("BrandX")).getSize());
	}
	
	@Test
	void testGetABrandDevicesNonExistingFirstPage() {
		//fail("Not yet implemented");
	}

	@Test
	void testTryToUpdateNonExistingDevice() {
		//fail("Not yet implemented");
	}

	@Test
	void testTryToDeleteNotExistingDevice() {
		//business.deleteDevice((long) 1);
	}
}
