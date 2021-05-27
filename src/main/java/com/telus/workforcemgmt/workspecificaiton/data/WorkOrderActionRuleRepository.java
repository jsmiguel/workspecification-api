package com.telus.workforcemgmt.workspecificaiton.data;

import java.time.LocalDateTime;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

public interface WorkOrderActionRuleRepository extends ReactiveCrudRepository<WorkOrderActionRule, Long>{
	
	public Flux<WorkOrderActionRule> findAllByWorkOrderCategoryCdAndWorkOrderActionCdAndEffectiveEndTsAfterAndEffectiveStartTsBefore(String workOrderCategoryCd, String workOrderActionCd, LocalDateTime effectiveEndTsBefore, LocalDateTime effectiveStartTs);
}
