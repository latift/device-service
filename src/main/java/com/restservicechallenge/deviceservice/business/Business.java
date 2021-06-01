package com.restservicechallenge.deviceservice.business;

import java.util.Optional;
import org.springframework.data.domain.Page;
import com.restservicechallenge.deviceservice.entity.DeviceEntity;

public interface Business {

	public DeviceEntity addDevice(DeviceEntity newDevice);
		
	public Optional<DeviceEntity> getDevice(Long id);
	
	public Page<DeviceEntity> allDevices(Optional<Integer> page, Optional<String> sortBy, Optional<String> brand);
	
	public DeviceEntity updateDevice(DeviceEntity newDevice, Long id);
	
	public void deleteDevice(Long id);
	
}
