package com.restservicechallenge.deviceservice;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restservicechallenge.deviceservice.data.DeviceRepository;
import com.restservicechallenge.deviceservice.entity.DeviceEntity;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(DeviceRepository repository) {

		  OffsetDateTime offsetDT1 = OffsetDateTime.of(2020, 1, 1, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"));
	      
	      OffsetDateTime offsetDT2 = OffsetDateTime.of(2019, 1, 1, 20, 15, 45, 345875000, ZoneOffset.of("+00:00"));
	        
		 return args -> {
			log.info("Preloading " + repository.save(new DeviceEntity("Sim1", "BrandX", offsetDT1)));
			log.info("Preloading " + repository.save(new DeviceEntity("Sim2", "Ericcson", offsetDT2)));
			log.info("Preloading " + repository.save(new DeviceEntity("Sim3", "BrandX", offsetDT2)));
			log.info("Preloading " + repository.save(new DeviceEntity("Sim4", "BrandX", offsetDT2)));
			log.info("Preloading " + repository.save(new DeviceEntity("Sim5", "BrandY", offsetDT2)));
			
			log.info("Preloading " + repository.save(new DeviceEntity("Sim6", "BrandX", offsetDT2)));
			log.info("Preloading " + repository.save(new DeviceEntity("Sim7", "Ericcson", offsetDT2)));
			log.info("Preloading " + repository.save(new DeviceEntity("Sim8", "BrandX", offsetDT2)));
			log.info("Preloading " + repository.save(new DeviceEntity("Sim9", "Ericcson", offsetDT2)));
			log.info("Preloading " + repository.save(new DeviceEntity("Sim10", "BrandX", offsetDT2)));
			
			log.info("Preloading " + repository.save(new DeviceEntity("Sim11", "BrandY", offsetDT2)));
			log.info("Preloading " + repository.save(new DeviceEntity("Sim12", "BrandY", offsetDT2)));
			log.info("Preloading " + repository.save(new DeviceEntity("Sim12", "BrandY", offsetDT2)));
			log.info("Preloading " + repository.save(new DeviceEntity("Sim11", "BrandY", offsetDT2)));
			log.info("Preloading " + repository.save(new DeviceEntity("Sim12", "BrandY", offsetDT2)));
			
			log.info("Preloading " + repository.save(new DeviceEntity("Sim11", "BrandZ", offsetDT2)));
			log.info("Preloading " + repository.save(new DeviceEntity("Sim12", "BrandZ", offsetDT2)));
			log.info("Preloading " + repository.save(new DeviceEntity("Sim13", "BrandY", offsetDT2)));

			
		};
	}
}
