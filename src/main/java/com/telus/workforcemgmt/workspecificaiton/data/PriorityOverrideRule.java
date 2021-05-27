package com.telus.workforcemgmt.workspecificaiton.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


/**
 * The persistent class for the PRIORITY_OVERRIDE_RULE database table.
 * 
 */
@Data
@Table("PRIORITY_OVERRIDE_RULE")
public class PriorityOverrideRule {

	@Id
	private long priorityOverrideRuleId;

	private ZonedDateTime createTs;

	private String createUserId;

	private String domainNm;

	private String domainValueCd;

	private ZonedDateTime effectiveEndTs;

	private ZonedDateTime effectiveStartTs;

	private String jobPriorityCd;

	private ZonedDateTime lastUpdtTs;

	private String lastUpdtUserId;


}