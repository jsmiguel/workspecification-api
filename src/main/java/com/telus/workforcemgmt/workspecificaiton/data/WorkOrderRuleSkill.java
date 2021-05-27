package com.telus.workforcemgmt.workspecificaiton.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


/**
 * The persistent class for the WORK_ORDER_RULE_SKILL database table.
 * 
 */
@Data
@Table("WORK_ORDER_RULE_SKILL")
public class WorkOrderRuleSkill {

	@Id
	private long workOrderRuleSkillId;

	private LocalDateTime createTs;

	private String createUserId;

	private LocalDateTime effectiveEndTs;

	private LocalDateTime effectiveStartTs;

	private String jobTypeCd;

	private LocalDateTime lastUpdtTs;

	private String lastUpdtUserId;

	private String productCd;

	private String serviceClassCd;

	private String serviceSubclassCd;

	private String skillTypeCode;

	private String technologyCd;

	private String workOrderClassificationCd;

	
}