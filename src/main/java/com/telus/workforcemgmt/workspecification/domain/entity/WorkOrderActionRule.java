package com.telus.workforcemgmt.workspecification.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


/**
 * The persistent class for the WORK_ORDER_ACTION_RULE database table.
 * 
 */

@Data
@Table("WORK_ORDER_ACTION_RULE")
public class WorkOrderActionRule {

	public enum WORK_ORDER_ACTION_USAGE_CODE {SUB_DEMAND, TIME_WIZARD, DEMAND_STREAM, PARTNER};
	
	@Id
	private long workOrderActionRuleId;

	private LocalDateTime createTs;

	private String createUserId;

	private LocalDateTime effectiveEndTs;

	private LocalDateTime effectiveStartTs;

	private LocalDateTime lastUpdtTs;

	private String lastUpdtUserId;

	private String workOrderActionCd;

	private String workOrderActionCtgryCd;

	private String workOrderActionUsageCd;

	private String workOrderCategoryCd;

	
}