package com.telus.workforcemgmt.workspecification.domain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


/**
 * The persistent class for the WORK_ORDER_RULE_SKILL_LEVEL database table.
 * 
 */
@Data
@Table("WORK_ORDER_RULE_SKILL_LEVEL")
public class WorkOrderRuleSkillLevel {

	@Id
	private long workOrderRuleSkillLevelId;

	private String causeLevel1Txt;

	private String causeLevel2Txt;

	private String causeLevel3Txt;

	private LocalDateTime createTs;

	private String createUserId;

	private LocalDateTime effectiveEndTs;

	private LocalDateTime effectiveStartTs;

	private LocalDateTime lastUpdtTs;

	private String lastUpdtUserId;

	private String multiUnitInd;

	private BigDecimal requiredLevelNum;

	private BigDecimal skillLevelNum;

	private String skillLevelUsageCd;

	private String technologyCd;

	private String troubleTypeTxt;

	private String workOrderActionCd;

	private String workOrderClassificationCd;


}