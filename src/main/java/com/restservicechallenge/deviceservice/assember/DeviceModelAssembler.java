package com.restservicechallenge.deviceservice.assember;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.restservicechallenge.deviceservice.api.DeviceController;
import com.restservicechallenge.deviceservice.entity.DeviceEntity;
import com.restservicechallenge.deviceservice.model.DeviceModel;


@Component
public class DeviceModelAssembler 
	extends RepresentationModelAssemblerSupport<DeviceEntity, DeviceModel> {

	public DeviceModelAssembler() {
		super(DeviceController.class, DeviceModel.class);
	}

	@Override
	public DeviceModel toModel(DeviceEntity entity) 
	{
		DeviceModel deviceModel = instantiateModel(entity);
		
		deviceModel.add(linkTo(
				methodOn(DeviceController.class)
				.getDevice(entity.getId()))
				.withSelfRel());
		
		deviceModel.setId(entity.getId());
		deviceModel.setBrand(entity.getBrand());
		deviceModel.setCreationTime(entity.getCreationTime());
		deviceModel.setName(entity.getName());
		return deviceModel;
	}
	
	@Override
	public CollectionModel<DeviceModel> toCollectionModel(Iterable<? extends DeviceEntity> entities) 
	{
		CollectionModel<DeviceModel> deviceModels = super.toCollectionModel(entities);
		
		return deviceModels;
	}


}
