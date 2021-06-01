package com.restservicechallenge.deviceservice.exceptionmanagement;

public class DeviceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DeviceNotFoundException(Long id) {
		super("Could not find device " + id);
	}
}
