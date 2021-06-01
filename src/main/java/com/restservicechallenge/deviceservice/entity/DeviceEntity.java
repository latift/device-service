package com.restservicechallenge.deviceservice.entity;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DeviceEntity {

	private @Id @GeneratedValue Long id;
	private String name;
	private String brand;
	private OffsetDateTime creationTime;

	DeviceEntity() {}
	
	public DeviceEntity(String name, String brand, OffsetDateTime creationTime) {
		super();
		this.name = name;
		this.brand = brand;
		this.creationTime = creationTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public OffsetDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(OffsetDateTime creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof DeviceEntity))
			return false;
		DeviceEntity device = (DeviceEntity) o;
		return Objects.equals(this.id, device.id) && Objects.equals(this.name, device.name)
				&& Objects.equals(this.brand, device.brand)&& Objects.equals(this.creationTime, device.creationTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.brand, this.creationTime);
	}

	@Override
	public String toString() {
		//return "Device{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + '}';
		return "Device{" + "id=" + this.id + ", name='" + this.name + '\'' + ", brand='" + this.brand + '\'' +  ", Creation Time='" + this.creationTime + '\'' + '}';
	}
}
