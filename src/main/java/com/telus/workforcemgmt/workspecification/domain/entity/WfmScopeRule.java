package com.telus.workforcemgmt.workspecification.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


/**
 * The persistent class for the WFM_SCOPE_RULE database table.
 * 
 */
@Data
@Table("WFM_SCOPE_RULE")
public class WfmScopeRule {
	
	@Id
	private long wfmScopeRuleId;

	private LocalDateTime createTs;

	private String createUserId;

	private String custNotifInd;

	private String dfltApptProflCd;

	private String fmsCoid;

	private String jobTypeCd;

	private LocalDateTime lastUpdtTs;

	private String lastUpdtUserId;

	private String productCd;

	private String provinceCd;

	private String serviceAreaClliCd;

	private String serviceClassCd;

	private String serviceSubclassCd;

	private String workOrderCategoryCd;

	private String workOrderClassificationCd;

	private String workgroupCd;

	
}