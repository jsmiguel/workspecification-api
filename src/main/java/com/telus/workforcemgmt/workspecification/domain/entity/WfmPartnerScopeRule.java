package com.telus.workforcemgmt.workspecification.domain.entity;



import lombok.Data;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


/**
 * The persistent class for the WFM_PARTNER_SCOPE_RULE database table.
 * 
 */

@Data
@Table("WFM_PARTNER_SCOPE_RULE")
public class WfmPartnerScopeRule {

	@Id
	private long wfmPartnerScopeRuleId;

	private LocalDateTime createTs;

	private String createUserId;

	private LocalDateTime effectiveEndTs;

	private LocalDateTime effectiveStartTs;

	private String jobTypeCd;

	private LocalDateTime lastUpdtTs;

	private String lastUpdtUserId;

	private String partnerCompanyCd;

	private String productCd;

	private String serviceAreaClliCd;

	private String serviceClassCd;

	private String technologyCd;

	private String workOrderActionCatgryCd;

	private String workOrderCategoryCd;

	private String workOrderClassificationCd;

	private String workgroupCd;

	
}