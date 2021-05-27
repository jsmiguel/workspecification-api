package com.telus.workforcemgmt.workspecification.domain.repository;

import java.time.LocalDateTime;

import com.telus.workforcemgmt.workspecification.domain.entity.WorkOrderRuleSkillLevel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

public interface WorkOrderRuleSkillLevelRepository extends ReactiveCrudRepository<WorkOrderRuleSkillLevel, Long>{
	
	public Flux<WorkOrderRuleSkillLevel> findAllByMultiUnitIndAndEffectiveEndTsAfterAndEffectiveStartTsBefore(String multiUnitInd, LocalDateTime effectiveEndTsBefore, LocalDateTime effectiveStartTs);
	
}
