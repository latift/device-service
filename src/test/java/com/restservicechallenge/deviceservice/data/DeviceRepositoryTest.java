package com.restservicechallenge.deviceservice.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.restservicechallenge.deviceservice.entity.DeviceEntity;

@DataJpaTest
public class DeviceRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	  
	@Autowired
	private DeviceRepository repository;
	
	@Test
	public void testShouldFindNoDevicesIfRepositoryIsEmpty() {
	   Iterable<DeviceEntity> devices = repository.findAll();
	   assertThat(devices).isEmpty();
	}
	
	@Test
  	public void testShouldStoreADevice() {
  		DeviceEntity deviceEntity = repository.save(new DeviceEntity("Sim1", "Ericcson",  OffsetDateTime.of(2020, 1, 1, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"))));
	    assertThat(deviceEntity).hasFieldOrPropertyWithValue("name", "Sim1");
	    assertThat(deviceEntity).hasFieldOrPropertyWithValue("brand", "Ericcson");
	 }
	  
	 @Test
	 public void testShouldFindBrandYSims() {
	    DeviceEntity dev1 = new DeviceEntity("Sim1", "BrandX",  OffsetDateTime.of(2020, 1, 1, 20, 15, 45, 345875000, ZoneOffset.of("+00:00")));
	    entityManager.persist(dev1);
	    DeviceEntity dev2 = new DeviceEntity("Sim2", "BrandY",  OffsetDateTime.of(2020, 1, 1, 20, 15, 45, 345875000, ZoneOffset.of("+00:00")));
		entityManager.persist(dev2);
		DeviceEntity dev3 = new DeviceEntity("Sim3", "BrandY",  OffsetDateTime.of(2020, 1, 1, 20, 15, 45, 345875000, ZoneOffset.of("+00:00")));
		entityManager.persist(dev3);
	    Iterable<DeviceEntity> devices = repository.findAllByBrand("BrandY", PageRequest.of(0,5,Sort.Direction.ASC, "id"));
	    assertThat(devices).hasSize(2).contains(dev2, dev3);
	 }

}