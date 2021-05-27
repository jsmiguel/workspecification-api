package com.telus.workforcemgmt.workspecification.domain.entity;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * The persistent class for the VIRTUAL_NAV_HIERARCHY database table.
 * 
 */
@Data
@Table("VIRTUAL_NAV_HIERARCHY")
public class VirtualNavHierarchy {

	@Id
	private long virtualNavHierarchyId;

	private String commonAreaTxt;

	private LocalDateTime createTs;

	private String createUserId;

	private BigDecimal districtLocationId;

	private String districtNm;

	private LocalDateTime effectiveEndTs;

	private LocalDateTime effectiveStartTs;

	private String fmsCoid;

	private LocalDateTime lastUpdtTs;

	private String lastUpdtUserId;

	private String latitudeQty;

	private String longitudeQty;

	private String manageCapacityInd;

	private String manageDispatchInd;

	private String provinceCd;

	private BigDecimal provinceLocationId;

	private String provinceNm;

	private BigDecimal regionLocationId;

	private String regionNm;

	private String sapLocationCd;

	private String serviceAreaClliCd;

	private BigDecimal serviceAreaLocationId;

	private String serviceAreaNm;

	private String workOrderCategoryCd;

}