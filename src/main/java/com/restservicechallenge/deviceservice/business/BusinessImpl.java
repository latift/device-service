package com.restservicechallenge.deviceservice.business;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.restservicechallenge.deviceservice.data.DeviceRepository;
import com.restservicechallenge.deviceservice.entity.DeviceEntity;

@Component
public class BusinessImpl implements Business {

	private final DeviceRepository repository;

	public BusinessImpl(DeviceRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public DeviceEntity addDevice(DeviceEntity newDevice) {
		return repository.save(newDevice);
	}

	@Override
	public Optional<DeviceEntity> getDevice(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<DeviceEntity> allDevices(Optional<Integer> page, Optional<String> sortBy, Optional<String> brand) {
		Pageable requestedPage=PageRequest.of(page.orElse(0),5,Sort.Direction.ASC, sortBy.orElse("id"));
		if(brand.isPresent()) 
			return repository.findAllByBrand(brand.get(), requestedPage);
		return repository.findAll(requestedPage);
	}
	
	@Override
	public DeviceEntity updateDevice(DeviceEntity newDevice, Long id) {
		return repository.findById(id) //
				.map(device -> {
					device.setName(newDevice.getName());
					device.setBrand(newDevice.getBrand());
					device.setCreationTime(newDevice.getCreationTime());
					return repository.save(device);
				}) //
				.orElseGet(() -> {
					newDevice.setId(id);
					return repository.save(newDevice);
				});
	}

	@Override
	public void deleteDevice(Long id) {
		repository.deleteById(id);
	}

}
