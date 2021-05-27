package com.telus.workforcemgmt.workspecification.domain.repository;

import java.time.LocalDateTime;

import com.telus.workforcemgmt.workspecification.domain.entity.SeverityPriorityRule;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


import reactor.core.publisher.Mono;

public interface SeverityPriorityRuleRepository extends ReactiveCrudRepository<SeverityPriorityRule, Long>{

	public Mono<SeverityPriorityRule> findByWorkOrderCategoryCdAndSeverityCdAndEffectiveEndTsAfterAndEffectiveStartTsBefore(String workOrderCategoryCd, String severityCd, LocalDateTime effectiveEndTsBefore, LocalDateTime effectiveStartTs);
}
