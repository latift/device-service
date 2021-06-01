package com.restservicechallenge.deviceservice.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.restservicechallenge.deviceservice.entity.DeviceEntity;

public interface DeviceRepository extends PagingAndSortingRepository<DeviceEntity, Long> {
	
	Page<DeviceEntity> findAllByBrand(String brand, Pageable pageable);

}
