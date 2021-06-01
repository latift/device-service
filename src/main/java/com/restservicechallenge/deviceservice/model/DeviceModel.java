package com.restservicechallenge.deviceservice.model;

import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public class DeviceModel extends RepresentationModel<DeviceModel>
{
	private Long id;
	private String name;
	private String brand;
	private OffsetDateTime creationTime;
	
}