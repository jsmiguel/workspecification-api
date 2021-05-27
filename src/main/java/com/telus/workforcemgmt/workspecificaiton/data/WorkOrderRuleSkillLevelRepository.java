package com.telus.workforcemgmt.workspecificaiton.data;

import java.time.LocalDateTime;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

public interface WorkOrderRuleSkillLevelRepository extends ReactiveCrudRepository<WorkOrderRuleSkillLevel, Long>{
	
	public Flux<WorkOrderRuleSkillLevel> findAllByMultiUnitIndAndEffectiveEndTsAfterAndEffectiveStartTsBefore(String multiUnitInd, LocalDateTime effectiveEndTsBefore, LocalDateTime effectiveStartTs);
	
}
