package com.restservicechallenge.deviceservice.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restservicechallenge.deviceservice.assember.DeviceModelAssembler;
import com.restservicechallenge.deviceservice.business.Business;
import com.restservicechallenge.deviceservice.entity.DeviceEntity;
import com.restservicechallenge.deviceservice.exceptionmanagement.DeviceNotFoundException;
import com.restservicechallenge.deviceservice.model.DeviceModel;


@RestController
public class DeviceController {

	private final Business business;
	
	private DeviceModelAssembler deviceModelAssembler;
	
	private final PagedResourcesAssembler<DeviceEntity> pagedResourcesAssembler;

	DeviceController(Business business, PagedResourcesAssembler<DeviceEntity> pagedResourcesAssembler, DeviceModelAssembler deviceModelAssembler) {
		this.business=business;
		this.pagedResourcesAssembler=pagedResourcesAssembler;
		this.deviceModelAssembler=deviceModelAssembler;
	}
	
	@PostMapping("/devices")
	public DeviceEntity addDevice(@RequestBody DeviceEntity newDevice) {
	
		return business.addDevice(newDevice);
	}
	
	@GetMapping("/devices/{id}")
	public EntityModel<DeviceEntity> getDevice(@PathVariable Long id) {

		DeviceEntity device = business.getDevice(id) //
				.orElseThrow(() -> new DeviceNotFoundException(id));

		return EntityModel.of(device, //
				linkTo(methodOn(DeviceController.class).getDevice(id)).withSelfRel());
				//linkTo(methodOn(DeviceController.class).allDevices()).withRel("devices"));
	}

	@GetMapping("/devices")
    public ResponseEntity<PagedModel<DeviceModel>> allDevices(@RequestParam("page") Optional<Integer> page ,@RequestParam("sortBy") Optional<String> sortBy,@RequestParam("brand") Optional<String> brand) 
    {
		Page<DeviceEntity> deviceEntities = business.allDevices(page, sortBy, brand);
			
		PagedModel<DeviceModel> collModel = pagedResourcesAssembler
								.toModel(deviceEntities, deviceModelAssembler);
			
		return new ResponseEntity<>(collModel,HttpStatus.OK);
    }
		

	@PutMapping("/devices/{id}")
	public DeviceEntity updateDevice(@RequestBody DeviceEntity newDevice, @PathVariable Long id) {

		return business.updateDevice(newDevice, id);
	}

	@DeleteMapping("/devices/{id}")
	void deleteDevice(@PathVariable Long id) {
	
		business.deleteDevice(id);
	}


}
