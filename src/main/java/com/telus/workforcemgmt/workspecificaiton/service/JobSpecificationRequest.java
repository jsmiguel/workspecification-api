package com.telus.workforcemgmt.workspecificaiton.service;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

public class JobSpecificationRequest {

	@NotBlank
	private String workOrderSpecificationId;
	
	private String specialProjectName = "";
	private String locationId;
	private String remoteFrameClli;
	private Long demandPolygonPointId;
	private String customerId;
	private String originalSystemId;
	
	private LocalDateTime effectDT = LocalDateTime.now();
	
	public JobSpecificationRequest() {
		
	}

	public String getWorkOrderSpecificationId() {
		return workOrderSpecificationId;
	}

	public void setWorkOrderSpecificationId(String workOrderSpecificationId) {
		this.workOrderSpecificationId = workOrderSpecificationId;
	}

	public String getSpecialProjectName() {
		return specialProjectName;
	}

	public void setSpecialProjectName(String specialProjectName) {
		this.specialProjectName = specialProjectName;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getRemoteFrameClli() {
		return remoteFrameClli;
	}

	public void setRemoteFrameClli(String remoteFrameClli) {
		this.remoteFrameClli = remoteFrameClli;
	}

	public Long getDemandPolygonPointId() {
		return demandPolygonPointId;
	}

	public void setDemandPolygonPointId(Long demandPolygonPointId) {
		this.demandPolygonPointId = demandPolygonPointId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOriginalSystemId() {
		return originalSystemId;
	}

	public void setOriginalSystemId(String originalSystemId) {
		this.originalSystemId = originalSystemId;
	}

	public LocalDateTime getEffectDT() {
		return effectDT;
	}

	public void setEffectDT(LocalDateTime effectDT) {
		this.effectDT = effectDT;
	}
	
	
}
