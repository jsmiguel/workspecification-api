package com.telus.workforcemgmt.workspecification.domain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


/**
 * The persistent class for the WORK_ORDER_RULE database table.
 * 
 */
@Data
@Table("WORK_ORDER_RULE")
public class WorkOrderRule  {
	
	@Id
	private long workOrderRuleId;

	private String calcSkillLevelInd;

	private String componentRequiredInd;

	private LocalDateTime createTs;

	private String createUserId;

	private String delayPeriodCutoffTm;

	private BigDecimal delayPeriodDaysQty;

	private String dmndTypCd;

	private BigDecimal jobPriorityNum;

	private String jobTypeCd;

	private LocalDateTime lastUpdtTs;

	private String lastUpdtUserId;

	private String outOfServiceInd;

	private String productCd;

	private String serviceClassCd;

	private String serviceSubclassCd;

	private String slaInd;

	private String subDemandTypeCd;

	private String technologyCd;

	private String testRequiredInd;

	private String workOrderActionCatgryCd;

	private String workOrderClassificationCd;


}